package com.mezase.server.test;

import com.mezase.common.models.Message;
import com.mezase.server.data.MessageQueue;

public class MessageQueueTest implements Runnable {

	private MessageQueue buffer;
	private String testerType;

	public MessageQueueTest(MessageQueue buffer, String testerType) {
		this.buffer = buffer;
		this.testerType = testerType;
	}

	@Override
	public void run() {
		if (testerType.equals("add")) {
			while (true) {
				buffer.addMessage(new Message("Hi!", 0));
				System.out.println("Added: Hi!" + " | Size:" + buffer.getMessages().size());
				sleep(1);
			}
		}
		if (testerType.equals("remove")) {
			while (true) {
				System.out.println("Removed: " + buffer.readMessage().getText() + "| Size:" + buffer.getMessages().size());
				sleep(1);
			}
		}
	}

	public void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
