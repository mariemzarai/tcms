package com.restapi.tcms.dao;
import com.restapi.tcms.service.ServiceAuth;
import com.restapi.tcms.model.Formateur;
import com.restapi.tcms.repository.FormateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;


@Service
public class FormateurDao implements Dao<Formateur>{
    private final FormateurRepository formateurRepository;
    private final ServiceAuth serviceAuth;
    @Autowired
    public FormateurDao(FormateurRepository formateurRepository, ServiceAuth serviceAuth)
    {
        this.formateurRepository=formateurRepository;
        this.serviceAuth = serviceAuth;
    }

    @Override
    public Optional<Formateur> getById(Long id) {
        return formateurRepository.findById(id);
    }

    @Override
    public List<Formateur> getAll() {
        return formateurRepository.findAll();
    }

    @Override
    public Optional<Formateur> create(Formateur  formateur) {
        if(!formateurRepository.existsByEmail(formateur.getEmail())) {
            Optional<Formateur> opFormateur =  Optional.of(formateurRepository.save(formateur));
            opFormateur.ifPresent(serviceAuth::createIdentityAccount);
            return opFormateur;
        }
        else
            throw new DataIntegrityViolationException("email taken");
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if(formateurRepository.existsById(id))
            formateurRepository.deleteById(id);
        else throw new EntityNotFoundException();
    }


  @Transactional
    public Formateur update(Long id, String nom, String prenom, Byte sexe, String email, String num_tel, String email2 , String profession) {
        Formateur f = formateurRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Formateur avec id " + id + " n'existe pas.\n"));

        if(nom != null && nom.length() > 0){
            f.setNom(nom);
        }

        if(prenom != null && prenom.length() > 0){
            f.setPrenom(prenom);
        }

        if(sexe != null){
            f.setSexe(sexe);
        }

        if(email != null && email.length() > 0){
            f.setEmail(email);
        }

        if(num_tel != null && num_tel.length() > 0){
            f.setNum_tel(num_tel);
        }
        if(email2 != null && email2.length() > 0){
            f.setEmail2(email2);
        }
        if(profession != null && profession.length() > 0){
            f.setProfession(profession);
        }

        return f;
    }

    public long countAll() {
        return formateurRepository.count();
    }

    /*public Formateur getFormateurByNom(String nom) {
         return  formateurRepository.getFormateurByNom(nom).orElseThrow(()->new RuntimeException("aucun_formateur_avec_le_nom "+ nom));
     }*/



}
