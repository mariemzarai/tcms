package com.restapi.tcms.controller;

import com.restapi.tcms.dao.SpecialiteDao;
import com.restapi.tcms.model.Matiere;
import com.restapi.tcms.model.Specialite;
import com.restapi.tcms.model.Stagiaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/specialite")
public class SpecialiteController {
    private final SpecialiteDao specialiteDao;

    @Autowired
    public SpecialiteController(SpecialiteDao specialiteDao) {
        this.specialiteDao = specialiteDao;
    }

    @PostMapping(path = "/ajouter", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> create(@RequestBody Specialite specialite){
        try {
            return new ResponseEntity<>(specialiteDao.create(specialite), HttpStatus.CREATED);
        }
        catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @GetMapping(path = "/tous")
    public List<Specialite> getAll(){
        return specialiteDao.getAll();
    }

    @DeleteMapping(path = "/supprimer/{id}")
    public  ResponseEntity<String> deleteSspecialite(@PathVariable("id") Long id){
        try {
            specialiteDao.delete(id);
            return  ResponseEntity.ok("Deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Entity not found");
        }
    }

    @GetMapping(path = "/{id}")
    public  ResponseEntity<Specialite> getSpecialite(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(specialiteDao.getById(id).orElseThrow(() -> new EntityNotFoundException("specialite avec id " + id + " n'existe pas.\n")));
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


    @GetMapping(path = "/{id}/stagiaires")
    public List<Stagiaire> getStagiaires(@PathVariable("id") Long id){
       Specialite specialite = specialiteDao.getById(id);
        return  specialite.getListeStagiaires();
    }

    @GetMapping(path = "/{id}/matieres")
    public List<Matiere> getMatieres(@PathVariable("id") Long id){
        Specialite specialite = specialiteDao.getById(id);
        return  specialite.getListeMatieres();
    }
}
