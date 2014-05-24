package com.mezase.server.net;

import com.mezase.server.controllers.MessageDistributor;
import com.mezase.server.data.MessageQueue;
import com.mezase.server.models.Message;
import com.mezase.server.models.User;

public class MessageWorker implements Runnable {

	private MessageDistributor md;

	private User user;
	private MessageQueue queue;
	private int timer;

	public MessageWorker(User user, MessageQueue queue, MessageDistributor md) {
		this.user = user;
		this.queue = queue;
		this.md = md;
	}

	@Override
	public void run() {
		while (true) {
			if (queue.getMessages().size() > 0) {
				Message message = queue.readMessage();
				System.out.println("Server received: " + message);
				
				md.distribute(message);
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
