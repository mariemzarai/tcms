package com.restapi.tcms.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Formateur extends Personne {
    @Column(unique = true,nullable = false)
    private String email2;
    private String profession;

    @OneToMany(mappedBy = "formateur")
    @JsonIgnore
    private List<Seance> seances;

}
