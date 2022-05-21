package com.nemo.footballassociation.API.controller;

import com.nemo.footballassociation.Contracts.Interfaces.Service.ILeagueBySeasonService;
import com.nemo.footballassociation.Contracts.Interfaces.Service.ILeagueService;
import com.nemo.footballassociation.Contracts.Interfaces.Service.ILoggedInUserService;
import com.nemo.footballassociation.Contracts.Interfaces.Service.ISeasonService;
import com.nemo.footballassociation.Contracts.Modules.DbModels.League;
import com.nemo.footballassociation.Contracts.Modules.DbModels.LeagueBySeason;
import com.nemo.footballassociation.Contracts.Modules.DbModels.Season;
import com.nemo.footballassociation.Contracts.Modules.DtoModels.LeagueBySeasonCreateDto;
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
    private ILeagueService leagueService;
    private ISeasonService seasonService;
    private ILoggedInUserService loggedInUserService;

    public LeagueBySeasonController(LeagueBySeasonService leagueBySeasonService, LoggedInUserService loggedInUserService, ILeagueService leagueService, ISeasonService seasonService) {
        super();
        this.leagueBySeasonService = leagueBySeasonService;
        this.loggedInUserService = loggedInUserService;
        this.leagueService = leagueService;
        this.seasonService = seasonService;
    }

    @GetMapping
    public List<LeagueBySeason> getAll() {
        return leagueBySeasonService.getAllLBS();
    }

    // build create LeagueBySeason REST API
    @PostMapping("/assigningGames")
    public ResponseEntity<LeagueBySeason> assigningGames(@RequestHeader("Authorization") String code, @RequestParam("league_id") int leagueId, @RequestParam("season_id") int seasonId) {
        try {
            if (!loggedInUserService.isRepresentativeOfTheAssociation(code)) {
                return new ResponseEntity("You are not authorized for this", HttpStatus.UNAUTHORIZED);
            }
            if (!leagueBySeasonService.existsLbsByIds(leagueId, seasonId)) {
                return new ResponseEntity("invalid couple of league and season ids", HttpStatus.BAD_REQUEST);
            }
            LeagueBySeason leagueBySeason = leagueBySeasonService.getLBSByIds(leagueId, seasonId);
            if (leagueBySeason.AssigningGames()) {
                return new ResponseEntity<>(leagueBySeasonService.saveLBS(leagueBySeason), HttpStatus.CREATED);
            } else {
                return new ResponseEntity("Can't assign games", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<LeagueBySeason> create(@RequestHeader("Authorization") String code, @RequestBody LeagueBySeasonCreateDto lbs) {
        try {
            if (!loggedInUserService.isRepresentativeOfTheAssociation(code)) {
                return new ResponseEntity("You are not authorized for this", HttpStatus.UNAUTHORIZED);
            }
            if (leagueBySeasonService.existsLbsByIds(lbs.getLeagueId(), lbs.getSeasonId())) {
                return new ResponseEntity("Already exists", HttpStatus.BAD_REQUEST);
            }
            if (!leagueService.existsById(lbs.getLeagueId())) {
                return new ResponseEntity("League don't exists", HttpStatus.BAD_REQUEST);
            }
            if (!seasonService.existsById(lbs.getSeasonId())) {
                return new ResponseEntity("Season don't exists", HttpStatus.BAD_REQUEST);
            }

            League league = leagueService.getLeagueById(lbs.getLeagueId());
            Season season = seasonService.getSeasonById(lbs.getSeasonId());
            LeagueBySeason leagueBySeason = new LeagueBySeason(season, league, lbs.getNumOfMatch());
            return new ResponseEntity(leagueBySeasonService.saveLBS(leagueBySeason), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
