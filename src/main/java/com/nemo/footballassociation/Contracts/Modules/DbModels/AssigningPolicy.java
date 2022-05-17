package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AssigningPolicy extends APolicy{
    @Id
    private int id;
    private int numOfMatchesBetweenTeamsInSeason;

    public AssigningPolicy(int numOfMatches) {
        this.numOfMatchesBetweenTeamsInSeason = numOfMatches;
    }

    public AssigningPolicy() {

    }

    public int getNumOfMatchesBetweenTeamsInSeason() {
        return numOfMatchesBetweenTeamsInSeason;
    }

    public void setNumOfMatchesBetweenTeamsInSeason(int numOfMatchesBetweenTeamsInSeason) {
        this.numOfMatchesBetweenTeamsInSeason = numOfMatchesBetweenTeamsInSeason;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    public int getId() {
        return id;
    }
}
