package com.restapi.tcms.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Personne {
    public static final Byte Homme = 1;
    public static final Byte Femme = 2;
    @Id
    @SequenceGenerator(
            name = "stagiaire_sequence",
            sequenceName = "stagiaire_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stagiaire_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    private Byte sexe = 0;
    @Column(unique = true)
    private String email;
    private String num_tel;

    public Personne(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Personne() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte getSexe() {
        return sexe;
    }

    public void setSexe(byte sexe) {
        this.sexe = sexe;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }
}
