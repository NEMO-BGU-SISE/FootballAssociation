package com.nemo.footballassociation.API.controller;

import com.nemo.footballassociation.Contracts.Interfaces.Service.ILoggedInUserService;
import com.nemo.footballassociation.Contracts.Interfaces.Service.ILeagueService;
import com.nemo.footballassociation.Contracts.Modules.DbModels.League;
import com.nemo.footballassociation.Service.LoggedInUserService;
import com.nemo.footballassociation.Service.LeagueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("league")
public class LeagueController {

    private ILeagueService leagueService;
    private ILoggedInUserService loggedInUserService;

    public LeagueController(LeagueService leagueService, LoggedInUserService loggedInUserService) {
        super();
        this.leagueService = leagueService;
        this.loggedInUserService = loggedInUserService;
    }

    // build create League REST API
    @PostMapping
    public ResponseEntity<League> saveLeague(@RequestHeader("Authorization") String code, @RequestBody League league) {
        try {
            if (!loggedInUserService.isRepresentativeOfTheAssociation(code)) {
                return new ResponseEntity("You are not authorized for this", HttpStatus.UNAUTHORIZED);
            }

            return new ResponseEntity<>(leagueService.saveLeague(league), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // build get all leagues REST API
    @GetMapping
    public List<League> getAllLeagues() {
        return leagueService.getAllLeague();
    }

    // build get league by id REST API
    // http://localhost:8080/api/leagues/1
    @GetMapping("{id}")
    public ResponseEntity<League> getLeagueById(@PathVariable("id") int leagueId) {
        try {
            return new ResponseEntity<>(leagueService.getLeagueById(leagueId), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // build delete league REST API
    // http://localhost:8080/api/leagues/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteLeague(@PathVariable("id") int id) {
        try {
            // delete league from DB
            leagueService.deleteLeague(id);
            return new ResponseEntity<>("League deleted successfully!.", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

