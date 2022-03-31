package com.restapi.tcms.repository;

import com.restapi.tcms.model.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StagiaireRepository extends JpaRepository<Stagiaire, Long> {
    Optional<Stagiaire> findStagiaireByEmail(String email);
    Boolean existsByEmail(String email);
}
