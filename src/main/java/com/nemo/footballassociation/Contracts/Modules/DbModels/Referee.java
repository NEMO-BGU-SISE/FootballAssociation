package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.Entity;

@Entity
public class Referee extends Subscription {
    public Referee(String name, String userName, String password) {
        super(name, userName, password);
    }

    public Referee() {
        super();
    }
}
