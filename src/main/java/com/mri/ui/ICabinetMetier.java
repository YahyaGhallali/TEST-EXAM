package com.mri.ui;

import com.gluonhq.attach.lifecycle.LifecycleService;

import java.util.List;

public interface ICabinetMetier {

    // Concernant les Patients
    List<Patient> afficherListePatients();
    List<Patient>  rechercherPatients(String motCle);
    Boolean ajouterPatient(Patient patient);
    Boolean supprimerPatient(Integer id_patient);
    List<Consultation> afficherConsultationsPatient(Integer id_patient);



    // Concernant les Médecins
    Boolean ajouterMedecin(Medecin medecin);
    List<Medecin> afficherListeMedecins();
    Boolean supprimerMedecin(Integer id_medecin);
    List<Consultation> afficherConsultationsMedecin(Integer id_medecin);

    // Concernant les Consultations
    Boolean ajouterConsultation(Consultation consultation);
    List<Consultation> afficherListeConsultations();
    Boolean supprimerConsultation(Integer id_consultation);
}

