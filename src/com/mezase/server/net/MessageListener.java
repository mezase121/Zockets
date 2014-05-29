package com.mezase.server.net;

import java.io.IOException;
import java.io.ObjectInputStream;

import com.mezase.common.models.Message;
import com.mezase.common.models.User;

public class MessageListener implements Runnable {

	private User user;
	private ObjectInputStream ois;
	private boolean running;

	public MessageListener(User user) {
		this.user = user;
		try {
			ois = new ObjectInputStream(user.getConnection().getSocket().getInputStream());
			running = true;
		} catch (Exception e) {
			//e.printStackTrace(); //Corrupted Stream - invalid header
			try {
				user.getConnection().getSocket().close();
				running = false;
				System.out.println("User " + user.getInfo() + " has disconnected.");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		while (running) {
			try {
				Object o = ois.readObject();
				System.out.println("tel:"+o.getClass());
				if (o.getClass().equals(Message.class)) {
					user.addMessage((Message) o);
				}
			} catch (Exception e) {
				try {
					user.getConnection().getSocket().close();
					ois.close();
					running = false;
					System.out.println("User " + user.getInfo() + " has disconnected.");
					//e.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
