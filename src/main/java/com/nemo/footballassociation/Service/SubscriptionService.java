package com.nemo.footballassociation.Service;

import com.nemo.footballassociation.DAL.entity.Subscription;
import com.nemo.footballassociation.DAL.repository.ISubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubscriptionService {
    private final ISubscriptionRepository subscriptionRepository;

    public SubscriptionService(ISubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Transactional
    public String create(Subscription subscription) {
        try {
            if (!subscriptionRepository.existsByName(subscription.getName())) {
                subscription.setId(null == subscriptionRepository.findMaxId() ? 0 : subscriptionRepository.findMaxId() + 1);
                subscriptionRepository.save(subscription);
                return "Subscription record created successfully.";
            } else {
                return "Subscription already exists in the database.";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Subscription> read() {
        return subscriptionRepository.findAll();
    }
}
