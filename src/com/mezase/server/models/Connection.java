package com.mezase.server.models;

import java.net.Socket;

public class Connection {

	private Socket socket;
	private int port;
	private String ipAddress;

	public Connection(Socket socket) {
		this.socket = socket;
		this.ipAddress = socket.getInetAddress().getHostAddress();
		this.port = socket.getPort();
	}

	public Connection() {
	}

	public Socket getSocket() {
		return socket;
	}

	public int getPort() {
		return port;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getInfo() {
		return ipAddress + ":" + port;
	}

}
