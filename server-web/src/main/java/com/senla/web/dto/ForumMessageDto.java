package com.senla.web.dto;

import com.senla.beans.User;

import javax.persistence.TemporalType;
import java.util.Date;

public class ForumMessageDto {
    private long id;
    private String message;
    private Date date;
    private UserDto recipient;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserDto getRecipient() {
        return recipient;
    }

    public void setRecipient(UserDto recipient) {
        this.recipient = recipient;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
