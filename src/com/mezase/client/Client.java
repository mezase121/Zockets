package com.mezase.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.mezase.client.controllers.Controller;
import com.mezase.client.data.MessageQueue;
import com.mezase.client.net.MessageListener;
import com.mezase.client.net.MessageWorker;
import com.mezase.client.view.ClientGUI;

public class Client extends Thread {

	private String HOST = "localhost";
	private int PORT = 10511;
	private Socket socket;
	private Controller controller;
	private MessageQueue queue = new MessageQueue();
	private String msg;
	private ClientGUI gui;

	public Client() {

	}

	public void run() {
		try {
			socket = new Socket(HOST, PORT);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			controller = new Controller(socket, queue, out);
			gui = new ClientGUI(controller);
			MessageListener ml = new MessageListener(socket, queue);
			MessageWorker mw = new MessageWorker(queue, gui);
			Thread mlt = new Thread(ml);
			Thread mwt = new Thread(mw);
			mlt.start();
			mwt.start();			
			/*while (true) {
				out.println(msg);
				out.println("#00!"); //Print message end
				out.println("001"); //Print code
				Thread.sleep(1000);
			}*/
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
