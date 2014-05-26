package com.mezase.server.net;

import com.mezase.common.models.interfaces.IMessage;
import com.mezase.server.controllers.MessageDistributor;
import com.mezase.server.data.MessageQueue;
import com.mezase.server.data.UserPool;
import com.mezase.server.models.User;

public class MessageWorker implements Runnable {

	private MessageDistributor md;

	private User user;
	private MessageQueue queue;
	private UserPool userPool;
	private int timer;
	private boolean running = true;

	public MessageWorker(User user, MessageQueue queue, MessageDistributor md, UserPool userPool) {
		this.user = user;
		this.queue = queue;
		this.md = md;
		this.userPool = userPool;
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
				userPool.removeUser(user);
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
