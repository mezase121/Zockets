package com.mezase.app;

import com.mezase.client.Client;
import com.mezase.logging.ZLogger;
import com.mezase.server.Server;

public class Application {

	/**
	 * TODO:
	 * -implement observer pattern to update login/logout and notify other uers (observers). p.85
	 */
	private static String HOST = "localhost";
	private static int PORT = 10511;
	private static int MAX_CONNECTIONS = 1000;

	public static void main(String[] args) throws InterruptedException {		
		ZLogger.init("Zockets");

		int opt = 3;
		if (opt == 1 || opt == 3) {
			Server server = new Server(PORT, MAX_CONNECTIONS);
			server.start();
		}
		if (opt == 2 || opt == 3) {
			for (int i = 0; i < 2; i++) {
				Client client = new Client(HOST, PORT);
				client.start();
				Thread.sleep(25);
			}
		}
	}

}
