package com.nemo.footballassociation.Contracts.Modules.DbModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nemo.footballassociation.Contracts.Enums.RefereeTraining;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Referee extends Subscription {

    @Enumerated(value = EnumType.STRING)
    @JsonProperty("referee_training")
    private RefereeTraining refereeTraining;

    public Referee() {
    }

    public Referee(String name, String userName, String password, RefereeTraining refereeTraining) {
        super(name, userName, password);
        this.refereeTraining = refereeTraining;
    }

    public RefereeTraining getRefereeTraining() {
        return refereeTraining;
    }

    public void setRefereeTraining(RefereeTraining refereeTraining) {
        this.refereeTraining = refereeTraining;
    }
}
