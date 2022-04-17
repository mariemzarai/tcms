package com.restapi.tcms.dao;

import com.restapi.tcms.model.Specialite;
import com.restapi.tcms.repository.SpecialiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class SpecialiteDao implements Dao<Specialite>{

    private  final  SpecialiteRepository specialiteRepository;
    @Autowired
    public SpecialiteDao(SpecialiteRepository specialiteRepository){
        this.specialiteRepository=specialiteRepository;
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
        if(!specialiteRepository.existsByTitre(specialite.getTitre()))
            return Optional.of(specialiteRepository.save(specialite)) ;
        else
            throw new DataIntegrityViolationException("specialite_avec_ce_titre_existe");
    }


    @Override
    public void delete(Long id) throws EntityNotFoundException {
          if(specialiteRepository.existsById(id))
            specialiteRepository.deleteById(id);
          else throw new EntityNotFoundException();
    }



}
