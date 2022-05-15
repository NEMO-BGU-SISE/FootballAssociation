package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.Entity;

@Entity
public class RepresentativeOfTheAssociation extends Subscription {
    public RepresentativeOfTheAssociation() {
    }
    public RepresentativeOfTheAssociation(String name, String userName, String password) {
        super(name, userName, password);
    }
    
}
