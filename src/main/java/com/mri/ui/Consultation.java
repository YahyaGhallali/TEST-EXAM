package com.mri.ui;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Consultation {
    private Integer id_consultation;
    private Date date_consultation;
    private Medecin medecin;
    private Patient patient;

    public Consultation(Integer id_consultation, Date date_consultation, Integer id_medecin, Integer id_patient) {
        this.id_consultation = id_consultation;
        this.date_consultation = date_consultation;

        this.medecin = findMedecin(id_medecin);
        this.patient = findPatient(id_patient);
    }
    private Patient findPatient(Integer idPatient) {
        CabinetMetierImpl cabinetMetier = new CabinetMetierImpl();
        List<Patient> patients = cabinetMetier.afficherListePatients();
        for (Patient patient : patients) {
            if(idPatient == patient.getId_patient()) {
                return patient;
            }
        }
        return null;
    }

    private Medecin findMedecin(Integer idMedecin) {
        CabinetMetierImpl cabinetMetier = new CabinetMetierImpl();
        List<Medecin> medecins = cabinetMetier.afficherListeMedecins();
        for (Medecin medecin : medecins) {
            if(idMedecin == medecin.getId_medecin()) {
                return medecin;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "id_consultation=" + id_consultation +
                ", date_consultation=" + date_consultation +
                ", medecin=" + medecin +
                ", patient=" + patient +
                '}';
    }

    public Integer getId_consultation() {
        return id_consultation;
    }

    public void setId_consultation(Integer id_consultation) {
        this.id_consultation = id_consultation;
    }

    public Date getDate_consultation() {
        return date_consultation;
    }

    public void setDate_consultation(Date date_consultation) {
        this.date_consultation = date_consultation;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
