package com.restapi.tcms.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Formateur extends Personne {
    @Column(unique = true,nullable = false)
    private String email2 ;
    private String profession ;

}
