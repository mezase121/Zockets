package com.mezase.server.net;

import java.net.ServerSocket;
import java.net.Socket;

import com.mezase.server.controllers.Controller;
import com.mezase.server.models.Connection;

public class ConnectionListener implements Runnable {

	private ServerSocket ssocket;
	private Controller controller;

	public ConnectionListener(ServerSocket ssocket, Controller controller) {
		this.ssocket = ssocket;
		this.controller = controller;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket socket = ssocket.accept();
				controller.addUser(new Connection(socket));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
