package com.example.demo.user;

import java.util.Date;

public class Tweet {
	
	private int id;
	private Date timestamp;
	private String message;

	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", timestamp=" + timestamp + ", message=" + message + "]";
	}
	
	

}
































