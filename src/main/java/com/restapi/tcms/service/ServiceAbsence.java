package com.restapi.tcms.service;

import com.restapi.tcms.dao.SeanceDao;
import com.restapi.tcms.model.Seance;
import com.restapi.tcms.model.Stagiaire;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ServiceAbsence {
    private SeanceDao seanceDao;

    public ServiceAbsence(SeanceDao seanceDao) {
        this.seanceDao = seanceDao;
    }

    public List<Stagiaire> getStagiairesFromSeance(Long idSeance){
        Seance seance = seanceDao.getById(idSeance).orElseThrow(()->new EntityNotFoundException("no seance with id " + idSeance));
        return seance.getGroupe().getStagiaires();
    }

    
}
