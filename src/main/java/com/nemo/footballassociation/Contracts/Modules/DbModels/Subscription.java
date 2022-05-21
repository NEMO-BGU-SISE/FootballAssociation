package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Subscription {
    @Id
    private int id;
    protected String name;
    protected String user_name;
    protected String password;

    public Subscription(String name, String user_name, String password) {
        this.name = name;
        this.user_name = user_name;
        this.password = password;
    }

    public Subscription() {

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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String userName) {
        this.user_name = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
