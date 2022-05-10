package com.nemo.footballassociation.Contracts.Modules.DbModels;

public class ClubOwner extends Subscription{
    private float salary;

    public ClubOwner(float salary, String name, String userName, String password) {
        super(name, userName, password);
        this.salary = salary;
    }
}
