package com.restapi.tcms.repository;

import com.restapi.tcms.model.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {
    public List<Seance> findByGroupe(Long groupId);
    public List<Seance> findByMatiere(Long matiereId);
    public List<Seance> findByFormateur(Long formateurId);
    public List<Seance> findByGroupeAndMatiere(Long groupe, Long matiere);

}
