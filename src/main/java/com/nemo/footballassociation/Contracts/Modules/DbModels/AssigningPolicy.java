package com.nemo.footballassociation.Contracts.Modules.DbModels;

public class AssigningPolicy extends APolicy{
    private int numOfMatchesBetweenTeamsInSeason;

    public AssigningPolicy(int numOfMatches) {
        this.numOfMatchesBetweenTeamsInSeason = numOfMatches;
    }
}
