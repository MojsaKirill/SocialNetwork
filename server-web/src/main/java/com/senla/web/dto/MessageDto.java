package com.senla.web.dto;

import java.util.Date;

public class MessageDto {
	private long id;
	private String message;
	private UserDto recipient;
	private UserDto sender;
	private Date date;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserDto getRecipient() {
		return recipient;
	}

	public void setRecipient(UserDto recipient) {
		this.recipient = recipient;
	}

	public UserDto getSender() {
		return sender;
	}

	public void setSender(UserDto sender) {
		this.sender = sender;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
