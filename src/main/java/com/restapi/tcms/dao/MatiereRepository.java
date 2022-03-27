package com.restapi.tcms.dao;

import com.restapi.tcms.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatiereRepository extends JpaRepository<Matiere, Integer> {
    boolean existsByNom(String nom);
}
