package com.restapi.tcms.repository;

import com.restapi.tcms.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Integer> {
    boolean existsByNom(String nom);
}
