package com.nemo.footballassociation.Contracts.Modules.DtoModels;

import com.nemo.footballassociation.Contracts.Enums.RefereeTraining;
import com.nemo.footballassociation.Contracts.Modules.DbModels.Event;
import com.nemo.footballassociation.Contracts.Modules.DbModels.TeamByLeagueBySeason;

import java.util.Date;
import java.util.List;

public class GameUpdateDto {
    private Date dateTime;
    private int homeScore;
    private int awayScore;
    private TeamByLeagueBySeason homeTeam;
    private TeamByLeagueBySeason awayTeam;
    private List<Event> events;

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
}
