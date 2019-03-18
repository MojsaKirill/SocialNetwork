package com.senla.beans;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user")
public class User extends AbstractModel {
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_id")
    private UserInfo userInfo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private UserAddress userAddress;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_forum",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_forum"))
    private List<Forum> forums;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_event",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_event"))
    private List<Event> events;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_friend",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_friend"))
    private List<User> friends;
    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messages;
    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PublicMessage> publicMessages;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles;

    public User() {
    }

    public User(long id) {
        this.setId(id);
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.forums = new ArrayList<Forum>();
        this.events = new ArrayList<Event>();
        this.friends = new ArrayList<User>();
        this.messages = new ArrayList<Message>();
        this.roles = new HashSet<Role>();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public List<Forum> getForums() {
        return forums;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setForums(List<Forum> forums) {
        this.forums = forums;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<PublicMessage> getPublicMessages() {
        return publicMessages;
    }

    public void setPublicMessages(List<PublicMessage> publicMessages) {
        this.publicMessages = publicMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, email);
    }
}
