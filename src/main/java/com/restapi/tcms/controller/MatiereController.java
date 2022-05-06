package com.restapi.tcms.controller;

import com.restapi.tcms.dao.MatiereDao;
import com.restapi.tcms.model.Matiere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(path = "/matiere")
public class MatiereController {
    private final MatiereDao matiereDao;

    @Autowired
    public MatiereController(MatiereDao matiereDao) {
        this.matiereDao = matiereDao;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Matiere> get(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(matiereDao.getById(id).orElseThrow(()-> new EntityNotFoundException(" matiere n'esxite pas d'id"+id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/ajouter", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> create(@RequestBody Matiere matiere){
        try {
            return new ResponseEntity<>(matiereDao.create(matiere), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @GetMapping(path = "/tous")
    public List<Matiere> getAll(){
        return matiereDao.getAll();
    }

    @DeleteMapping(path = "/supprimer/{id}")
    public  ResponseEntity<String> delete(@PathVariable("id") Long id){
        try {
            matiereDao.delete(id);
            return  ResponseEntity.ok("{\"message\":\"Deleted successfully\"}");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Entity not found");
        }
    }

    @PutMapping(path = "/modifier/{id}")
    public ResponseEntity<Matiere> update(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Float coef,
            @RequestParam(required = false) Float nb_heures,
            @RequestParam(required = false) Long specialite){

        try {
            return ResponseEntity.ok(matiereDao.update(id, nom, description, coef, nb_heures, specialite));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
