package com.restapi.tcms.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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
    private boolean absent;

    public Presence(Seance seance, Date date, Stagiaire stagiaire, boolean absent) {
        this.seance = seance;
        this.date = date;
        this.stagiaire = stagiaire;
        this.absent = absent;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
