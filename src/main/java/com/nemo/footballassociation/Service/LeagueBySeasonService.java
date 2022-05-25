package com.nemo.footballassociation.Service;

import com.nemo.footballassociation.Contracts.Interfaces.Repository.ILeagueBySeasonRepository;
import com.nemo.footballassociation.Contracts.Interfaces.Service.ILeagueBySeasonService;
import com.nemo.footballassociation.Contracts.Modules.DbModels.LeagueBySeason;
import com.nemo.footballassociation.Contracts.Modules.DbModels.Referee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LeagueBySeasonService implements ILeagueBySeasonService {

    private final ILeagueBySeasonRepository leagueBySeasonRepository;

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

    @Override
    @Transactional
    public LeagueBySeason updateLBS(LeagueBySeason lbs, int id) throws Exception {
        LeagueBySeason existingLBS = leagueBySeasonRepository.findById(id).orElseThrow(
                () -> new Exception(""));

        existingLBS.update(lbs);
        leagueBySeasonRepository.save(existingLBS);
        return existingLBS;
    }

    @Override
    @Transactional
    public boolean assignGames(int leagueId, int seasonId) throws Exception {
        LeagueBySeason leagueBySeason = getLBSByIds(leagueId, seasonId);
        return leagueBySeason.AssigningGames();
    }
}
