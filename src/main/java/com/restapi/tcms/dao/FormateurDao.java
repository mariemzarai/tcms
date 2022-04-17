package com.restapi.tcms.dao;
import com.restapi.tcms.model.Formateur;
import com.restapi.tcms.repository.FormateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;


@Service
public class FormateurDao implements Dao<Formateur>{
    private  final FormateurRepository formateurRepository ;
    @Autowired
    public FormateurDao(FormateurRepository formateurRepository)
    {
        this.formateurRepository=formateurRepository;
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
        if(!formateurRepository.existsByEmail(formateur.getEmail()))
            return Optional.of(formateurRepository.save(formateur));
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

    /*public Formateur getFormateurByNom(String nom) {
         return  formateurRepository.getFormateurByNom(nom).orElseThrow(()->new RuntimeException("aucun_formateur_avec_le_nom "+ nom));
     }*/



}
