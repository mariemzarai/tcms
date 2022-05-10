package com.restapi.tcms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private List<Presence> presenceList;
}
