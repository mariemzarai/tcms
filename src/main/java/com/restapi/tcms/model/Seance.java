package com.restapi.tcms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Seance {
    @Id
    @SequenceGenerator(
            name = "seance_sequence",
            sequenceName = "seance_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seance_sequence"
    )
    private Long id;
    @ManyToOne
    private Formateur formateur;
    @ManyToOne
    private Groupe groupe;
    @ManyToOne
    private Matiere matiere;
    @OneToMany(mappedBy = "seance")
    @JsonIgnore
    @ToString.Exclude
    private List<Presence> presenceList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Seance seance = (Seance) o;
        return id != null && Objects.equals(id, seance.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Seance{" + "id=" + id + '}';
    }
}
