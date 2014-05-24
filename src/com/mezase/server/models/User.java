package com.mezase.server.models;

import java.io.IOException;
import java.io.PrintWriter;

import com.mezase.server.data.MessageQueue;

public class User {

	private String username;
	private long id;
	private Connection connection;
	private MessageQueue queue = new MessageQueue();
	private PrintWriter out;

	public User(long id, Connection connection) {
		this.id = id;
		this.connection = connection;
		try {
			out = new PrintWriter(connection.getSocket().getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write(String message) {
		out.println(message);
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void addMessage(Message message) {
		queue.addMessage(message);
	}

	public Message readMessage() {
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
