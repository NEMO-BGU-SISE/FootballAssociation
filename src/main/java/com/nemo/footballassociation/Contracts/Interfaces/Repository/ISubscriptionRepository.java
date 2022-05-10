package com.nemo.footballassociation.Contracts.Interfaces.Repository;

import com.nemo.footballassociation.Contracts.Modules.DbModels.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISubscriptionRepository extends JpaRepository<Subscription, Integer> {

    public boolean existsByName(String name);

    public List<Subscription> findByName(String name);

    @Query("select max(s.id) from Subscription s")
    public Integer findMaxId();
}
