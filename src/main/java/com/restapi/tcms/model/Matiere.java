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
public class Matiere {
    @Id
    @SequenceGenerator(
            name = "matiere_sequence",
            sequenceName = "matiere_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "matiere_sequence"
    )
    Long id;
    @Column(nullable = false)
    private String nom;
    private String description;
    private float coefficient = 1;
    private Float nb_heures;
    @ManyToOne
    private Specialite specialite;
    @OneToMany(mappedBy = "matiere") // Makes Note the owning side. --RECOMMENDED
    @JsonIgnore
    @ToString.Exclude
    private List<Note> notes;
    @OneToMany(mappedBy = "matiere")
    @JsonIgnore
    @ToString.Exclude
    private List<Seance> seances;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Matiere matiere = (Matiere) o;
        return id != null && Objects.equals(id, matiere.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
