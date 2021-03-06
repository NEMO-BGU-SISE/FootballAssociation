package com.nemo.footballassociation.API.controller;

import com.nemo.footballassociation.Contracts.Interfaces.Service.ILoggedInUserService;
import com.nemo.footballassociation.Contracts.Modules.DbModels.Referee;
import com.nemo.footballassociation.Contracts.Interfaces.Service.IRefereeService;
import com.nemo.footballassociation.Contracts.Modules.DtoModels.RefereeInsertDto;
import com.nemo.footballassociation.Contracts.Modules.DtoModels.RefereeUpdateDto;
import com.nemo.footballassociation.Service.LoggedInUserService;
import com.nemo.footballassociation.Service.RefereeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

@RestController
@RequestMapping("referee")
public class RefereeController {

    private IRefereeService refereeService;
    private ILoggedInUserService loggedInUserService;

    public RefereeController(RefereeService refereeService, LoggedInUserService loggedInUserService) {
        super();
        this.refereeService = refereeService;
        this.loggedInUserService = loggedInUserService;
    }

    // build create Referee REST API
    @PostMapping
    public ResponseEntity<Referee> saveReferee(@RequestHeader("Authorization") String code, @RequestBody RefereeInsertDto referee) {
        try {
            if (!loggedInUserService.isRepresentativeOfTheAssociation(code)) {
                return new ResponseEntity("You are not authorized for this", HttpStatus.UNAUTHORIZED);
            } else if (referee.getName().matches(".*[0-9].*")) {
                return new ResponseEntity("Error: Name contains numbers", HttpStatus.BAD_REQUEST);
            } else if (!referee.getUserName().matches("^(.+)@(\\S+)$")) {
                return new ResponseEntity("Error: The username is not a valid Email address", HttpStatus.BAD_REQUEST);
            } else if (!referee.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")) {
                return new ResponseEntity("Error: The password is not secure enough", HttpStatus.BAD_REQUEST);
            } else if (refereeService.existsByUserName(referee.getUserName())) {
                return new ResponseEntity("Error: userName is taken", HttpStatus.BAD_REQUEST);
            }
            String md5Password = DigestUtils.md5Hex(referee.getPassword());
            referee.setPassword(md5Password);
            Referee refereeToSave = new Referee(referee.getName(), referee.getUserName(), referee.getPassword(), referee.getRefereeTraining());
            return new ResponseEntity<>(refereeService.saveReferee(refereeToSave), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
            return new ResponseEntity("Referee Id does not exists", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("userName/{userName}")
    public ResponseEntity<Referee> getRefereeByUserName(@PathVariable("userName") String refereeUserName) {
        try {
//            Referee referee = refereeService.getRefereeByUserName(refereeUserName);
            return new ResponseEntity<Referee>(refereeService.getRefereeByUserName(refereeUserName), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity("Referee user name does not exists", HttpStatus.BAD_REQUEST);
        }
    }

    // build update referee REST API
    // http://localhost:8080/api/referees/1
    @PutMapping("{id}")
    public ResponseEntity<Referee> updateReferee(@RequestHeader("Authorization") String code, @PathVariable("id") int id, @RequestBody RefereeUpdateDto referee) {
        try {
            if (!loggedInUserService.isRepresentativeOfTheAssociation(code)) {
                return new ResponseEntity("You are not authorized for this", HttpStatus.UNAUTHORIZED);
            }
            if (!refereeService.existsById(id)) {
                return new ResponseEntity("referee id doesn't exists", HttpStatus.BAD_REQUEST);
            }
            Referee refereeToUpdate = refereeService.getRefereeById(id);

            refereeToUpdate.setName(referee.getName());
            refereeToUpdate.setPassword(referee.getPassword());
            refereeToUpdate.setRefereeTraining(referee.getRefereeTraining());
            return new ResponseEntity<>(refereeService.updateReferee(refereeToUpdate, id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // build delete referee REST API
    // http://localhost:8080/api/referees/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteReferee(@RequestHeader("Authorization") String code, @PathVariable("id") int id) {
        try {
            if (!loggedInUserService.isRepresentativeOfTheAssociation(code)) {
                return new ResponseEntity("You are not authorized for this", HttpStatus.UNAUTHORIZED);
            }
            // delete referee from DB
            refereeService.deleteReferee(id);
            return new ResponseEntity<String>("Referee deleted successfully!.", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity("Referee doesn't exists!.", HttpStatus.BAD_REQUEST);
        }
    }

}

