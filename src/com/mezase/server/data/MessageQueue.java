package com.mezase.server.data;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.mezase.server.models.Message;

public class MessageQueue {

	private Lock lock = new ReentrantLock();
	private LinkedList<Message> messages = new LinkedList<Message>();

	public MessageQueue() {

	}

	public void addMessage(Message message) {
		lock.lock();
		messages.add(message);
		lock.unlock();
	}

	public Message readMessage() {
		Message message = new Message("");
		lock.lock();
		if (messages.size() > 0) {
			message = messages.pollFirst();
		}
		lock.unlock();
		return message;
	}

	public LinkedList<Message> getMessages() {
		return messages;
	}

	public class Test {
		public void addMessage(Message message) {
			lock.lock();
			messages.add(message);
			lock.unlock();
		}

		public Message readMessage() {
			Message message = new Message("");
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
