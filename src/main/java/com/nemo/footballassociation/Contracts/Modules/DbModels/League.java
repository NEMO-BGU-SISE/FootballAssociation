package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.*;

@Entity
public class League {
    @Id
    private int id;

    private String name;

    public League() {
    }

    public League(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
