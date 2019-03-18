package com.senla.beans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "event")
public class Event extends AbstractModel {
    @Column(name = "name")
    private String name;
    @Column(name = "about_event")
    private String aboutEvent;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_event")
    private Date dateEvent;
    @ManyToMany
    @JoinTable(name = "user_event",
            joinColumns = @JoinColumn(name = "id_event"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<User> subscribers;
    @ManyToOne
    @JoinColumn(name = "id_moderator")
    private User moderator;

    public Event() {
    }

    public Event(String name, String aboutEvent, Date dateEvent) {
        this.name = name;
        this.aboutEvent = aboutEvent;
        this.dateEvent = dateEvent;
        this.subscribers = new ArrayList<User>();
    }

    public String getName() {
        return name;
    }

    public String getAboutEvent() {
        return aboutEvent;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAboutEvent(String aboutEvent) {
        this.aboutEvent = aboutEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
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
}
