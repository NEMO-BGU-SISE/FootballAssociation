package com.nemo.footballassociation.Contracts.Modules.DtoModels;

import com.nemo.footballassociation.Contracts.Enums.RefereeTraining;

public class RefereeUpdateDto {
    private String name;
    private String password;
    private RefereeTraining refereeTraining;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RefereeTraining getRefereeTraining() {
        return refereeTraining;
    }

    public void setRefereeTraining(RefereeTraining refereeTraining) {
        this.refereeTraining = refereeTraining;
    }
}
