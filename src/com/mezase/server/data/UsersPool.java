package com.mezase.server.data;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.mezase.server.models.Connection;
import com.mezase.server.models.User;
import com.mezase.server.net.MessageListener;

public class UsersPool {

	private Lock lock = new ReentrantLock();
	private ArrayList<User> users = new ArrayList<User>();
	private int size;

	public void addUser(User user) {
		lock.lock();
		users.add(user);
		size++;
		System.out.println("New user: " + user.getConnection().getIpAddress() + ":" + user.getConnection().getPort() + " (" + size + ")");
		lock.unlock();
		MessageListener ml = new MessageListener(user);
		Thread mlt = new Thread(ml);
		mlt.start();
	}

	public void removeUser(Connection connection) {
		lock.lock();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getConnection().equals(connection)) {
				users.remove(i);
				size--;
				break;
			}
		}
		lock.unlock();
	}

	public User getUserByConnection(Connection connection) {
		User user = null;
		lock.lock();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getConnection().equals(connection)) {
				user = users.get(i);
				break;
			}
		}
		lock.unlock();
		return user;
	}

	public class Test {
		public void removeUser() {
			lock.lock();
			int random = (int) (Math.random() * users.size());
			if (users.size() > 0) {
				users.remove(random);
				setSize(getSize() - 1);
			}
			System.out.println("Removed: " + random + "| Size:" + users.size());
			lock.unlock();
		}

		public void addUser(User user) {
			lock.lock();
			users.add(user);
			setSize(getSize() + 1);
			lock.unlock();
			System.out.println("New user: " + user.getConnection().getIpAddress() + ":" + user.getConnection().getPort());
			MessageListener ml = new MessageListener(user);
			Thread mlt = new Thread(ml);
			mlt.start();
		}
	}

	public Test getTest() {
		return new Test();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
