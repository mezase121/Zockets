package com.mezase.client.models;

public class Message {

	private String text = "";

	public Message() {

	}

	public void addSegment(String str) {
		text += str + "\n";
	}

	public Message(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
