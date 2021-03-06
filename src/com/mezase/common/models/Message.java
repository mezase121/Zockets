package com.mezase.common.models;

import java.io.Serializable;

import com.mezase.common.models.interfaces.IMessage;

public class Message implements Serializable, IMessage {

	private static final long serialVersionUID = -6500130205281164170L;
	private String text = "";
	private int code;

	public Message(String text, int code) {
		this.text = text;
		this.code = code;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public int getCode() {
		return code;
	}
}
