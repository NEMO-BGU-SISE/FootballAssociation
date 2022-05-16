package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class League {
    @Id
    private int Id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season season;

    @ManyToMany
    @JoinColumn(name = "games_id")
    private List<Game> games;

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public League() {
    }

    public League(String name, Season season) {
        this.name = name;
        this.season = season;
        this.games = new ArrayList<>();
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getAllGames() {
        return games;
    }

    public void setAllGames(List<Game> allGames) {
        this.games = allGames;
    }
}
