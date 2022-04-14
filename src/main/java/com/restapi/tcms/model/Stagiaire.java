package com.restapi.tcms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Stagiaire extends Personne{

    private String nom_parent;
    private String num_tel_parent;
    private String adresse_postale;
    @ManyToOne
    private Specialite specialite;
    @ManyToOne
    private Groupe groupe;
    @OneToMany(mappedBy = "stagiaire")
    private List<Note> notes;
}




