package com.restapi.tcms.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ServiceScolarite extends Personne{
    public ServiceScolarite(String nom, String prenom, String email, String num_tel) {
        super(nom, prenom, email, num_tel);
    }
}
