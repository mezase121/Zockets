package com.mezase.server.controllers;

import com.mezase.server.data.UsersPool;
import com.mezase.server.models.Connection;
import com.mezase.server.models.User;
import com.mezase.server.net.UserMessageWorker;

public class Controller {

	private UsersPool usersPool;

	public Controller() {
		usersPool = new UsersPool();
	}

	public void addUser(Connection connection) {
		User user = new User(usersPool.getSize(), connection);
		usersPool.addUser(user);
		UserMessageWorker mw = new UserMessageWorker(user, user.getQueue());
		Thread mwt = new Thread(mw);
		mwt.start();
	}

	public void removeUser(Connection connection) {
		usersPool.removeUser(connection);
	}
	
	public void routeMessage(){
		
	}
}
