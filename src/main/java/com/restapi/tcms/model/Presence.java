package com.restapi.tcms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Presence {
    @Id
    @SequenceGenerator(
            name = "presence_sequence",
            sequenceName = "presence_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "presence_sequence"
    )
    private long id;
    @ManyToOne
    private Seance seance;
    private Date date;
    @ManyToOne
    private Stagiaire stagiaire;
    private boolean present;

    public Presence(Seance seance, Date date, Stagiaire stagiaire, boolean present) {
        this.seance = seance;
        this.date = date;
        this.stagiaire = stagiaire;
        this.present = present;
    }
}
