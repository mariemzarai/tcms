package com.restapi.tcms.dao;

import com.restapi.tcms.model.Presence;
import com.restapi.tcms.model.Stagiaire;
import com.restapi.tcms.model.StagiaireAbsence;
import com.restapi.tcms.repository.PresenceRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
@Service
public class PresenceDao implements Dao<Presence> {
    private final PresenceRepository presenceRepository;

    public PresenceDao(PresenceRepository presenceRepository) {
        this.presenceRepository = presenceRepository;
    }


    @Override
    public Optional<Presence> getById(Long id) {
        return presenceRepository.findById(id);
    }

    @Override
    public List<Presence> getAll() {
        return presenceRepository.findAll();
    }

    @Override
    public Optional<Presence> create(Presence presence) {
        return Optional.of(presenceRepository.save(presence));
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Presence presence = presenceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("presence entity with id" +id+ " not found"));
    }

    public List<Stagiaire> getListeElimineBySeance(Long idSeance) {
        return presenceRepository.getEliminatedBySeance(idSeance);
    }
    public List<StagiaireAbsence> getNbAbsencesBySeance(Long idSeance) {
        return presenceRepository.getNbAbsenceBySeance(idSeance);
    }

    public List<Presence> getAllPresencesBySeance(Long idSeance){
        return presenceRepository.getAllBySeanceId(idSeance);
    }
}
