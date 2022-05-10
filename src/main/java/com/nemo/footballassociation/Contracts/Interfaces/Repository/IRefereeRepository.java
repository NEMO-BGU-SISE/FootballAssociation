package com.nemo.footballassociation.Contracts.Interfaces.Repository;

import com.nemo.footballassociation.Contracts.Modules.DbModels.Referee;
import com.nemo.footballassociation.Contracts.Modules.DbModels.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRefereeRepository extends JpaRepository<Referee, Integer> {
    public Referee findByUserName(String userName);
}
