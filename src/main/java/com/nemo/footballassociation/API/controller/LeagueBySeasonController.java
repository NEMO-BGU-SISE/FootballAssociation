package com.nemo.footballassociation.API.controller;

import com.nemo.footballassociation.Contracts.Interfaces.Service.ILeagueBySeasonService;
import com.nemo.footballassociation.Contracts.Interfaces.Service.ILoggedInUserService;
import com.nemo.footballassociation.Contracts.Modules.DbModels.LeagueBySeason;
import com.nemo.footballassociation.Service.LeagueBySeasonService;
import com.nemo.footballassociation.Service.LoggedInUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("leagueBySeason")
public class LeagueBySeasonController {

    private ILeagueBySeasonService leagueBySeasonService;
    private ILoggedInUserService loggedInUserService;

    public LeagueBySeasonController(LeagueBySeasonService leagueBySeasonService, LoggedInUserService loggedInUserService) {
        super();
        this.leagueBySeasonService = leagueBySeasonService;
        this.loggedInUserService = loggedInUserService;
    }

    // build create LeagueBySeason REST API
    @PostMapping("{league_id, season_id}")
    public ResponseEntity<LeagueBySeason> saveLeagueBySeason(@RequestHeader("Authorization") String code, @PathVariable("league_id") int leagueId, @PathVariable("season_id") int seasonId) {
        try {
            if (!loggedInUserService.isRepresentativeOfTheAssociation(code)) {
                return new ResponseEntity("You are not authorized for this", HttpStatus.UNAUTHORIZED);
            }
            if (!leagueBySeasonService.existsLbsByIds(leagueId, seasonId)) {
                return new ResponseEntity("invalid couple of league and season ids", HttpStatus.BAD_REQUEST);
            }
            LeagueBySeason leagueBySeason = leagueBySeasonService.getLBSByIds(leagueId, seasonId);
            leagueBySeason.AssigningGames();
            return new ResponseEntity<>(leagueBySeasonService.saveLBS(leagueBySeason), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
