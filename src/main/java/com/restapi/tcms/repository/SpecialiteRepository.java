package com.restapi.tcms.repository;

import com.restapi.tcms.model.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SpecialiteRepository extends JpaRepository<Specialite, Long> {
    Boolean existsByTitre(String titre);
    Optional<Specialite> getSpecialiteByTitre(String titre);
}