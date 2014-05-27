package com.mezase.client.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {

	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private boolean inactive = false;

	public Connection(String host, int port) {
		try {
			this.socket = new Socket(host, port);
			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			oos.close();
			socket.close();
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public ObjectOutputStream getOos() {
		return oos;
	}

	public ObjectInputStream getOis() {
		return ois;
	}

	public void shutdown() {
		this.inactive = true;
	}

	public boolean isInactive() {
		return inactive;
	}

	public boolean isConnected() {
		if (socket == null) {
			return false;
		}
		return socket.isConnected();
	}

}
