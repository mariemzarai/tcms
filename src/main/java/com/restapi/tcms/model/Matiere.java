package com.restapi.tcms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
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
    private List<Note> notes;
    @OneToMany(mappedBy = "matiere")
    @JsonIgnore
    private List<Seance> seances;
}
