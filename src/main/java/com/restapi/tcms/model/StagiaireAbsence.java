package com.restapi.tcms.model;

public class StagiaireAbsence {
    private Long stagiaireId;
    private Long nbAbsence;

    public StagiaireAbsence(Long stagiaireId, Long nbAbsence) {
        this.stagiaireId = stagiaireId;
        this.nbAbsence = nbAbsence;
    }

    public Long getStagiaireId() {
        return stagiaireId;
    }

    public void setStagiaireId(Long stagiaireId) {
        this.stagiaireId = stagiaireId;
    }

    public Long getNbAbsence() {
        return nbAbsence;
    }

    public void setNbAbsence(Long nbAbsence) {
        this.nbAbsence = nbAbsence;
    }
}
