package com.restapi.tcms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Groupe {
    @Id
    @SequenceGenerator(
            name = "groupe_sequence",
            sequenceName = "groupe_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator= "groupe_sequence"
    )
    private Long id;
    @Column(unique = true, nullable = false)
    private  String nom;
    @ManyToOne
    private Specialite specialite;
    @OneToMany(mappedBy = "groupe")
    @JsonIgnore
    @ToString.Exclude
    private List<Stagiaire> stagiaires;
    @OneToMany(mappedBy = "groupe")
    @JsonIgnore
    @ToString.Exclude
    private List<Seance> seances;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Groupe groupe = (Groupe) o;
        return id != null && Objects.equals(id, groupe.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
