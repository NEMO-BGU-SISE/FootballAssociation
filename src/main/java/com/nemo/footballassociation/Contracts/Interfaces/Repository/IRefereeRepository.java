package com.nemo.footballassociation.Contracts.Interfaces.Repository;

import com.nemo.footballassociation.Contracts.Modules.DbModels.Referee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRefereeRepository extends JpaRepository<Referee, Integer> {
}
