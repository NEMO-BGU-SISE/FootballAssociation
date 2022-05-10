package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.Entity;

@Entity
public class Referee extends Subscription {
    private String refereeTraining;

    public Referee(String name, String userName, String password, String refereeTraining) {
        super(name, userName, password);
        this.refereeTraining = refereeTraining;
    }

    public Referee() {
    }
}
