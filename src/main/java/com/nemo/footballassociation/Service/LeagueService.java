package com.nemo.footballassociation.Service;

import com.nemo.footballassociation.Contracts.Interfaces.Repository.ILeagueRepository;
import com.nemo.footballassociation.Contracts.Interfaces.Service.ILeagueService;
import com.nemo.footballassociation.Contracts.Modules.DbModels.League;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueService implements ILeagueService {

    private ILeagueRepository leagueRepository;

    public LeagueService(ILeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @Override
    public League saveLeague(League league) {
        league.setId(leagueRepository.findMaxId() == null ? 0 : leagueRepository.findMaxId() + 1);
        return leagueRepository.save(league);
    }

    @Override
    public List<League> getAllLeague() {
        return leagueRepository.findAll();
    }

    @Override
    public League getLeagueById(int id) throws Exception {
        return leagueRepository.findById(id).orElseThrow(() ->
                new Exception(""));

    }

    @Override
    public boolean existsById(int id) {
        return leagueRepository.existsById(id);
    }

    @Override
    public void deleteLeague(int id) throws Exception {
        // check whether a league exist in a DB or not
        leagueRepository.findById(id).orElseThrow(() ->
                new Exception("League"));
        leagueRepository.deleteById(id);
    }
}
