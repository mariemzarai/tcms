package com.restapi.tcms.model;

import java.util.List;

public class NoteExamListeNotes {
    private String typeExam;
    private List<StagiaireNote> listeNotes;

    public NoteExamListeNotes(String type, List<StagiaireNote> listeNotes) {
        this.typeExam = type;
        this.listeNotes = listeNotes;
    }

    public String getTypeExam() {
        return typeExam;
    }

    public void setTypeExam(String typeExam) {
        this.typeExam = typeExam;
    }

    public List<StagiaireNote> getListeNotes() {
        return listeNotes;
    }

    public void setListeNotes(List<StagiaireNote> listeNotes) {
        this.listeNotes = listeNotes;
    }
}
