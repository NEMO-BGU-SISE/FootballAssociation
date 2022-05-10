package com.nemo.footballassociation.API.controller;

import com.nemo.footballassociation.Contracts.Modules.DbModels.Referee;
import com.nemo.footballassociation.Contracts.Interfaces.Service.IRefereeService;
import com.nemo.footballassociation.Service.RefereeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/referee")
public class RefereeController {

    private IRefereeService refereeService;

    public RefereeController(RefereeService refereeService) {
        super();
        this.refereeService = refereeService;
    }

    // build create Referee REST API
    @PostMapping
    public ResponseEntity<Referee> saveReferee(@RequestBody Referee referee) {
        return new ResponseEntity<Referee>(refereeService.saveReferee(referee), HttpStatus.CREATED);
    }

    // build get all referees REST API
    @GetMapping
    public List<Referee> getAllReferees() {
        return refereeService.getAllReferee();
    }

    // build get referee by id REST API
    // http://localhost:8080/api/referees/1
    @GetMapping("{id}")
    public ResponseEntity<Referee> getRefereeById(@PathVariable("id") int refereeId) {
        try {
            return new ResponseEntity<Referee>(refereeService.getRefereeById(refereeId), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // build update referee REST API
    // http://localhost:8080/api/referees/1
    @PutMapping("{id}")
    public ResponseEntity<Referee> updateReferee(@PathVariable("id") int id
            , @RequestBody Referee referee) {
        try {
            return new ResponseEntity<Referee>(refereeService.updateReferee(referee, id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // build delete referee REST API
    // http://localhost:8080/api/referees/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteReferee(@PathVariable("id") int id) {

        try {
            // delete referee from DB
            refereeService.deleteReferee(id);
            return new ResponseEntity<String>("Referee deleted successfully!.", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

