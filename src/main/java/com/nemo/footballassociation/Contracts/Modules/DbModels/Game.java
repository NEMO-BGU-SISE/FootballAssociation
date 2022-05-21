package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Game {
    @Id
    private int id;
    private Date date_time;
    private int home_score;
    private int away_score;
    @ManyToOne
    private TeamByLeagueBySeason home_team;
    @ManyToOne
    private TeamByLeagueBySeason away_team;
    @ManyToMany
    private List<Event> events;

    public Game(int id, Date date_time, List<Event> events) {
        this.id = id;
        this.date_time = date_time;
        this.events = events;
    }

    public Game() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public int getHome_score() {
        return home_score;
    }

    public void setHome_score(int homeScore) {
        this.home_score = homeScore;
    }

    public int getAway_score() {
        return away_score;
    }

    public void setAway_score(int awayScore) {
        this.away_score = awayScore;
    }

    public TeamByLeagueBySeason getHome_team() {
        return home_team;
    }

    public void setHome_team(TeamByLeagueBySeason homeTeam) {
        this.home_team = homeTeam;
    }

    public TeamByLeagueBySeason getAway_team() {
        return away_team;
    }

    public void setAway_team(TeamByLeagueBySeason awayTeam) {
        this.away_team = awayTeam;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

}
