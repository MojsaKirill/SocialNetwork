package com.senla.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
public class Message extends AbstractModel {
    @Column(name = "message")
    private String message;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "id_recipient")
    private User recipient;
    @ManyToOne
    @JoinColumn(name = "id_sender")
    private User sender;

    public Message() {
    }

    public Message(String message, User recipient, User sender, Date date) {
        this.message = message;
        this.recipient = recipient;
        this.sender = sender;
        this.date = date;
    }

    public Message(String message, User sender) {
        this.message = message;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public User getSender() {
        return sender;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
