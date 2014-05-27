package com.mezase.client;

import com.mezase.client.controllers.Controller;
import com.mezase.client.data.MessageQueue;
import com.mezase.client.net.Connection;
import com.mezase.client.net.MessageListener;
import com.mezase.client.net.MessageWorker;
import com.mezase.client.view.ClientGUI;

public class Client extends Thread {

	private String HOST;
	private int PORT;
	private Connection connection;
	private Controller controller;
	private MessageQueue queue = new MessageQueue();
	private ClientGUI gui;

	public Client(String HOST, int PORT) {
		this.HOST = HOST;
		this.PORT = PORT;
	}

	public void run() {
		try {
			connection = new Connection(HOST, PORT);
			controller = new Controller(connection, queue);
			gui = new ClientGUI(controller);
			if (connection.isConnected()) {
				MessageListener ml = new MessageListener(connection, queue);
				MessageWorker mw = new MessageWorker(connection, queue, gui);
				Thread mlt = new Thread(ml);
				Thread mwt = new Thread(mw);
				mlt.setDaemon(true);
				mwt.setDaemon(true);
				mlt.start();
				mwt.start();
				mlt.join();
				mwt.join();
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
