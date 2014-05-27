package com.mezase.client.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.mezase.client.data.MessageQueue;
import com.mezase.common.models.Message;

public class MessageListener implements Runnable {

	private Connection connection;
	private String message;
	private MessageQueue queue;
	private boolean running = true;

	public MessageListener(Connection connection, MessageQueue queue) {
		this.queue = queue;
		this.connection = connection;
	}

	@Override
	public void run() {
		while (running && !connection.isInactive()) {
			try {
				Message message = (Message) connection.getOis().readObject();
				queue.addMessage(message);
			} catch (Exception e) {
				running = false;
				//e.printStackTrace(); //Client disconnected
			}
		}
	}

}
