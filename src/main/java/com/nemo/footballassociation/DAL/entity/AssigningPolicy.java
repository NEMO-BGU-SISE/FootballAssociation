package com.nemo.footballassociation.DAL.entity;

public class AssigningPolicy extends APolicy{
    private int numOfMatchesBetweenTeamsInSeason;

    public AssigningPolicy(int numOfMatches) {
        this.numOfMatchesBetweenTeamsInSeason = numOfMatches;
    }
}
