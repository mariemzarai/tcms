package com.restapi.tcms;

import com.restapi.tcms.model.Formateur;
import com.restapi.tcms.model.Personne;
import com.restapi.tcms.security.Role;
import com.restapi.tcms.security.Utilisateur;
import com.restapi.tcms.security.UtilisateurDao;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceAuth {
    private UtilisateurDao utilisateurDao;

    public ServiceAuth(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    public boolean createIdentityAccount(Personne p){
        Role role = (p instanceof Formateur? Role.ROLE_FORMATEUR: Role.ROLE_STAGIAIRE);
        Optional<Utilisateur> OpUtilisateur =
                utilisateurDao.create(new Utilisateur(null, p.getEmail(), p.getNum_tel(), role, p));
        return OpUtilisateur.isPresent();
    }
}
