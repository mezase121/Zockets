package com.mezase.server.controllers;

import com.mezase.server.data.UserPool;
import com.mezase.server.models.Connection;
import com.mezase.server.models.User;
import com.mezase.server.net.MessageWorker;

public class Controller {

	private UserPool usersPool;
	private MessageDistributor md;

	public Controller() {
		usersPool = new UserPool();
		md = new MessageDistributor(usersPool);
	}

	public void addUser(Connection connection) {
		User user = new User(usersPool.getSize(), connection);
		usersPool.addUser(user);
		MessageWorker mw = new MessageWorker(user, user.getQueue(), md);
		Thread mwt = new Thread(mw);
		mwt.start();
	}

	public void removeUser(Connection connection) {
		usersPool.removeUser(connection);
	}
}
