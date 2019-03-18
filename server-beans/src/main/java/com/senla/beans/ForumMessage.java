package com.senla.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "forum_message")
public class ForumMessage extends AbstractModel {
    @Column(name = "message")
    private String message;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "id_recipient")
    private User recipient;
    @ManyToOne
    @JoinColumn(name = "id_forum")
    private Forum forum;

    public ForumMessage() {
    }

    public ForumMessage(String message, Date date, User recipient, Forum forum) {
        this.message = message;
        this.date = date;
        this.recipient = recipient;
        this.forum = forum;
    }

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

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }
}
