package com.mezase.client.net;

import com.mezase.client.data.MessageQueue;
import com.mezase.client.view.ClientGUI;

public class MessageWorker implements Runnable {

	private MessageQueue queue;
	private ClientGUI gui;
	private int timer;

	public MessageWorker(MessageQueue queue, ClientGUI gui) {
		this.queue = queue;
		this.gui = gui;
	}

	@Override
	public void run() {
		while (true) {
			if (queue.getMessages().size() > 0) {
				String message = queue.readMessage().getText();
				gui.updateOutput(message);
				timer = 0;
			}
			else {
				timer++;
				if (timer > 900000) {
					timer = 4000;
				}
			}
			if (timer >= 4000) {
				sleep(2);
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
