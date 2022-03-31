package com.restapi.tcms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    String nom;
    String description;
    float coefficient = 1;
    Float nb_heures;
    @ManyToOne
    private Specialite specialite;
}
