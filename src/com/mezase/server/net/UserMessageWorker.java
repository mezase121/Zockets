package com.mezase.server.net;

import com.mezase.server.data.MessageQueue;
import com.mezase.server.models.User;

public class UserMessageWorker implements Runnable {

	private User user;
	private MessageQueue queue;
	private int timer;

	public UserMessageWorker(User user, MessageQueue queue) {
		this.user = user;
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			if (queue.getMessages().size() > 0) {
				String message = queue.readMessage().getText();
				System.out.println("Server received: " + message);
				//user.write(message);
				timer = 0;
			}
			else {
				timer++;
				if (timer > 900000) {
					timer = 5000;
				}
			}
			if (timer >= 5000) {
				sleep(5);
			}
		}
	}

	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
