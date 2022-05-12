package com.restapi.tcms.service;

import com.restapi.tcms.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceStagiaire {

    public List<MatiereNoteAbsences> getListeMatiereNoteAbsences(Stagiaire stagiaire){
        List<MatiereNoteAbsences> matiereNoteAbsencesList = new ArrayList<>();
        List<Matiere> matiereList = stagiaire.getSpecialite().getListeMatieres();
        List<Note> noteList = stagiaire.getNotes();
        List<Presence> presenceList = stagiaire.getPresenceList();

        matiereList.forEach(matiere -> {
            Float moyenne = calculerMoyenne(
                    noteList.stream().filter(n -> n.getMatiere().equals(matiere))
                            .collect(Collectors.toList())
            );
            Integer nb_absences =
                    presenceList.stream().filter(p -> p.getSeance().getMatiere().equals(matiere) && p.isAbsent()).toList().size();
            MatiereNoteAbsences matiereNoteAbsences = new MatiereNoteAbsences(matiere.getId(), matiere.getNom(), moyenne, nb_absences);
            matiereNoteAbsencesList.add(matiereNoteAbsences);
        });
        return matiereNoteAbsencesList;
    }


    private Float calculerMoyenne(List<Note> collect) {
        Float sum = 0F;
        for (int i = 0; i < collect.size(); i++) {
            sum += collect.get(i).getValeur();
        }
        if(sum == 0F)
            return 0F;
        return sum/collect.size();
    }
}
