package com.restapi.tcms.service;

import com.restapi.tcms.model.Formateur;
import com.restapi.tcms.model.Personne;
import com.restapi.tcms.model.Seance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceFormateur {
    private ServiceAuth serviceAuth;

    public ServiceFormateur(ServiceAuth serviceAuth) {
        this.serviceAuth = serviceAuth;
    }

    public List<Seance> getListSeanceOfAuthenticatedFormateur(){
        Personne p = serviceAuth.getAuthenticatedUser();
        if(p instanceof  Formateur) {
            return ((Formateur) p).getSeances();
        }
        else throw new RuntimeException("Utilisateur authentifié n'est pas un formateur");
    }
}
