package com.nemo.footballassociation.Contracts.Interfaces.Repository;

import com.nemo.footballassociation.Contracts.Modules.DbModels.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITeamRepository extends JpaRepository<Team, Integer> {
    public List<Team> findById(int id);

    public boolean existsById(int id);

    @Query("select max(t.id) from Team t")
    public Integer findMaxId();
}
