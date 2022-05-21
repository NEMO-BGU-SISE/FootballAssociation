package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class LoggedInUser {
    @Id
    private int id;
    @OneToOne
    @JoinColumn(name = "subscriptionId")
    private Subscription subscription;
    private String code;
    private LocalDateTime expired_date_time;

    public LoggedInUser() {
    }

    public LoggedInUser(int id, Subscription subscription, String code, LocalDateTime expired_date_time) {
        this.id = id;
        this.subscription = subscription;
        this.code = code;
        this.expired_date_time = expired_date_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpired_date_time() {
        return expired_date_time;
    }

    public void setExpired_date_time(LocalDateTime expiredDateTime) {
        this.expired_date_time = expiredDateTime;
    }
}
