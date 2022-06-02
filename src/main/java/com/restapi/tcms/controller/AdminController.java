package com.restapi.tcms.controller;

import com.restapi.tcms.model.Formateur;
import com.restapi.tcms.model.History;
import com.restapi.tcms.service.ServiceHistorique;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/admin")
public class AdminController {

    private final ServiceHistorique serviceHistorique;

    public AdminController(ServiceHistorique serviceHistorique) {
        this.serviceHistorique = serviceHistorique;
    }

    @GetMapping(path = "/historique")
    public List<History> getAllHistorique(){
        return serviceHistorique.getAll();
    }
}
