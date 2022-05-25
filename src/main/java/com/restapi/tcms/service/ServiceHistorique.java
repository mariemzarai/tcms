package com.restapi.tcms.service;

import com.restapi.tcms.model.History;
import com.restapi.tcms.model.Personne;
import com.restapi.tcms.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceHistorique {
    private HistoryRepository historyRepository;
    private  ServiceAuth serviceAuth;

    public ServiceHistorique(HistoryRepository historyRepository, ServiceAuth serviceAuth) {
        this.historyRepository = historyRepository;
        this.serviceAuth = serviceAuth;
    }

    public void enregistrerHistorique(String desc){
        Personne personne = serviceAuth.getAuthenticatedUser();
        History history = new History(null, desc, personne, new Date());
        historyRepository.save(history);
    }

    public List<History> getAll() {
        return historyRepository.findAll();
    }
}
