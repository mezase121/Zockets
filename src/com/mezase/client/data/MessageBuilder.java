package com.mezase.client.data;

import com.mezase.client.models.Message;

public abstract class MessageBuilder {

	protected Message message = new Message();

	abstract public void addHeader();

	abstract public void addData(String data);

	abstract public void addEnd();

	abstract public void addCode();
	
	public Message getMessage(){
		return message;
	}
}
