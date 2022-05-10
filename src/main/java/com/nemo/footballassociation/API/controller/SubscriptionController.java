package com.nemo.footballassociation.API.controller;

import com.nemo.footballassociation.Contracts.Modules.DbModels.Subscription;
import com.nemo.footballassociation.Service.SubscriptionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "Subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String info(){
        return "The application is up...";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createStudent(@RequestBody Subscription subscription){
        return subscriptionService.create(subscription);
    }

    @RequestMapping(value = "read", method = RequestMethod.GET)
    public List<Subscription> readStudents(){
        return subscriptionService.read();
    }

}
