package com.mezase.client.net;

import java.net.Socket;

import com.mezase.client.data.MessageQueue;
import com.mezase.client.view.ClientGUI;

public class MessageWorker implements Runnable {

	private Socket socket;
	private MessageQueue queue;
	private ClientGUI gui;
	private int timer;
	private boolean running = true;

	public MessageWorker(Socket socket, MessageQueue queue, ClientGUI gui) {
		this.socket = socket;
		this.queue = queue;
		this.gui = gui;
	}

	@Override
	public void run() {
		while (running) {
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
			if (socket.isClosed()) {
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
