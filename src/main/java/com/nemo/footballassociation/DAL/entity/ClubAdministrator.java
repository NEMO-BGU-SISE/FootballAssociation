package com.nemo.footballassociation.DAL.entity;

public class ClubAdministrator extends Subscription{
    private float salary;

    public ClubAdministrator(float salary, String name, String userName, String password) {
        super(name, userName, password);
        this.salary = salary;
    }
}
