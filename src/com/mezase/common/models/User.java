package com.mezase.common.models;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.mezase.common.models.interfaces.IMessage;
import com.mezase.server.data.MessageQueue;
import com.mezase.server.models.Connection;

public class User implements Serializable {

	private static final long serialVersionUID = -3104695998243120654L;

	private String username;
	private long id;
	private Connection connection;
	private MessageQueue queue = new MessageQueue();
	private ObjectOutputStream oos;

	public User(long id, Connection connection) {
		this.id = id;
		this.connection = connection;
		try {
			oos = new ObjectOutputStream(connection.getSocket().getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write(IMessage message) {
		try {
			oos.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void addMessage(IMessage message) {
		queue.addMessage(message);
	}

	public IMessage readMessage() {
		return queue.readMessage();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Connection getConnection() {
		return connection;
	}

	public String getUsername() {
		return username;
	}

	public MessageQueue getQueue() {
		return queue;
	}

	public String getInfo() {
		Connection c = connection;
		return c.getIpAddress() + ":" + c.getPort() + "(" + id + ")";
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", id=" + id + ", connection=" + connection + ", queue=" + queue + "]";
	}
}
