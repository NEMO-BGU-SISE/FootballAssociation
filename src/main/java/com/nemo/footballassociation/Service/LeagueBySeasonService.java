package com.nemo.footballassociation.Service;

import com.nemo.footballassociation.Contracts.Interfaces.Repository.ILeagueBySeasonRepository;
import com.nemo.footballassociation.Contracts.Interfaces.Repository.ILeagueRepository;
import com.nemo.footballassociation.Contracts.Interfaces.Service.ILeagueBySeasonService;
import com.nemo.footballassociation.Contracts.Interfaces.Service.ILeagueService;
import com.nemo.footballassociation.Contracts.Modules.DbModels.League;
import com.nemo.footballassociation.Contracts.Modules.DbModels.LeagueBySeason;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueBySeasonService implements ILeagueBySeasonService {

    private ILeagueBySeasonRepository leagueBySeasonRepository;

    public LeagueBySeasonService(ILeagueBySeasonRepository leagueBySeasonRepository) {
        this.leagueBySeasonRepository = leagueBySeasonRepository;
    }

    @Override
    public LeagueBySeason saveLBS(LeagueBySeason leagueBySeason) {
        leagueBySeason.setId(leagueBySeasonRepository.findMaxId() == null ? 0 : leagueBySeasonRepository.findMaxId() + 1);
        return leagueBySeasonRepository.save(leagueBySeason);
    }

    @Override
    public boolean existsLbsByIds(int leagueId, int seasonId) {
        return leagueBySeasonRepository.existsByLeagueIdAndSeasonId(leagueId, seasonId);
    }

    @Override
    public List<LeagueBySeason> getAllLBS() {
        return leagueBySeasonRepository.findAll();
    }

    @Override
    public LeagueBySeason getLBSByIds(int leagueId, int seasonId) throws Exception {
        return leagueBySeasonRepository.findByLeagueIdAndSeasonId(leagueId, seasonId).orElseThrow(() ->
                new Exception(""));

    }

    public LeagueBySeason updateLBS(LeagueBySeason league, int id) throws Exception {
        if (leagueBySeasonRepository.existsById(id)) {
            try {
                LeagueBySeason lbsToBeUpdate = leagueBySeasonRepository.findById(id);
                lbsToBeUpdate.update(league);
                leagueBySeasonRepository.save(lbsToBeUpdate);
                return lbsToBeUpdate;
            } catch (Exception e) {
                throw e;
            }
        } else {
            return null;
        }
    }
}
