package com.nemo.footballassociation.Contracts.Modules.DbModels;

public class Coach extends Subscription{
    private float salary;
    private String training;
    private String role;

    public Coach(float salary, String training, String role, String name, String userName, String password) {
        super(name, userName, password);
        this.salary = salary;
        this.training = training;
        this.role = role;
    }
}
