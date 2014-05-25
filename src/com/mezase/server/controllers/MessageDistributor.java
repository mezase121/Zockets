package com.mezase.server.controllers;

import com.mezase.common.models.interfaces.IMessage;
import com.mezase.server.data.UserPool;
import com.mezase.server.models.User;

public class MessageDistributor {

	private UserPool userPool;

	public MessageDistributor(UserPool userPool) {
		this.userPool = userPool;
	}

	public void distribute(IMessage message) {
		int code = message.getCode();
		switch (code) {
		case 0:
			for (int i = 0; i < userPool.getSize(); i++) {
				User u = userPool.getUser(i);
				u.write(message);
			}
			break;
		}
	}
}
