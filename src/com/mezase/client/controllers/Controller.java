package com.mezase.client.controllers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.mezase.client.data.MessageQueue;
import com.mezase.common.models.Message;

public class Controller {

	private Socket socket;
	private MessageQueue queue;
	private ObjectOutputStream oos;

	public Controller(Socket socket, MessageQueue queue, ObjectOutputStream oos) {
		this.socket = socket;
		this.queue = queue;
		this.oos = oos;

	}

	public void writeBroadcastMessage(String data) {
		Message message = new Message(data, 0);
		try {
			oos.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			oos.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
