package com.mezase.server.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mezase.server.controllers.MessageDistributor;
import com.mezase.server.models.Message;
import com.mezase.server.models.User;

public class MessageListener implements Runnable {
	
	private User user;
	private BufferedReader br;
	private String message;
	private boolean running;

	public MessageListener(User user) {
		this.user = user;
		try {
			br = new BufferedReader(new InputStreamReader(user.getConnection().getSocket().getInputStream()));
			running = true;
		} catch (IOException e) {
			running = false;
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (running) {
			try {
				message = "";
				String header = ""; // Accept Header !0_Hd#
				String data = ""; // Message data
				String code = ""; // What to do with the message
				header = br.readLine();
				if (header.equals("!0_Hd#")) {
					while (!data.equals("!0_end#")) { // End of data = !0_end#
						data = br.readLine();
						if (!data.equals("!0_end#")) {
							if (message.length() == 0) {
								message += data;
							}
							else {
								message += "\n" + data;
							}
						}
						else {
							break;
						}
					}
				}
				code = br.readLine(); // What to do with the message
				if (code == null) {
					System.out.println("User " + user.getConnection().getIpAddress() + " has disconnected.");
					user.getConnection().getSocket().close();
					br.close();
					running = false;
				}
				else {
					if (code.equals("001")) {
					}
					else {
						user.addMessage(new Message(message, user, code));
					}
				}
			} catch (Exception e) {
				try {
					user.getConnection().getSocket().close();
					br.close();
					running = false;
					e.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
