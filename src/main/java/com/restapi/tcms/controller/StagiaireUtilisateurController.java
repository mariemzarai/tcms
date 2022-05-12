package com.restapi.tcms.controller;

import com.restapi.tcms.model.MatiereNoteAbsences;
import com.restapi.tcms.model.Personne;
import com.restapi.tcms.model.Stagiaire;
import com.restapi.tcms.service.ServiceAuth;
import com.restapi.tcms.service.ServiceStagiaire;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/matieres")
public class StagiaireUtilisateurController {
    private final ServiceStagiaire serviceStagiaire;
    private final ServiceAuth serviceAuth;

    public StagiaireUtilisateurController(ServiceStagiaire serviceStagiaire, ServiceAuth serviceAuth) {
        this.serviceStagiaire = serviceStagiaire;
        this.serviceAuth = serviceAuth;
    }

    @GetMapping(path = "/")
    public List<MatiereNoteAbsences> getMatiereNotesAbsenceOfAuthentificatedStagiaire(){
        Personne user = serviceAuth.getAuthenticatedUser();
        if(!(user instanceof Stagiaire))
            return null;
        List<MatiereNoteAbsences> listeMatiereNoteAbsences = serviceStagiaire.getListeMatiereNoteAbsences((Stagiaire) user);
        return listeMatiereNoteAbsences;
    }
}
