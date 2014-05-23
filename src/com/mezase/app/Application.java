package com.mezase.app;

import com.mezase.client.Client;
import com.mezase.logging.Logger;
import com.mezase.server.Server;
import com.mezase.server.data.MessageQueue;
import com.mezase.server.data.UsersPool;
import com.mezase.server.test.UsersPoolTest;
import com.mezase.server.test.MessageQueueTest;
import com.mezase.client.view.ClientGUI;

public class Application {

	public static void main(String[] args) throws InterruptedException {

		Logger.init("Zockets log");

		//ClientGUI gui = new ClientGUI();
		/*UsersPool up = new UsersPool();
		Thread t1 = new Thread(new UsersPoolTest(up, "add"));
		Thread t2 = new Thread(new UsersPoolTest(up, "remove"));
		t1.start();
		t2.start();*/
		/*MessagesBuffer cp = new MessagesBuffer();
		Thread t1 = new Thread(new MessagesBufferTest(cp, "add"));
		Thread t2 = new Thread(new MessagesBufferTest(cp, "remove"));
		t1.start();
		t2.start();*/
		Server server = new Server();
		server.start();
		for (int i = 0; i < 1; i++) {
			Client client = new Client();
			client.start();
			Thread.sleep(50);
		}
	}

}
