package com.nemo.footballassociation.Contracts.Interfaces.Repository;

import com.nemo.footballassociation.Contracts.Modules.DbModels.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILeagueRepository extends JpaRepository<League, Integer> {
    public Optional<League> findById(int Id);

    public boolean existsById(int Id);

    @Query("select max(s.Id) from League s")
    public Integer findMaxId();
}
