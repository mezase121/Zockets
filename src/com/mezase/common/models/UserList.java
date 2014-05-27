package com.mezase.common.models;

import java.io.Serializable;
import java.util.ArrayList;

public class UserList implements Serializable {

	private static final long serialVersionUID = 2514158042380081535L;

	private ArrayList<User> users = new ArrayList<User>();

	public UserList() {

	}

	public void addUser(User user) {
		users.add(user);
	}

	public ArrayList<User> getUsers() {
		return users;
	}

}
