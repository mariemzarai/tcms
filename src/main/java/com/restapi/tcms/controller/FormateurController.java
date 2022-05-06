package com.restapi.tcms.controller;
import com.restapi.tcms.dao.FormateurDao;
import com.restapi.tcms.model.Formateur;
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
@RequestMapping(path="/formateur")
public class FormateurController {


    private final FormateurDao formateurDao;
    @Autowired
    public FormateurController(FormateurDao formateurDao){this.formateurDao=formateurDao;}

    @PostMapping(path = "/ajouter", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> create(@RequestBody Formateur formateur){
        try {
            return  ResponseEntity.ok(formateurDao.create(formateur).get());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @GetMapping(path = "/tous")
    public List<Formateur> getAll(){
        return formateurDao.getAll();
    }

    @GetMapping(path = "/stat")
    public long getNumberOfFormateurs(){
        return formateurDao.countAll();
    }

    @DeleteMapping(path = "/supprimer/{id}")
    public  ResponseEntity<String> delete(@PathVariable("id") Long id){
        try {
            formateurDao.delete(id);
            return  ResponseEntity.ok("{\"message\":\"Deleted successfully\"}");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Entity not found");
        }
    }

    @GetMapping(path = "/{id}")
    public  ResponseEntity<Formateur> getById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(formateurDao.getById(id).orElseThrow(()-> new EntityNotFoundException("formateur avec id: " + id + " n'existe pas")));
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

   @PutMapping(path = "/modifier/{id}")
    public Formateur update(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String prenom,
            @RequestParam(required = false) Byte sexe,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String num_tel,
            @RequestParam(required = false) String email2,
            @RequestParam(required = false) String profession)
            {return formateurDao.update(id, nom, prenom, sexe, email, num_tel,email2,profession);
    }
}
