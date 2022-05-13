package com.restapi.tcms.model;

public class MatiereNoteAbsences {
    private Long matiere_id;
    private String matiere_nom;
    private Float note;
    private Integer nb_absences;

    public MatiereNoteAbsences(Long matiere_id, String matiere_nom, Float note, Integer nb_absences) {
        this.matiere_id = matiere_id;
        this.matiere_nom = matiere_nom;
        this.note = note;
        this.nb_absences = nb_absences;
    }

    public Long getMatiere_id() {
        return matiere_id;
    }

    public void setMatiere_id(Long matiere_id) {
        this.matiere_id = matiere_id;
    }

    public String getMatiere_nom() {
        return matiere_nom;
    }

    public void setMatiere_nom(String matiere_nom) {
        this.matiere_nom = matiere_nom;
    }

    public Float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    public Integer getNb_absences() {
        return nb_absences;
    }

    public void setNb_absences(Integer nb_absences) {
        this.nb_absences = nb_absences;
    }
}
