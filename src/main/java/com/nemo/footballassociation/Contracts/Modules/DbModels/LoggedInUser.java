package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class LoggedInUser {
    @Id
    private int Id;
    @OneToOne
    @JoinColumn(name = "subscriptionId")
    private Subscription subscription;
    private String code;
    private LocalDateTime expiredDateTime;

    public LoggedInUser() {
    }

    public LoggedInUser(int id, Subscription subscription, String code, LocalDateTime expiredDateTime) {
        this.Id = id;
        this.subscription = subscription;
        this.code = code;
        this.expiredDateTime = expiredDateTime;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
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

    public LocalDateTime getExpiredDateTime() {
        return expiredDateTime;
    }

    public void setExpiredDateTime(LocalDateTime expiredDateTime) {
        this.expiredDateTime = expiredDateTime;
    }
}
