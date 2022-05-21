package com.nemo.footballassociation.Contracts.Modules.DbModels;

import com.nemo.footballassociation.Contracts.Enums.RefereeTraining;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Referee extends Subscription {

    @Enumerated(value = EnumType.STRING)
    private RefereeTraining referee_training;

    public Referee() {
    }

    public Referee(String name, String userName, String password, RefereeTraining referee_training) {
        super(name, userName, password);
        this.referee_training = referee_training;
    }


    public RefereeTraining getReferee_training() {
        return referee_training;
    }

    public void setReferee_training(RefereeTraining refereeTraining) {
        this.referee_training = refereeTraining;
    }
}
