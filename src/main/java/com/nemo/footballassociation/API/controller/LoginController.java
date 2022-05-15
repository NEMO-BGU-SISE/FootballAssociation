package com.nemo.footballassociation.API.controller;

import com.nemo.footballassociation.Contracts.Interfaces.Repository.ILoggedInUserRepository;
import com.nemo.footballassociation.Contracts.Interfaces.Repository.ISubscriptionRepository;
import com.nemo.footballassociation.Contracts.Interfaces.Service.ILoggedInUserService;
import com.nemo.footballassociation.Contracts.Modules.DbModels.LoggedInUser;
import com.nemo.footballassociation.Contracts.Modules.DbModels.Subscription;
import com.nemo.footballassociation.Contracts.Modules.DtoModels.SubscriptionLoginDto;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("login")
public class LoginController {

    private ISubscriptionRepository subscriptionRepository;
    private ILoggedInUserService loggedInUserService;

    public LoginController(ISubscriptionRepository subscriptionRepository, ILoggedInUserService loggedInUserService) {
        super();
        this.subscriptionRepository = subscriptionRepository;
        this.loggedInUserService = loggedInUserService;
    }

    @PostMapping
    public ResponseEntity<String> subscriptionLogin(@RequestBody SubscriptionLoginDto subscriptionLoginDto) {
        try {
            if (subscriptionRepository.existsByUserName(subscriptionLoginDto.getUserName())) {
                Subscription subscription = subscriptionRepository.findByUserName(subscriptionLoginDto.getUserName());
                if (subscription.getPassword().equals(DigestUtils.md5Hex(subscriptionLoginDto.getPassword()))) {
                    String code = "MyOwnCode-" + subscription.getUserName(); //TODO!!!
                    LoggedInUser user = new LoggedInUser(0, subscription, code, LocalDateTime.now().plusDays(7));
                    loggedInUserService.saveLoggedInUser(user);
                    return new ResponseEntity<>(code, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Invalid userName or Password", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> subscriptionLogout(@RequestBody String code) {
        try {
            if (loggedInUserService.existsByCode(code)) {
                loggedInUserService.deleteByCode(code);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>("Invalid userName or Password", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
