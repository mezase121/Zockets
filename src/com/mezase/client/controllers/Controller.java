package com.mezase.client.controllers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.mezase.client.data.MessageQueue;
import com.mezase.client.net.Connection;
import com.mezase.common.models.Message;
import com.mezase.common.models.interfaces.IMessage;

public class Controller {

	private Connection connection;
	private MessageQueue queue;

	public Controller(Connection connection, MessageQueue queue) {
		this.connection = connection;
		this.queue = queue;
	}

	public void writeBroadcastMessage(String data) {
		IMessage message = new Message(data, 0);
		try {
			if(connection.isConnected())
			connection.getOos().writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		connection.close();
	}

	public void shutdown() {
		connection.shutdown();
	}

	public boolean isConnected() {
		return connection.isConnected();
	}

}
