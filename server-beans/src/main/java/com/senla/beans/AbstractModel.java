package com.senla.beans;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}