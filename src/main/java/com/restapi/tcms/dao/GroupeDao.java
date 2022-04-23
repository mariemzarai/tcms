package com.restapi.tcms.dao;
import com.restapi.tcms.model.Groupe;
import com.restapi.tcms.repository.GroupeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class GroupeDao implements Dao<Groupe> {
    private final GroupeRepository groupeRepository;

    @Autowired
    public GroupeDao(GroupeRepository groupeRepository) {
        this.groupeRepository = groupeRepository;
    }

    @Override
    public Optional<Groupe> getById(Long id) {
        return  groupeRepository.findById(id);
    }
    @Override
    public List<Groupe> getAll() {
        return groupeRepository.findAll() ;
    }
    @Override
    public Optional<Groupe> create(Groupe groupe) {
        if ( groupe.getNom() == null || groupe.getNom().length() == 0)
            throw new DataIntegrityViolationException("nom_groupe_ne_peut_pas_etre_vide");
        else if (!groupeRepository.existsByNom(groupe.getNom()))
             return Optional.of(groupeRepository.save(groupe));
        else
            throw new DataIntegrityViolationException("nom_groupe_ne_peut_pas_etre_dupliqué");
    }
    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if(groupeRepository.existsById(id))
          groupeRepository.deleteById(id);
        else throw new EntityNotFoundException();
    }


    public long countAll() {
        return groupeRepository.count();
    }
}