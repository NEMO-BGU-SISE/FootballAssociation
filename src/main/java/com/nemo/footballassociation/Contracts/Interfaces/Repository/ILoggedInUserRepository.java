package com.nemo.footballassociation.Contracts.Interfaces.Repository;

import com.nemo.footballassociation.Contracts.Modules.DbModels.LoggedInUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ILoggedInUserRepository extends JpaRepository<LoggedInUser, Integer> {
    public LoggedInUser findBySubscriptionId(int Id);

    public boolean existsBySubscriptionId(int Id);

    @Query("select max(s.id) from Referee s")
    public Integer findMaxId();

    boolean existsByCode(String code);

    public Optional<LoggedInUser> findByCode(String code);
    Optional<LoggedInUser> deleteByCode(String code);
}
