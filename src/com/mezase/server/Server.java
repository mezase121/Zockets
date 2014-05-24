package com.mezase.server;

import java.io.IOException;
import java.net.ServerSocket;

import com.mezase.server.controllers.Controller;
import com.mezase.server.controllers.MessageDistributor;
import com.mezase.server.data.MessageQueue;
import com.mezase.server.net.ConnectionListener;

public class Server extends Thread {

	private int PORT = 10511;
	private int MAX_CONNECTIONS = 1000;
	private ServerSocket ssocket;
	private Controller controller = new Controller();

	public Server() {

	}

	public void start() {
		try {
			ssocket = new ServerSocket(PORT);
			ConnectionListener cl = new ConnectionListener(ssocket, controller);
			Thread clt = new Thread(cl);
			clt.start();
			System.out.println(":::Server online:::");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			ssocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
