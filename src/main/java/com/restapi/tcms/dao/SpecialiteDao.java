package com.restapi.tcms.dao;

import com.restapi.tcms.model.Specialite;
import com.restapi.tcms.model.Stagiaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
public class SpecialiteDao {
    @Autowired
    private SpecialiteRepository specialiteRepository;

    public Specialite create(Specialite specialite){
        if(specialite.getTitre() == null || specialite.getTitre().length() == 0)
            throw new DataIntegrityViolationException("specialite_titre_ne_peut_pas_etre_vide");
        if(!specialiteRepository.existsByTitre(specialite.getTitre()))
            return specialiteRepository.save(specialite);
        else
            throw new DataIntegrityViolationException("specialite_avec_ce_titre_existe");
    }

    public List<Specialite> getAll() {
        return specialiteRepository.findAll();
    }

    public void delete(Integer id) throws EntityNotFoundException {
        if(specialiteRepository.existsById(id))
            specialiteRepository.deleteById(id);
        else throw new EntityNotFoundException();
    }

    public Specialite getById(Integer id) {
        return specialiteRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("Pas de specialite avec l'id " + id));
    }

}
