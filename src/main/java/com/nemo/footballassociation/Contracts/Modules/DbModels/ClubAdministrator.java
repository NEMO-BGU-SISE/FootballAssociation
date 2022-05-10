package com.nemo.footballassociation.Contracts.Modules.DbModels;

public class ClubAdministrator extends Subscription{
    private float salary;

    public ClubAdministrator(float salary, String name, String userName, String password) {
        super(name, userName, password);
        this.salary = salary;
    }
}
