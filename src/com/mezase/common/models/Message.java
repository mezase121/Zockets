package com.mezase.common.models;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 3127925688153804302L;
	private String text = "";
	private int code;

	public Message(String text, int code) {
		this.text = text;
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public int getCode() {
		return code;
	}
}
