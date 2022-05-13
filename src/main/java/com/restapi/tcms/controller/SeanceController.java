package com.restapi.tcms.controller;

import com.restapi.tcms.dao.SeanceDao;
import com.restapi.tcms.model.Seance;
import com.restapi.tcms.model.Stagiaire;
import com.restapi.tcms.service.ServiceAbsence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(path = "/seance")
public class SeanceController {
    private final SeanceDao seanceDao;
    private final ServiceAbsence serviceAbsence;

    @Autowired
    public SeanceController(SeanceDao seanceDao, ServiceAbsence serviceAbsence) {
        this.seanceDao = seanceDao;
        this.serviceAbsence = serviceAbsence;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Seance> get(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(seanceDao.getById(id).orElseThrow(()-> new EntityNotFoundException(" Seance n'esxite pas d'id"+id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}/stagiaires")
    public List<Stagiaire> getStagiaires(@PathVariable("id") Long id){
        return serviceAbsence.getStagiairesFromSeance(id);
    }

    @PostMapping(path = "/ajouter", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> create(@RequestBody Seance seance){
        try {
            return new ResponseEntity<>(seanceDao.create(seance), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @GetMapping(path = "/tous")
    public List<Seance> getAll(){
        return seanceDao.getAll();
    }

    @DeleteMapping(path = "/supprimer/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        try {
            seanceDao.delete(id);
            return  ResponseEntity.ok("{\"message\":\"Deleted successfully\"}");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Entity not found");
        }
    }

    public List<Seance> getAllByFormateur(Long idFormateur){
        return seanceDao.getAllByFormateur(idFormateur);
    }

//    public List<Seance> getAllByMatiere(Long idMatiere){
//        return seanceDao.getAllByMatiere(idMatiere);
//    }
//    @GetMapping(path = "/")
//    public List<Seance> getAllByGroupe(Long idGroupe){
//        return seanceDao.getAllByGroupe(idGroupe);
//    }

    @GetMapping(path = "/")
    public List<Seance> getAllByGroupeAndMatiere(
            @PathVariable Long groupeId,
            @PathVariable Long matiereId){
        if(groupeId == null && matiereId != null)
            return seanceDao.getAllByMatiere(matiereId);
        else if (matiereId == null && groupeId != null)
            return seanceDao.getAllByGroupe(groupeId);
        else
            return seanceDao.getAllByGroupeAndMatiere(groupeId, matiereId);
    }

}
