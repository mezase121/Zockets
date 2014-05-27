package com.mezase.client.net;

import com.mezase.client.data.MessageQueue;
import com.mezase.client.view.ClientGUI;

public class MessageWorker implements Runnable {

	private Connection connection;
	private MessageQueue queue;
	private ClientGUI gui;
	private int timer;
	private boolean running = true;

	public MessageWorker(Connection connection, MessageQueue queue, ClientGUI gui) {
		this.connection = connection;
		this.queue = queue;
		this.gui = gui;
	}

	@Override
	public void run() {
		while (running && !connection.isInactive()) {
			if (queue.getMessages().size() > 0) {
				String message = queue.readMessage().getText();
				gui.updateOutput(message);
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
			if (connection.getSocket().isClosed()) {
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
