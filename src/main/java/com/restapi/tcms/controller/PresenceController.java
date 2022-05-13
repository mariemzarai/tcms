package com.restapi.tcms.controller;

import com.restapi.tcms.dao.PresenceDao;
import com.restapi.tcms.dao.SeanceDao;
import com.restapi.tcms.dao.StagiaireDao;
import com.restapi.tcms.model.Presence;
import com.restapi.tcms.model.Seance;
import com.restapi.tcms.model.Stagiaire;
import com.restapi.tcms.model.StagiaireAbsence;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/presence")
public class PresenceController {
    private final PresenceDao presenceDao;
    private final SeanceDao seanceDao;
    private final StagiaireDao stagiaireDao;

    public PresenceController(PresenceDao presenceDao, SeanceDao seanceDao, StagiaireDao stagiaireDao) {
        this.presenceDao = presenceDao;
        this.seanceDao = seanceDao;
        this.stagiaireDao = stagiaireDao;
    }

    @PostMapping(path = "/ajouterliste/{seanceId}", consumes = "application/json", produces = "application/json")
    public void create(@RequestBody List<StagiairePresence> presenceList, @PathVariable(name = "seanceId") long seanceId){
        Date date = new Date();
        Seance seance = seanceDao.getById(seanceId).get();
        presenceList.forEach(stagiairePresence -> {
            Stagiaire stagiaire = stagiaireDao.getById(stagiairePresence.id).orElseThrow(()-> new EntityNotFoundException("stagiaire not found"));
            Presence presence = new Presence(seance, date, stagiaire, stagiairePresence.absent);
            presenceDao.create(presence);
        });
    }

    @GetMapping(path = "/elimination/{seanceId}")
    public List<Stagiaire> getElimineBySeance(@PathVariable(name = "seanceId") Long seanceId){

        return presenceDao.getListeElimineBySeance(seanceId);
    }
    @GetMapping(path = "/absences/{seanceId}")
    public List<StagiaireAbsence> getNbAbsencesBySeance(@PathVariable(name = "seanceId") Long seanceId){

        return presenceDao.getNbAbsencesBySeance(seanceId);
    }

    private static class StagiairePresence {
        long id;
        boolean absent;

        public StagiairePresence(long id, boolean absent) {
            this.id = id;
            this.absent = absent;
        }
    }
}
