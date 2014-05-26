package com.mezase.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.mezase.client.controllers.Controller;
import com.mezase.client.data.MessageQueue;
import com.mezase.client.net.MessageListener;
import com.mezase.client.net.MessageWorker;
import com.mezase.client.view.ClientGUI;

public class Client extends Thread {

	private String HOST;
	private int PORT;
	private Socket socket;
	private Controller controller;
	private MessageQueue queue = new MessageQueue();
	private ClientGUI gui;

	public Client(String HOST, int PORT) {
		this.HOST = HOST;
		this.PORT = PORT;
	}

	public void run() {
		try {
			socket = new Socket(HOST, PORT);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			controller = new Controller(socket, queue, oos);
			gui = new ClientGUI(controller);
			MessageListener ml = new MessageListener(socket, queue);
			MessageWorker mw = new MessageWorker(socket, queue, gui);
			Thread mlt = new Thread(ml);
			Thread mwt = new Thread(mw);
			mlt.start();
			mwt.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
