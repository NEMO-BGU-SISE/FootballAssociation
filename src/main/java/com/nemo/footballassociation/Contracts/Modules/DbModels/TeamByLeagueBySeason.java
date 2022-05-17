package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class TeamByLeagueBySeason {
    @Id
    private int id;

    @ManyToOne
    private Team team;
    @ManyToOne
    private LeagueBySeason leagueBySeason;

    @OneToMany
    private List<Game> games;

    public TeamByLeagueBySeason(int id, Team team, LeagueBySeason leagueBySeason, List<Game> games) {
        this.id = id;
        this.team = team;
        this.leagueBySeason = leagueBySeason;
        this.games = games;
    }

    public TeamByLeagueBySeason() {

    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public LeagueBySeason getLeagueBySeason() {
        return leagueBySeason;
    }

    public void setLeagueBySeason(LeagueBySeason leagueBySeason) {
        this.leagueBySeason = leagueBySeason;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}
