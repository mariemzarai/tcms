package com.restapi.tcms.dao;

import com.restapi.tcms.model.Matiere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class MatiereDao {
    @Autowired
    private MatiereRepository matiereRepository;
    @Autowired
    private SpecialiteRepository specialiteRepository;

    public Matiere create(Matiere matiere){
        if(matiere.getNom() == null || matiere.getNom().length() == 0)
            throw new DataIntegrityViolationException("nom_de_matiere_ne_peut_pas_etre_vide");
        if(!matiereRepository.existsByNom(matiere.getNom()))
            return matiereRepository.save(matiere);
        else
            throw new DataIntegrityViolationException("matiere_avec_ce_nom_existe");
    }

    public List<Matiere> getAll() {
        return matiereRepository.findAll();
    }

    public void delete(Integer id) throws EntityNotFoundException {
        if(matiereRepository.existsById(id))
            matiereRepository.deleteById(id);
        else throw new EntityNotFoundException();
    }

    public Matiere getById(Integer id) {
        return matiereRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("Pas de matiere avec l'id " + id));
    }
    @Transactional
    public Matiere update(Integer id, String nom, String description, Float coef, Float nb_heures, Integer specialite) {
        Matiere matiere = getById(id);
        if(nom != null && nom.length() > 0){
            matiere.setNom(nom);
        }
        if(description != null){
            matiere.setDescription(description);
        }

        if (coef != null && coef > 0) {
            matiere.setCoefficient(coef);
        }
        if (nb_heures != null && nb_heures > 0) {
            matiere.setNb_heures(nb_heures);
        }
        if(specialite != null && specialiteRepository.findById(specialite).isPresent()){
            matiere.setSpecialite(specialiteRepository.findById(specialite).get());
        }
        return  matiere;
    }

    public boolean existsById(Integer id) {
        return matiereRepository.existsById(id);
    }
}
