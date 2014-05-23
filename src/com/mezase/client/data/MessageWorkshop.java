package com.mezase.client.data;

public class MessageWorkshop {

	public void construct(MessageBuilder mb, String data) {
		mb.addHeader();
		mb.addData(data);
		mb.addEnd();
		mb.addCode();
	}

}
