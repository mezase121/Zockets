package com.mezase.server.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.mezase.common.models.Message;
import com.mezase.server.models.User;

public class MessageListener implements Runnable {

	private User user;
	private BufferedReader br;
	private ObjectInputStream ois;
	private String message;
	private boolean running;

	public MessageListener(User user) {
		this.user = user;
		try {
			ois = new ObjectInputStream(user.getConnection().getSocket().getInputStream());
			running = true;
		} catch (IOException e) {
			running = false;
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (running) {
			try {
				Object o = ois.readObject();
				if (o.getClass().equals(Message.class)) {
					user.addMessage((Message) o);
				}
			} catch (Exception e) {
				try {
					user.getConnection().getSocket().close();
					ois.close();
					running = false;
					//e.printStackTrace();
					System.out.println("User " + user.getInfo() + " has disconnected.");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
