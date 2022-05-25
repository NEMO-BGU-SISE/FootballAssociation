package com.nemo.footballassociation.Contracts.Modules.DbModels;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Game {
    @Id
    private int id;
    private Date dateTime;
    @Nullable
    private int homeScore;
    @Nullable
    private int awayScore;
    @ManyToOne
    private TeamByLeagueBySeason homeTeam;
    @ManyToOne
    private TeamByLeagueBySeason awayTeam;
    @ManyToMany
    private List<Event> events;

    public Game(int id, Date dateTime) {
        this.id = id;
        this.dateTime = dateTime;
        this.events = new ArrayList<>();
    }

    public Game() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public TeamByLeagueBySeason getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(TeamByLeagueBySeason homeTeam) {
        this.homeTeam = homeTeam;
    }

    public TeamByLeagueBySeason getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(TeamByLeagueBySeason awayTeam) {
        this.awayTeam = awayTeam;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void update(Game other) {
        this.setAwayTeam(other.getAwayTeam());
        this.setHomeTeam(other.getHomeTeam());
    }

}
