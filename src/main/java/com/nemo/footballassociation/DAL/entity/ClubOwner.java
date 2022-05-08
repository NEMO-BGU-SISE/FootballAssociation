package com.nemo.footballassociation.DAL.entity;

public class ClubOwner extends Subscription{
    private float salary;

    public ClubOwner(float salary, String name, String userName, String password) {
        super(name, userName, password);
        this.salary = salary;
    }
}
