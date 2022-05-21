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
    private LeagueBySeason league_by_season;

    @OneToMany
    private List<Game> games;

    public TeamByLeagueBySeason(int id, Team team, LeagueBySeason league_by_season, List<Game> games) {
        this.id = id;
        this.team = team;
        this.league_by_season = league_by_season;
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

    public LeagueBySeason getLeague_by_season() {
        return league_by_season;
    }

    public void setLeague_by_season(LeagueBySeason leagueBySeason) {
        this.league_by_season = leagueBySeason;
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
