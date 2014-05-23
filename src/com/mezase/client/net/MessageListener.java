package com.mezase.client.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.mezase.client.data.MessageQueue;
import com.mezase.client.models.Message;

public class MessageListener implements Runnable {

	private BufferedReader br;
	private String message;
	private MessageQueue queue;

	public MessageListener(Socket socket, MessageQueue queue) {
		try {
			this.queue = queue;
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				message = br.readLine();
				queue.addMessage(new Message(message));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
