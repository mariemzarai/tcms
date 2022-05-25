package com.restapi.tcms.service;

import com.restapi.tcms.model.Admin;
import com.restapi.tcms.model.Formateur;
import com.restapi.tcms.model.Personne;
import com.restapi.tcms.model.ServiceStagiaire;
import com.restapi.tcms.model.Stagiaire;
import com.restapi.tcms.security.Role;
import com.restapi.tcms.security.Utilisateur;
import com.restapi.tcms.security.UtilisateurDao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceAuth {
    private UtilisateurDao utilisateurDao;

    public ServiceAuth(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    public boolean createIdentityAccount(Personne p){
        Role role = (
                p instanceof Formateur? Role.ROLE_FORMATEUR:
                p instanceof Stagiaire ?Role.ROLE_STAGIAIRE:
                p instanceof Admin ?Role.ROLE_ADMINISTRATEUR:
                p instanceof ServiceStagiaire ?Role.ROLE_S_STAGIAIRE:
                Role.ROLE_S_SCOLARITE
                );

        Optional<Utilisateur> OpUtilisateur =
                utilisateurDao.create(new Utilisateur(null, p.getEmail(), p.getNum_tel(), role, p));
        return OpUtilisateur.isPresent();
    }

    public Personne getAuthenticatedUser(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (username == null)
            return null;
        Utilisateur utilisateur = null;
        if (utilisateurDao.getByUsername(username).isPresent()) {
            utilisateur = utilisateurDao.getByUsername(username).get();
        }
        else
            return null;
        return utilisateur.getIdentite();
    }

    public String getAuthenticatedUserRole(){
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray(new GrantedAuthority[0])[0].getAuthority();
    }
}
