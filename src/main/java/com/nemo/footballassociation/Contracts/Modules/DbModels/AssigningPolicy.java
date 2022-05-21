package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AssigningPolicy extends APolicy{
    @Id
    private int id;
    private int num_of_matches_between_teams_in_season;

    public AssigningPolicy(int numOfMatches) throws Exception {
        if(numOfMatches != 1 && numOfMatches != 2){
            throw new Exception();
        }
        this.num_of_matches_between_teams_in_season = numOfMatches;
    }

    public AssigningPolicy() {

    }

    public int getNumOfMatchesBetweenTeamsInSeason() {
        return num_of_matches_between_teams_in_season;
    }

    public void setNumOfMatchesBetweenTeamsInSeason(int numOfMatchesBetweenTeamsInSeason) {
        this.num_of_matches_between_teams_in_season = numOfMatchesBetweenTeamsInSeason;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    public int getId() {
        return id;
    }
}
