package com.restapi.tcms.dao;

import com.restapi.tcms.model.Seance;
import com.restapi.tcms.repository.SeanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class SeanceDao implements Dao<Seance>{
    private final SeanceRepository seanceRepository;

    @Autowired
    public SeanceDao(SeanceRepository seanceRepository) {
        this.seanceRepository = seanceRepository;
    }

    @Override
    public Optional<Seance> getById(Long id) {
        return seanceRepository.findById(id);
    }

    @Override
    public List<Seance> getAll() {
        return seanceRepository.findAll();
    }

    @Override
    public Optional<Seance> create(Seance seance) {
        return Optional.of(seanceRepository.save(seance));
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if(seanceRepository.existsById(id))
            seanceRepository.deleteById(id);
        else throw new EntityNotFoundException();
    }

    public List<Seance> getAllByGroupe(Long groupeId){
        return seanceRepository.findByGroupe(groupeId);
    }

    public List<Seance> getAllByMatiere(Long matiereId){
        return seanceRepository.findByMatiere(matiereId);
    }

    public List<Seance> getAllByFormateur(Long formateurId){
        return seanceRepository.findByFormateur(formateurId);
    }

    public List<Seance> getAllByGroupeAndMatiere(Long groupe, Long matiere){
        return seanceRepository.findByGroupeAndMatiere(groupe, matiere);
    }
}
