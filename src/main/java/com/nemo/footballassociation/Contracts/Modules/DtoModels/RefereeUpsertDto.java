package com.nemo.footballassociation.Contracts.Modules.DtoModels;

import com.nemo.footballassociation.Contracts.Enums.RefereeTraining;

public class RefereeUpsertDto {
    private String name;
    private String userName;
    private String password;
    private RefereeTraining refereeTraining;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
