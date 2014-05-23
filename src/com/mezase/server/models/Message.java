package com.mezase.server.models;

public class Message {

	private String text;
	private User user;
	private String code;

	public Message(String text, User user, String code) {
		this.text = text;
		this.user = user;
		this.code = code;
	}

	public Message(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public User getUser() {
		return user;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return "Message [text=" + text + ", user=" + user + ", code=" + code + "]";
	}

}
