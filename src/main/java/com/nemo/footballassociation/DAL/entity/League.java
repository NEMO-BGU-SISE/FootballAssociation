package com.nemo.footballassociation.DAL.entity;

import java.util.ArrayList;

public class League {
    private String name;
    private Season season;
    private ArrayList<Game> allGames;

    public League(String name, Season season) {
        this.name = name;
        this.season = season;
        this.allGames = new ArrayList<>();
    }
}
