package com.nemo.footballassociation.Contracts.Modules.DbModels;

import org.hibernate.annotations.Generated;

import javax.persistence.*;

@MappedSuperclass
//@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Subscription {

    @Id
    private int id;
    protected String name;
    protected String userName;
    protected String password;

    public Subscription(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean login(String userName, String password) {
        return true;
    }

    public boolean logout() {
        return true;
    }
}
