package com.mezase.client.controllers;

import java.io.PrintWriter;
import java.net.Socket;

import com.mezase.client.data.MessageQueue;
import com.mezase.client.data.MessageWorkshop;
import com.mezase.client.models.*;

public class Controller {

	private Socket socket;
	private MessageQueue queue;
	private PrintWriter out;
	private MessageWorkshop mws = new MessageWorkshop();

	public Controller(Socket socket, MessageQueue queue, PrintWriter out) {
		this.socket = socket;
		this.queue = queue;
		this.out = out;

	}

	public void writeBroadcastMessage(String data) {
		BroadcastMessage message = new BroadcastMessage();
		mws.construct(message, data);
		out.print(message.getMessage().getText());
		out.flush();
	}

}
