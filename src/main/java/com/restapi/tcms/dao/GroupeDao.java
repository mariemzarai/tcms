package com.restapi.tcms.dao;
import com.restapi.tcms.model.Groupe;
import com.restapi.tcms.model.Stagiaire;
import com.restapi.tcms.repository.GroupeRepository;
import com.restapi.tcms.service.ServiceHistorique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class GroupeDao implements Dao<Groupe> {
    private final GroupeRepository groupeRepository;
    private final ServiceHistorique serviceHistorique;

    @Autowired
    public GroupeDao(GroupeRepository groupeRepository, ServiceHistorique serviceHistorique) {
        this.groupeRepository = groupeRepository;
        this.serviceHistorique = serviceHistorique;
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
        else if (!groupeRepository.existsByNom(groupe.getNom())) {
            Groupe save = groupeRepository.save(groupe);
            serviceHistorique.enregistrerHistorique("creation d'un nouveau groupe: " + save.getNom() + " pour " + save.getSpecialite().getTitre());
            return Optional.of(save);
        }
        else
            throw new DataIntegrityViolationException("nom_groupe_ne_peut_pas_etre_dupliqué");
    }
    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if(groupeRepository.existsById(id)) {
            groupeRepository.deleteById(id);
            serviceHistorique.enregistrerHistorique("suppression du groupe avec id " + id);
        }
        else throw new EntityNotFoundException();
    }

    public List<Stagiaire> getStagiairesOfGroupe(Long id){
        Groupe groupe = groupeRepository.getById(id);
        return groupe.getStagiaires();
    }

    public long countAll() {
        return groupeRepository.count();
    }
}