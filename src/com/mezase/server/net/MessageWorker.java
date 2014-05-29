package com.mezase.server.net;

import com.mezase.common.models.User;
import com.mezase.common.models.interfaces.IMessage;
import com.mezase.server.controllers.MessageDistributor;
import com.mezase.server.data.MessageQueue;
import com.mezase.server.data.UserPool;

public class MessageWorker implements Runnable {

	private MessageDistributor md;

	private User user;
	private MessageQueue queue;
	private int timer;
	private boolean running = true;

	public MessageWorker(User user, MessageDistributor md) {
		this.user = user;
		this.queue = user.getQueue();
		this.md = md;
	}

	@Override
	public void run() {
		while (running) {
			if (queue.getMessages().size() > 0) {
				IMessage message = queue.readMessage();
				System.out.println("Server received: " + message.getText());
				md.distribute(message);
				timer = 0;
			}
			else {
				timer++;
				if (timer >= Integer.MAX_VALUE) {
					timer = 10000;
				}
			}
			if (timer >= 10000) {
				sleep(10);
			}
			if (user.getConnection().getSocket().isClosed()) {
				running = false;
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
