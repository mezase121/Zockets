package com.mezase.client.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.mezase.client.data.MessageQueue;
import com.mezase.common.models.Message;

public class MessageListener implements Runnable {

	private ObjectInputStream ois;
	private String message;
	private MessageQueue queue;
	private boolean running = true;

	public MessageListener(Socket socket, MessageQueue queue) {
		try {
			this.queue = queue;
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (running) {
			try {
				Message message = (Message) ois.readObject();
				queue.addMessage(message);
			} catch (Exception e) {
				try {
					ois.close();
					running = false;
					//e.printStackTrace(); //Client disconnected
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
