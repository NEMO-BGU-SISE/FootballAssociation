package com.nemo.footballassociation.Contracts.Interfaces.Repository;

import com.nemo.footballassociation.Contracts.Modules.DbModels.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISeasonRepository extends JpaRepository<Season, Integer> {
    public Optional<Season> findById(int Id);

    public boolean existsById(int Id);

    @Query("select max(s.id) from Season s")
    public Integer findMaxId();
}
