package com.restapi.tcms.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;

@Entity
public class Stagiaire extends Personne{

    private String nom_parent;
    private String num_tel_parent;
    private String adresse_postale;

    public Stagiaire() {
    }

    public Stagiaire(String nom, String prenom, String email, String nom_parent, String adresse_postale) {
        super(nom, prenom, email);
        this.nom_parent = nom_parent;
        this.adresse_postale = adresse_postale;
    }

    public String getNom_parent() {
        return nom_parent;
    }

    public void setNom_parent(String nom_parent) {
        this.nom_parent = nom_parent;
    }

    public String getAdresse_postale() {
        return adresse_postale;
    }

    public void setAdresse_postale(String adresse_postale) {
        this.adresse_postale = adresse_postale;
    }

    public String getNum_tel_parent() {
        return num_tel_parent;
    }

    public void setNum_tel_parent(String num_tel_parent) {
        this.num_tel_parent = num_tel_parent;
    }

}




