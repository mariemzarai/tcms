package com.restapi.tcms.controller;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.restapi.tcms.dao.GroupeDao;
import com.restapi.tcms.model.Groupe;
import com.restapi.tcms.model.Stagiaire;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@Data
@AllArgsConstructor
@RequestMapping(path = "/groupe")
public class GroupeController {
    @Autowired
    private final GroupeDao groupeDao;

    @PostMapping(path = "/ajouter", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> create(@RequestBody Groupe groupe){
        try {
            return new ResponseEntity<>(groupeDao.create(groupe), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
    @GetMapping(path = "/tous")
    public List<Groupe> getAll(){
        return groupeDao.getAll();
    }

    @GetMapping(path = "/stat")
    public long getNumberOfGroupes(){
        return groupeDao.countAll();
    }

    @GetMapping(path = "/{id}")
    public  ResponseEntity<Groupe> getById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(groupeDao.getById(id).orElseThrow(()->new EntityNotFoundException("groupe avec id "+id+" n'existe pas")));
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/supprimer/{id}")
    public  ResponseEntity<String> delete(@PathVariable("id") Long id){
        try {
            groupeDao.delete(id);
            return  ResponseEntity.ok("Deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Entity not found");
        }
    }
/* @GetMapping(path = "/{nom}")
    public Groupe getByNom(@PathVariable("nom") String nom){return groupeDao.getGroupeByNom(nom);
    }*/

}
