package com.mezase.common.models;

public class PrivateMessage extends Message {

	private static final long serialVersionUID = 78521253392697161L;
	
	private int id;
	private String username;

	public PrivateMessage(String text, int code, int id) {
		super(text, code);
		this.id = id;
	}

	public PrivateMessage(String text, int code, String username) {
		super(text, code);
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

}
