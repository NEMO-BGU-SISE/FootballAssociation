package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Event {

    @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
