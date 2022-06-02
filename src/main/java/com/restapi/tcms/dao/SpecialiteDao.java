package com.restapi.tcms.dao;

import com.restapi.tcms.model.Specialite;
import com.restapi.tcms.repository.SpecialiteRepository;
import com.restapi.tcms.service.ServiceHistorique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class SpecialiteDao implements Dao<Specialite>{

    private final  SpecialiteRepository specialiteRepository;
    private final ServiceHistorique serviceHistorique;
    @Autowired
    public SpecialiteDao(SpecialiteRepository specialiteRepository, ServiceHistorique serviceHistorique){
        this.specialiteRepository=specialiteRepository;
        this.serviceHistorique = serviceHistorique;
    }

    @Override
    public Optional<Specialite> getById(Long id) {return specialiteRepository.findById(id);

    }
    @Override
    public List<Specialite> getAll() {
        return specialiteRepository.findAll();
    }

     @Override
     public Optional<Specialite> create(Specialite specialite){
        if(specialite.getTitre() == null || specialite.getTitre().length() == 0)
            throw new DataIntegrityViolationException("specialite_titre_ne_peut_pas_etre_vide");
        if(!specialiteRepository.existsByTitre(specialite.getTitre())) {
            Specialite save = specialiteRepository.save(specialite);
            serviceHistorique.enregistrerHistorique("creation d'une nouvelle spécialité: " + save.getTitre());
            return Optional.of(save) ;
        } else
            throw new DataIntegrityViolationException("specialite_avec_ce_titre_existe");
    }


    @Override
    public void delete(Long id) throws EntityNotFoundException {
          if(specialiteRepository.existsById(id)) {
              specialiteRepository.deleteById(id);
              serviceHistorique.enregistrerHistorique("suppression de la spéécialité avec id: " + id);
          }
          else throw new EntityNotFoundException();
    }


    public long countAll() {
        return  specialiteRepository.count();
    }
}
