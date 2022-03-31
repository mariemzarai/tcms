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
    private Integer id;
    @Column(nullable = false)
    private String nom;
    private String description;
    private float coefficient = 1;
    private Float nb_heures;
    @ManyToOne
    private Specialite specialite;
    @OneToMany(mappedBy = "matiere") // Makes Note the owning side. --RECOMMENDED
    private List<Note> notes;
}
