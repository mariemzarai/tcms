package com.restapi.tcms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Stagiaire extends Personne{

    private String nom_parent;
    private String num_tel_parent;
    private String adresse_postale;
    @ManyToOne
    private Specialite specialite;
    @ManyToOne
    private Groupe groupe;
    @OneToMany(mappedBy = "stagiaire")
    @JsonIgnore
    @ToString.Exclude
    private List<Note> notes;
    @OneToMany(mappedBy = "stagiaire")
    @JsonIgnore
    @ToString.Exclude
    private List<Presence> presenceList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Stagiaire stagiaire = (Stagiaire) o;
        return getId() != null && Objects.equals(getId(), stagiaire.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}




