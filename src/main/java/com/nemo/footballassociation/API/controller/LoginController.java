package com.nemo.footballassociation.API.controller;

import com.nemo.footballassociation.Contracts.Interfaces.Repository.ISubscriptionRepository;
import com.nemo.footballassociation.Contracts.Interfaces.Service.IRefereeService;
import com.nemo.footballassociation.Contracts.Modules.DbModels.Referee;
import com.nemo.footballassociation.Contracts.Modules.DbModels.Subscription;
import com.nemo.footballassociation.Contracts.Modules.DtoModels.SubscriptionLoginDto;
import com.nemo.footballassociation.Service.RefereeService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("login")
public class LoginController {

    private ISubscriptionRepository subscriptionRepository;

    public LoginController(ISubscriptionRepository subscriptionRepository) {
        super();
        this.subscriptionRepository = subscriptionRepository;
    }

    @PostMapping
    public ResponseEntity<String> subscriptionLogin(@RequestBody SubscriptionLoginDto subscriptionLoginDto) {
        try {
            if (subscriptionRepository.existsByUserName(subscriptionLoginDto.getUserName())) {
                Subscription subscription = subscriptionRepository.findByUserName(subscriptionLoginDto.getUserName());
                if (subscription.getPassword().equals(DigestUtils.md5Hex(subscriptionLoginDto.getPassword()))) {
                    String code = "CODE"; //TODO!!!
                    return new ResponseEntity<>(code, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Invalid userName or Password", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
