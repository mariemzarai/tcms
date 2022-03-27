package com.restapi.tcms.repository;

import com.restapi.tcms.model.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormateurRepository extends JpaRepository<Formateur,Long>{
    Optional<Formateur> findFormateurByNom(String nom);
    Boolean existsByNom(String Nom);
   // Optional<Formateur> findformateurByEmail(String email);
    Boolean existsByEmail(String email);
}
