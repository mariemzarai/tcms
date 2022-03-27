package com.restapi.tcms.dao;
import com.restapi.tcms.model.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupeRepository extends JpaRepository<Groupe,Long> {
    Boolean existsByNom(String titre);

    Optional<Groupe> getGroupeByNom(String nom);
}