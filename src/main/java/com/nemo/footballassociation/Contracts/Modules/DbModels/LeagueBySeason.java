package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.*;
import java.util.List;

@Entity
public class LeagueBySeason {
    @Id
    private int Id;
    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season season;
    @ManyToOne
    @JoinColumn(name = "league_id")
    private League league;
    @OneToMany
    private List<Game> games;
    @OneToMany
    private List<TeamByLeagueBySeason> teams;
    @ManyToOne
    private AssigningPolicy assigningPolicy;

    public LeagueBySeason() {
    }

    public void update(LeagueBySeason other) {
        this.setSeason(other.getSeason());
        this.setLeague(other.getLeague());
        this.setGames(other.getGames());
        this.setTeams(other.getTeams());
        this.setAssigningPolicy(other.getAssigningPolicy());
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public List<TeamByLeagueBySeason> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamByLeagueBySeason> teams) {
        this.teams = teams;
    }

    public AssigningPolicy getAssigningPolicy() {
        return assigningPolicy;
    }

    public void setAssigningPolicy(AssigningPolicy assigningPolicy) {
        this.assigningPolicy = assigningPolicy;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
