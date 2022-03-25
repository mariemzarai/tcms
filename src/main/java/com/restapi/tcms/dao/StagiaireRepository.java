package com.restapi.tcms.dao;

import com.restapi.tcms.model.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StagiaireRepository extends JpaRepository<Stagiaire, Long> {
    Optional<Stagiaire> findStagiaireByEmail(String email);
    Boolean existsByEmail(String email);
}
