package com.nemo.footballassociation.Contracts.Modules.DtoModels;

import com.sun.istack.NotNull;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.annotation.Validated;

import javax.persistence.MapKey;

public class LeagueBySeasonCreateDto {

    private int leagueId;
    private int seasonId;

    private int numOfMatch;

    public int getNumOfMatch() {
        return numOfMatch;
    }

    public void setNumOfMatch(int numOfMatch) {
        this.numOfMatch = numOfMatch;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }
}
