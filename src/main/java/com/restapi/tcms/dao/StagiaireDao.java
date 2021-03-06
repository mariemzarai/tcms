package com.restapi.tcms.dao;

import com.restapi.tcms.service.ServiceAuth;
import com.restapi.tcms.model.Stagiaire;
import com.restapi.tcms.repository.StagiaireRepository;
import com.restapi.tcms.service.ServiceHistorique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StagiaireDao implements Dao<Stagiaire> {

    private final StagiaireRepository stagiaireRepository;
    private final ServiceAuth serviceAuth;
    private final ServiceHistorique serviceHistorique;
    @Autowired
    public  StagiaireDao(StagiaireRepository stagiaireRepository, ServiceAuth serviceAuth, ServiceHistorique serviceHistorique){this.stagiaireRepository=stagiaireRepository;
        this.serviceAuth = serviceAuth;
        this.serviceHistorique = serviceHistorique;
    }

    @Override
    public Optional<Stagiaire> getById(Long id){
        return stagiaireRepository.findById(id);
    }
    @Override
    public Optional<Stagiaire> create(Stagiaire stagiaire) {
        if(!stagiaireRepository.existsByEmail(stagiaire.getEmail())) {
            Optional<Stagiaire> opStagiaire = Optional.of(stagiaireRepository.save(stagiaire));
            opStagiaire.ifPresent(serviceAuth::createIdentityAccount);
            serviceHistorique.enregistrerHistorique("creation d'un nouveau stagiaire: " + stagiaire.getNom() + " " + stagiaire.getPrenom());
            return  opStagiaire;
        }
        else
            throw new DataIntegrityViolationException("email taken");
    }
    @Override
    public void delete(Long id) throws EntityNotFoundException{
        if(stagiaireRepository.existsById(id)) {
            stagiaireRepository.deleteById(id);
            serviceHistorique.enregistrerHistorique("suppression du stagiaire avec l'id: " + id);
        }
        else throw new EntityNotFoundException();
    }

    public List<Stagiaire> getAll(){
        return stagiaireRepository.findAll();
    }

    @Transactional
    public Stagiaire update(Long id, String nom, String prenom, Byte sexe, String email, String num_tel, String nom_parent, String num_tel_parent, String adresse_postale) {
      Stagiaire s=stagiaireRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("stagiaire avec id " + id + " n'existe pas.\n"));


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

    public long countAll() {
        return stagiaireRepository.count();
    }
}
