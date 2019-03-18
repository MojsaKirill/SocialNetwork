package com.senla.beans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "forum")
public class Forum extends AbstractModel {
    @Column(name = "name")
    private String name;
    @Column(name = "topic")
    private String topic;
    @ManyToMany
    @JoinTable(name = "user_forum",
            joinColumns = @JoinColumn(name = "id_forum"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<User> subscribers;
    @ManyToOne
    @JoinColumn(name = "id_moderator")
    private User moderator;
    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL)
    private List<ForumMessage> messages;

    public Forum() {

    }

    public Forum(String name, String topic) {
        this.name = name;
        this.topic = topic;
        this.subscribers = new ArrayList<User>();
    }

    public String getName() {
        return name;
    }

    public String getTopic() {
        return topic;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setSubscribers(List<User> subscribers) {
        this.subscribers = subscribers;
    }

    public User getModerator() {
        return moderator;
    }

    public void setModerator(User moderator) {
        this.moderator = moderator;
    }

    public List<ForumMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ForumMessage> messages) {
        this.messages = messages;
    }
}
