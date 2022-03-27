package com.restapi.tcms.dao;
import com.restapi.tcms.model.Groupe;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Data
@AllArgsConstructor
public class GroupeDao {
    @Autowired
    private final GroupeRepository groupeRepository;

    public List<Groupe> getAll() {
        return groupeRepository.findAll() ;
    }

    public  Groupe getById(Long id) {
        return  groupeRepository.getById(id) ;
    }

    public Groupe  create( Groupe groupe) {
        if ( groupe.getNom() == null || groupe.getNom().length() == 0)
            throw new DataIntegrityViolationException("nom_groupe_ne_peut_pas_etre_vide");
        else if (!groupeRepository.existsByNom(groupe.getNom()))
             return groupeRepository.save(groupe);
        else
            throw new DataIntegrityViolationException("nom_groupe_ne_peut_pas_etre_dupliqué");
    }
    public void delete(long id) throws EntityNotFoundException {
        if(groupeRepository.existsById(id))
          groupeRepository.deleteById(id);
        else throw new EntityNotFoundException();
    }

    /* public Groupe getGroupeByNom(String nom) {
        return  groupeRepository.getGroupeByNom(nom).orElseThrow(()->new RuntimeException("aucun_groupe_avec_le_nom "+ nom));
    }*/


}