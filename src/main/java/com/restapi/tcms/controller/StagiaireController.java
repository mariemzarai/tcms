package com.restapi.tcms.controller;

import com.restapi.tcms.dao.StagiaireDao;
import com.restapi.tcms.model.Stagiaire;
import com.restapi.tcms.service.ServiceAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/stagiaires")
public class StagiaireController {

    private final StagiaireDao stagiaireDao;
    private final ServiceAuth serviceAuth;

    @Autowired
    public StagiaireController(StagiaireDao stagiaireDao, ServiceAuth serviceAuth) {
        this.stagiaireDao = stagiaireDao;
        this.serviceAuth = serviceAuth;
    }

    @PostMapping(path = "/ajouter", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> create(@RequestBody Stagiaire stagiaire){
        try {
            Optional<Stagiaire> stagiaire1 = stagiaireDao.create(stagiaire);
            stagiaire1.ifPresent(serviceAuth::createIdentityAccount);
            return new ResponseEntity<>(stagiaire1, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @GetMapping(path = "/tous")
    public List<Stagiaire> getAll(){
        return stagiaireDao.getAll();
    }
    @GetMapping(path = "/stat")
    public long getNumberOfStagiaires(){
        return stagiaireDao.countAll();
    }

    @DeleteMapping(path = "/supprimer/{id}")
    public  ResponseEntity<String> delete(@PathVariable("id") Long id){
        try {
            stagiaireDao.delete(id);
            return  ResponseEntity.ok("{\"message\":\"Deleted successfully\"}");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Entity not found");
        }
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public  ResponseEntity<Stagiaire> getStagiaire(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(stagiaireDao.getById(id).orElseThrow(()->new EntityNotFoundException("stagiare d'"+id+"n'existe pas")));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/modifier/{id}")
    public ResponseEntity<Stagiaire> updateStagiaire(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String prenom,
            @RequestParam(required = false) Byte sexe,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String num_tel,
            @RequestParam(required = false) String nom_parent,
            @RequestParam(required = false) String num_tel_parent,
            @RequestParam(required = false) String adresse_postale){
        try {
            return ResponseEntity.ok(stagiaireDao.update(id, nom, prenom, sexe, email, num_tel, nom_parent, num_tel_parent, adresse_postale));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
