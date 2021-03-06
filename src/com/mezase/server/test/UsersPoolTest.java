package com.mezase.server.test;

import com.mezase.common.models.User;
import com.mezase.server.data.UserPool;
import com.mezase.server.models.Connection;

public class UsersPoolTest implements Runnable {

	private UserPool pool;
	private String testerType;

	public UsersPoolTest(UserPool pool, String testerType) {
		this.pool = pool;
		this.testerType = testerType;
	}

	@Override
	public void run() {
		if (testerType.equals("add")) {
			while (true) {
				pool.getTest().addUser(new User(pool.getSize(), new Connection()));
				sleep(1);
			}
		}
		if (testerType.equals("remove")) {
			while (true) {
				pool.getTest().removeUser();
				sleep(1);
			}
		}
	}

	public void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
