package com.nemo.footballassociation.Contracts.Interfaces.Repository;

import com.nemo.footballassociation.Contracts.Modules.DbModels.LeagueBySeason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILeagueBySeasonRepository extends JpaRepository<LeagueBySeason, Integer> {
    public Optional<LeagueBySeason> findByLeagueIdAndSeasonId(int leagueId, int seasonId);

    public boolean existsByLeagueIdAndSeasonId(int leagueId, int seasonId);

    @Query("select max(s.id) from LeagueBySeason s")
    public Integer findMaxId();
}
