package com.restapi.tcms.dao;

import com.restapi.tcms.model.Stagiaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Component
public class StagiaireDao {
    @Autowired
    private StagiaireRepository stagiaireRepository;

    public Stagiaire create(Stagiaire stagiaire) {
        if(!stagiaireRepository.existsByEmail(stagiaire.getEmail()))
            return stagiaireRepository.save(stagiaire);
        else
            throw new DataIntegrityViolationException("email taken");
    }

    public Stagiaire getById(Long id){
        return stagiaireRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("Pas de stagiaire avec l'id " + id));
    }

    public void delete(Long id) throws EntityNotFoundException{
        if(stagiaireRepository.existsById(id))
            stagiaireRepository.deleteById(id);
        else throw new EntityNotFoundException();
    }

    public List<Stagiaire> getAll(){
        return stagiaireRepository.findAll();
    }

    @Transactional
    public Stagiaire update(Long id, String nom, String prenom, Byte sexe, String email, String num_tel, String nom_parent, String num_tel_parent, String adresse_postale) {
        Stagiaire s = stagiaireRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Stagiaire avec id " + id + " n'existe pas.\n"));

        if(nom != null && nom.length() > 0){
            s.setNom(nom);
        }

        if(prenom != null && prenom.length() > 0){
            s.setPrenom(prenom);
        }

        if(sexe != null){
            s.setSexe(sexe);
        }

        if(email != null && email.length() > 0){
            s.setEmail(email);
        }

        if(num_tel != null && num_tel.length() > 0){
            s.setNum_tel(num_tel);
        }

        if(nom_parent != null && nom_parent.length() > 0){
            s.setNom_parent(nom_parent);
        }

        if(num_tel_parent != null && num_tel_parent.length() > 0){
            s.setNum_tel_parent(num_tel_parent);
        }

        if(adresse_postale != null && adresse_postale.length() > 0){
            s.setAdresse_postale(adresse_postale);
        }

        return s;
    }

}