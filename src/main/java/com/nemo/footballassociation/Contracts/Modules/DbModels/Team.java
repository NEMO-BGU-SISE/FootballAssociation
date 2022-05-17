package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Team {
    @Id
    private int id;
    private String name;


}
