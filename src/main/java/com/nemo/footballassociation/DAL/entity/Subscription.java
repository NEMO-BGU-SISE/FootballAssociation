package com.nemo.footballassociation.DAL.entity;

public class Subscription {
    protected String name;
    protected String userName;
    protected String password;

    public Subscription(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public boolean login(String userName, String password){
        return true;
    }

    public boolean logout(){
        return true;
    }
}
