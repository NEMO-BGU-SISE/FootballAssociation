package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AssigningPolicy extends APolicy{
    @Id
    private int id;
    private int numOfMatchesBetweenTeamsInSeason;

    public AssigningPolicy(int numOfMatches) throws Exception {
        if(numOfMatches != 1 && numOfMatches != 2){
            throw new Exception();
        }
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
