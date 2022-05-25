package com.restapi.tcms.dao;

import com.restapi.tcms.model.Matiere;
import com.restapi.tcms.repository.MatiereRepository;
import com.restapi.tcms.repository.SpecialiteRepository;
import com.restapi.tcms.service.ServiceHistorique;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MatiereDao implements Dao<Matiere> {
    private final MatiereRepository matiereRepository;
    private final SpecialiteRepository specialiteRepository;
    private final ServiceHistorique serviceHistorique;

    public MatiereDao(MatiereRepository matiereRepository, SpecialiteRepository specialiteRepository, ServiceHistorique serviceHistorique) {
        this.matiereRepository = matiereRepository;
        this.specialiteRepository = specialiteRepository;
        this.serviceHistorique = serviceHistorique;
    }

    @Override
    public Optional<Matiere> create(Matiere matiere){
        if(matiere.getNom() == null || matiere.getNom().length() == 0)
            throw new DataIntegrityViolationException("nom_de_matiere_ne_peut_pas_etre_vide");
        if(!matiereRepository.existsByNom(matiere.getNom())) {
            Matiere save = matiereRepository.save(matiere);
            serviceHistorique.enregistrerHistorique("creation d'une nouvelle matiere: " + save.getNom() + " pour " + save.getSpecialite().getTitre());
            return Optional.of(save);
        }
        else
            throw new DataIntegrityViolationException("matiere_avec_ce_nom_existe");
    }
    @Override
    public  Optional<Matiere> getById(Long id) {
        return matiereRepository.findById(id) ;}
    @Override
    public List<Matiere> getAll() {
        return matiereRepository.findAll();
    }
    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if(matiereRepository.existsById(id)) {
            matiereRepository.deleteById(id);
            serviceHistorique.enregistrerHistorique("suppression de la matière avec l'id: " + id);
        }
        else throw new EntityNotFoundException();
    }

    @Transactional
    public Matiere update(Long id, String nom, String description, Float coef, Float nb_heures, Long specialite) {
        Optional<Matiere> optionalMatiere= getById(id);
        Matiere matiere;

        if (optionalMatiere.isPresent()){
            matiere = optionalMatiere.get();
        }
        else throw new NoSuchElementException("Aucun matiere avec id " + specialite);

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

    public boolean existsById(Long id) {
        return matiereRepository.existsById(id);
    }
}
