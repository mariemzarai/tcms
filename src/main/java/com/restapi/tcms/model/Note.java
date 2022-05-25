package com.restapi.tcms.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Note {
    @Id
    @SequenceGenerator(
            name = "note_sequence",
            sequenceName = "note_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "note_sequence"
    )
    private Integer id;
    private Float valeur;
    private String type;
    @ManyToOne
    private Stagiaire stagiaire;
    @ManyToOne
    private Matiere matiere;

    public Note(Float valeur, String type, Stagiaire stagiaire, Matiere matiere) {
        this.valeur = valeur;
        this.type = type;
        this.stagiaire = stagiaire;
        this.matiere = matiere;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Note note = (Note) o;
        return id != null && Objects.equals(id, note.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
