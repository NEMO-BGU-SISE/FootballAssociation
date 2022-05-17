package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class League {
    @Id
    private int Id;

    private String name;

    public League() {
    }

    public League(String name) {
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
