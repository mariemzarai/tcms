package com.restapi.tcms.model;

public class StagiaireNote {
    private Long id;
    private Float note;

    public StagiaireNote(Long stagiaireId, String nom, String prenom, Float note) {
        this.id = stagiaireId;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }
}
