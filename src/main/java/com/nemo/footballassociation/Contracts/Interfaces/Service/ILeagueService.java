package com.nemo.footballassociation.Contracts.Interfaces.Service;

import com.nemo.footballassociation.Contracts.Modules.DbModels.League;

import java.util.List;

public interface ILeagueService {
    League saveLeague(League league);
    List<League> getAllLeague();
    League getLeagueById(int id) throws Exception;
//    League updateLeague(League league, int id) throws Exception;
    void deleteLeague(int id) throws Exception;
}
