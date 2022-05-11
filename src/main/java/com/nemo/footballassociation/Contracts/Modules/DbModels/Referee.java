package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.Entity;

@Entity
public class Referee extends Subscription {
    private String refereeTraining;

    public Referee() {
    }

    public Referee(String name, String userName, String password, String refereeTraining) {
        super(name, userName, password);
        this.refereeTraining = refereeTraining;
    }


    public String getRefereeTraining() {
        return refereeTraining;
    }

    public void setRefereeTraining(String refereeTraining) {
        this.refereeTraining = refereeTraining;
    }
}
