package com.nemo.footballassociation.DAL.entity;

import java.util.Date;

public class Player extends Subscription{
    private Date birthDate;
    private Position position;

    public Player(String name, String userName, String password, Date birthDate, Position position) {
        super(name, userName, password);
    }
}
