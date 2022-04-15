package com.restapi.tcms.dao;

import com.restapi.tcms.model.Note;
import com.restapi.tcms.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class NoteDao implements Dao<Note>{
    private final NoteRepository noteRepository;

    @Autowired
    public NoteDao(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Optional<Note> getById(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    @Override
    public Optional<Note> create(Note note) {
        return Optional.of(noteRepository.save(note));
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if(noteRepository.existsById(id))
            noteRepository.deleteById(id);
        else throw new EntityNotFoundException();
    }
}
