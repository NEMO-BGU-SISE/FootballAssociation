package com.nemo.footballassociation.Service;

import com.nemo.footballassociation.Contracts.Interfaces.Repository.ISeasonRepository;
import com.nemo.footballassociation.Contracts.Interfaces.Service.ISeasonService;
import com.nemo.footballassociation.Contracts.Interfaces.Service.ISeasonService;
import com.nemo.footballassociation.Contracts.Modules.DbModels.Season;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonService implements ISeasonService {

    private ISeasonRepository seasonRepository;

    public SeasonService(ISeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    @Override
    public Season saveSeason(Season season) {
        season.setId(seasonRepository.findMaxId() == null ? 0 : seasonRepository.findMaxId() + 1);
        return seasonRepository.save(season);
    }

    @Override
    public List<Season> getAllSeason() {
        return seasonRepository.findAll();
    }

    @Override
    public Season getSeasonById(int id) throws Exception {
        return seasonRepository.findById(id).orElseThrow(() ->
                new Exception(""));

    }

    @Override
    public boolean existsById(int id) {
        return seasonRepository.existsById(id);
    }

    @Override
    public void deleteSeason(int id) throws Exception {
        // check whether a season exist in a DB or not
        seasonRepository.findById(id).orElseThrow(() ->
                new Exception("Season"));
        seasonRepository.deleteById(id);
    }
}
