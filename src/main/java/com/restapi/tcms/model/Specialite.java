package com.restapi.tcms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Specialite {
    @Id
    @SequenceGenerator(
            name = "specialite_sequence",
            sequenceName = "specialite_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "specialite_sequence"
    )
    private Long id;
    @Column(unique = true, nullable = false)
    private String titre;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "specialite")
    @ToString.Exclude
    private List<Stagiaire> listeStagiaires;

    @JsonIgnore
    @OneToMany(mappedBy = "specialite")
    @ToString.Exclude
    private List<Matiere> listeMatieres;

    @JsonIgnore
    @OneToMany(mappedBy = "specialite")
    @ToString.Exclude
    private  List<Groupe> listeGroupes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Specialite that = (Specialite) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
