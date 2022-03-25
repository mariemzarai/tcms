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
    private Integer id;
    @Column(unique = true, nullable = false)
    private String titre;
    private String description;
//    @OneToMany(mappedBy = "specialite")
//    private List<Stagiaire> listeStagiaires;
}
