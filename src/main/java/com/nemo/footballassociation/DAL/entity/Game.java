package com.nemo.footballassociation.DAL.entity;

import java.util.Date;

public class Game {
    private static int idGenerator = 0;
    private int id;
    private Date dateTime;
    private String field;
    private int[] score;
    private Team homeTeam;
    private Team awayTeam;
    private League league;
    private Season season;

    public Game(Date dateTime, League league, Season season) {
        this.id = idGenerator++;
        this.dateTime = dateTime;
        this.score = new int[2];
        this.league = league;
        this.season = season;
    }


    public void setField(String field) {
        this.field = field;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }
}
