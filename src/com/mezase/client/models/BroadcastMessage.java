package com.mezase.client.models;

import com.mezase.client.data.MessageBuilder;

public class BroadcastMessage extends MessageBuilder {

	@Override
	public void addHeader() {
		message.addSegment("!0_Hd#");
	}

	@Override
	public void addData(String data) {
		message.addSegment(data);
	}

	@Override
	public void addEnd() {
		message.addSegment("!0_end#");
	}

	@Override
	public void addCode() {
		message.addSegment("01");
	}

}
