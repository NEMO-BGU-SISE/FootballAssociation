package com.nemo.footballassociation.Contracts.Interfaces.Service;

import com.nemo.footballassociation.Contracts.Modules.DbModels.LeagueBySeason;

import java.util.List;

public interface ILeagueBySeasonService {
    LeagueBySeason saveLBS(LeagueBySeason leagueBySeason);

    boolean existsLbsByIds(int leagueId, int seasonId);

    List<LeagueBySeason> getAllLBS();

    LeagueBySeason getLBSByIds(int leagueId, int seasonId) throws Exception;

    LeagueBySeason updateLBS(LeagueBySeason league, int id) throws Exception;
}
