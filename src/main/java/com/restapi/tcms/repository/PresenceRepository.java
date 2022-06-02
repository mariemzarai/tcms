package com.restapi.tcms.repository;

import com.restapi.tcms.model.Presence;
import com.restapi.tcms.model.Stagiaire;
import com.restapi.tcms.model.StagiaireAbsence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long> {
    @Query("Select p.stagiaire from Presence p where p.seance.id=?1 AND p.absent=1 group by p.stagiaire having count(*) >= 3")
    List<Stagiaire> getEliminatedBySeance(Long id);
    @Query("SELECT new com.restapi.tcms.model.StagiaireAbsence(p.stagiaire.id, count(*)) FROM Presence p where p.seance.id=?1 AND p.absent=1 group by p.stagiaire")
    List<StagiaireAbsence> getNbAbsenceBySeance(Long id);
    @Query("SELECT p FROM Presence p WHERE p.seance.id=?1")
    List<Presence> getAllBySeanceId(Long id);
}
