package com.mezase.server.data;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.mezase.common.models.Message;
import com.mezase.common.models.interfaces.IMessage;

public class MessageQueue {

	private Lock lock = new ReentrantLock();
	private LinkedList<IMessage> messages = new LinkedList<IMessage>();

	public MessageQueue() {

	}

	public void addMessage(IMessage message) {
		lock.lock();
		messages.add(message);
		lock.unlock();
	}

	public IMessage readMessage() {
		IMessage message = null;
		lock.lock();
		if (messages.size() > 0) {
			message = messages.pollFirst();
		}
		lock.unlock();
		return message;
	}

	public LinkedList<IMessage> getMessages() {
		return messages;
	}

	public class Test {
		public void addMessage(Message message) {
			lock.lock();
			messages.add(message);
			lock.unlock();
		}

		public IMessage readMessage() {
			IMessage message = null;
			lock.lock();
			if (messages.size() > 0) {
				message = messages.pollFirst();
			}
			lock.unlock();
			return message;
		}
	}

	public Test getTest() {
		return new Test();
	}
}
