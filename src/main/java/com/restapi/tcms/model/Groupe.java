package com.restapi.tcms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private List<Stagiaire> stagiaires;
    @OneToMany(mappedBy = "groupe")
    @JsonIgnore
    private List<Seance> seances;


}
