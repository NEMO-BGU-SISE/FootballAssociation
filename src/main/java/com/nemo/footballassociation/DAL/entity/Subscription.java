package com.nemo.footballassociation.DAL.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Subscription {

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

    public boolean login(String userName, String password){
        return true;
    }

    public boolean logout(){
        return true;
    }
}
