package com.restapi.tcms.service;

import com.restapi.tcms.dao.NoteDao;
import com.restapi.tcms.dao.SeanceDao;
import com.restapi.tcms.dao.StagiaireDao;
import com.restapi.tcms.model.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
public class ServiceNotes {
    private final SeanceDao seanceDao;
    private final NoteDao noteDao;
    private final StagiaireDao stagiaireDao;

    public ServiceNotes(SeanceDao seanceDao, NoteDao noteDao, StagiaireDao stagiaireDao) {
        this.seanceDao = seanceDao;
        this.noteDao = noteDao;
        this.stagiaireDao = stagiaireDao;
    }

    public void ajouterNotes(Long seanceId, String epreuve, List<StagiaireNote> listeNotes){
        Seance seance = seanceDao.getById(seanceId).orElseThrow(()->new EntityNotFoundException("Seance not found"));
        List<Stagiaire> stagiaireList = seance.getGroupe().getStagiaires();
        listeNotes.forEach(stagiaireNote -> {
            Stagiaire stagiaire = stagiaireDao.getById(stagiaireNote.getId()).orElseThrow(()-> new EntityNotFoundException("Stagiaire not found"));
            Note note = new Note(stagiaireNote.getNote(), epreuve, stagiaire, seance.getMatiere());
            noteDao.create(note);
        });
    }
}
