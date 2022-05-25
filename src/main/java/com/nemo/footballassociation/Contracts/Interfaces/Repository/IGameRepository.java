package com.nemo.footballassociation.Contracts.Interfaces.Repository;

import com.nemo.footballassociation.Contracts.Modules.DbModels.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IGameRepository extends JpaRepository<Game, Integer> {
    public Optional<Game> findById(int gameId);

    public boolean existsById(int id);

    @Query("select max(g.id) from Game g")
    public Integer findMaxId();

//    @Modifying
//    @Query("update Game g set g.home_team_id = :home_team_id, g.away_team_id = :away_team_id WHERE g.id = :id")
//    void updateGame(@Param("id") int id, @Param("home_team_id") int home_team_id, @Param("away_team_id") int away_team_id);
}
