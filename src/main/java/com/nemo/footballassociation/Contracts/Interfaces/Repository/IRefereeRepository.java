package com.nemo.footballassociation.Contracts.Interfaces.Repository;

import com.nemo.footballassociation.Contracts.Modules.DbModels.Referee;
import com.nemo.footballassociation.Contracts.Modules.DbModels.Subscription;
import com.nemo.footballassociation.Contracts.Modules.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRefereeRepository extends JpaRepository<Referee, Integer> {
    public List<Referee> findByUserName(String userName);

    public boolean existsByUserName(String userName);

    @Query("select max(s.id) from Referee s")
    public Integer findMaxId();
}
