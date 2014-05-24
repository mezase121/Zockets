package com.mezase.server.controllers;

import com.mezase.server.data.UserPool;
import com.mezase.server.models.Message;
import com.mezase.server.models.User;

public class MessageDistributor {

	private UserPool userPool;

	public MessageDistributor(UserPool userPool) {
		this.userPool = userPool;
	}

	public void distribute(Message message) {
		String code = message.getCode();
		String text = message.getText();

		if (code.equals("01")) {
			for (int i = 0; i < userPool.getSize(); i++) {
				User u = userPool.getUser(i);
				u.write(text);
			}
		}
		else if (code.equals("00")) {
			//echo
		}

	}

}
