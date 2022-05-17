package com.nemo.footballassociation.Contracts.Interfaces.Service;

import com.nemo.footballassociation.Contracts.Modules.DbModels.Referee;
import com.nemo.footballassociation.Contracts.Modules.DbModels.Season;

import java.util.List;

public interface ISeasonService {
    Season saveSeason(Season season);
    List<Season> getAllSeason();
    Season getSeasonById(int id) throws Exception;
//    Season updateSeason(Season season, int id) throws Exception;
    void deleteSeason(int id) throws Exception;
}
