package com.nemo.footballassociation.Contracts.Interfaces.Repository;

import com.nemo.footballassociation.Contracts.Modules.DbModels.RepresentativeOfTheAssociation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRepresentativeOfTheAssociationRepository extends JpaRepository<RepresentativeOfTheAssociation, Integer> {
    public Optional<RepresentativeOfTheAssociation> findById(int Id);

    public boolean existsById(int Id);
}
