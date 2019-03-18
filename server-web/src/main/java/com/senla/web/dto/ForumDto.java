package com.senla.web.dto;

public class ForumDto {
	private long id;
	private String name;
	private String topic;
	private UserDto moderator;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public UserDto getModerator() {
		return moderator;
	}

	public void setModerator(UserDto moderator) {
		this.moderator = moderator;
	}
}
