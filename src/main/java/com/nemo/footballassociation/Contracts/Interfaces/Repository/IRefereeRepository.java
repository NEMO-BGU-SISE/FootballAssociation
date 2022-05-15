package com.nemo.footballassociation.Contracts.Interfaces.Repository;

import com.nemo.footballassociation.Contracts.Modules.DbModels.Referee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRefereeRepository extends JpaRepository<Referee, Integer> {
    public List<Referee> findByUserName(String userName);

    public boolean existsByUserName(String userName);

    @Query("select max(s.id) from Subscription s")
    public Integer findMaxId();
}
