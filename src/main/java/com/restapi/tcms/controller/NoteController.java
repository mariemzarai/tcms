package com.restapi.tcms.controller;

import com.restapi.tcms.dao.NoteDao;
import com.restapi.tcms.model.Note;
import com.restapi.tcms.model.NoteExamListeNotes;
import com.restapi.tcms.service.ServiceNotes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/note")
public class NoteController {
    private final NoteDao noteDao;
    private final ServiceNotes serviceNotes;

    @Autowired
    public NoteController(NoteDao noteDao, ServiceNotes serviceNotes) {
        this.noteDao = noteDao;
        this.serviceNotes = serviceNotes;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Note> get(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(noteDao.getById(id).orElseThrow(()-> new EntityNotFoundException("note n'esxite pas d'id"+id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/ajouter", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Note> create(@RequestBody Note note){
        Optional<Note> newNote = noteDao.create(note);
        if(newNote.isPresent())
            return new ResponseEntity<>(newNote.get(), HttpStatus.CREATED);
        else
            return ResponseEntity.badRequest().build();
    }

    @PostMapping(path = "/ajouterListe/{seanceId}", consumes = "application/json", produces = "application/json")
    public void ajouterListeDeNotes(@RequestBody NoteExamListeNotes noteExamListeNotes, @PathVariable(name = "seanceId") Long seanceId){
        //TODO change type examen
        serviceNotes.ajouterNotes(seanceId, noteExamListeNotes.getTypeExam(), noteExamListeNotes.getListeNotes());
    }

    @GetMapping(path = "/tous")
    public List<Note> getAll(){
        return noteDao.getAll();
    }

    @DeleteMapping(path = "/supprimer/{id}")
    public  ResponseEntity<?> delete(@PathVariable("id") Long id){
        try {
            noteDao.delete(id);
            return  ResponseEntity.ok("Deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Entity not found");
        }
    }

}
