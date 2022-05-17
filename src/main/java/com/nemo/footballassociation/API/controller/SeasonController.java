package com.nemo.footballassociation.API.controller;

import com.nemo.footballassociation.Contracts.Interfaces.Service.ILoggedInUserService;
import com.nemo.footballassociation.Contracts.Interfaces.Service.IRefereeService;
import com.nemo.footballassociation.Contracts.Interfaces.Service.ISeasonService;
import com.nemo.footballassociation.Contracts.Modules.DbModels.Referee;
import com.nemo.footballassociation.Contracts.Modules.DbModels.Season;
import com.nemo.footballassociation.Service.LoggedInUserService;
import com.nemo.footballassociation.Service.RefereeService;
import com.nemo.footballassociation.Service.SeasonService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("season")
public class SeasonController {

    private ISeasonService seasonService;
    private ILoggedInUserService loggedInUserService;

    public SeasonController(SeasonService seasonService, LoggedInUserService loggedInUserService) {
        super();
        this.seasonService = seasonService;
        this.loggedInUserService = loggedInUserService;
    }

    // build create Season REST API
    @PostMapping
    public ResponseEntity<Season> saveSeason(@RequestHeader("Authorization") String code, @RequestBody Season season) {
        try {
            if (!loggedInUserService.isRepresentativeOfTheAssociation(code)) {
                return new ResponseEntity("You are not authorized for this", HttpStatus.UNAUTHORIZED);
            }

            return new ResponseEntity<>(seasonService.saveSeason(season), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // build get all seasons REST API
    @GetMapping
    public List<Season> getAllSeasons() {
        return seasonService.getAllSeason();
    }

    // build get season by id REST API
    // http://localhost:8080/api/seasons/1
    @GetMapping("{id}")
    public ResponseEntity<Season> getSeasonById(@PathVariable("id") int seasonId) {
        try {
            return new ResponseEntity<>(seasonService.getSeasonById(seasonId), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // build delete season REST API
    // http://localhost:8080/api/seasons/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSeason(@PathVariable("id") int id) {
        try {
            // delete season from DB
            seasonService.deleteSeason(id);
            return new ResponseEntity<>("Season deleted successfully!.", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

