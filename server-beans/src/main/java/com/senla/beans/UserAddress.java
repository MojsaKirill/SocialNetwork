package com.senla.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class UserAddress extends AbstractModel {
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "home")
    private String home;
    @OneToOne(mappedBy = "userAddress")
    private User user;

    public UserAddress() {
    }

    public UserAddress(String city, String country, String home) {
        this.city = city;
        this.country = country;
        this.home = home;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getHome() {
        return home;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
