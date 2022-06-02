package com.restapi.tcms.model;

import com.restapi.tcms.security.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {
    @Id
    @SequenceGenerator(
            name = "history_sequence",
            sequenceName = "history_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator= "history_sequence"
    )
    private Long id;
    @Column
    private String description;
    @ManyToOne
    private Personne utilisateur;
    private Date date;
}
