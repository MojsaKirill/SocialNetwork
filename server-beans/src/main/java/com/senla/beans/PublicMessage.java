package com.senla.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "public_message")
public class PublicMessage extends AbstractModel {
    @Column(name = "message")
    private String message;
    @ManyToOne
    @JoinColumn(name = "id_recipient")
    private User recipient;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    public PublicMessage() {
    }

    public PublicMessage(String message, User recipient, Date date) {
        this.message = message;
        this.recipient = recipient;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
