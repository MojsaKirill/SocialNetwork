package com.senla.web.dto;

import java.util.Date;

public class EventDto {
	private long id;
	private String name;
	private String aboutEvent;
	private Date dateEvent;
	private UserDto moderator;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAboutEvent() {
		return aboutEvent;
	}

	public void setAboutEvent(String aboutEvent) {
		this.aboutEvent = aboutEvent;
	}

	public Date getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(Date dateEvent) {
		this.dateEvent = dateEvent;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserDto getModerator() {
		return moderator;
	}

	public void setModerator(UserDto moderator) {
		this.moderator = moderator;
	}
}
