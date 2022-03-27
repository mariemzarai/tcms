package com.restapi.tcms.repository;

import com.restapi.tcms.model.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialiteRepository extends JpaRepository<Specialite, Integer> {
    Boolean existsByTitre(String titre);
    Optional<Specialite> getSpecialiteByTitre(String titre);
}