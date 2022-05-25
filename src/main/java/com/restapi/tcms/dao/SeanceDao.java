package com.restapi.tcms.dao;

import com.restapi.tcms.model.Seance;
import com.restapi.tcms.repository.SeanceRepository;
import com.restapi.tcms.service.ServiceHistorique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class SeanceDao implements Dao<Seance>{
    private final SeanceRepository seanceRepository;
    private final ServiceHistorique serviceHistorique;
    @Autowired
    public SeanceDao(SeanceRepository seanceRepository, ServiceHistorique serviceHistorique) {
        this.seanceRepository = seanceRepository;
        this.serviceHistorique = serviceHistorique;
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
        Seance save = seanceRepository.save(seance);
        serviceHistorique.enregistrerHistorique("creation d'une nouvelle seance: " + save.getFormateur().getNom() + " " + save.getFormateur().getPrenom() + " enseigne " + save.getMatiere().getNom() + " à " + save.getGroupe() + " : " + save.getGroupe().getSpecialite().getTitre());
        return Optional.of(save);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if(seanceRepository.existsById(id)) {
            seanceRepository.deleteById(id);
            serviceHistorique.enregistrerHistorique("suppression de la seance avec id " + id);
        }
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
