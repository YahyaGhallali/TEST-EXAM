package com.mri.ui;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CabinetMetierImpl implements ICabinetMetier {
    private static final String afficherListePatientsQuery = "SELECT * FROM patient;";
    private static final String rechercherPatientsQuery = "SELECT * FROM patient WHERE nom =(?) OR prenom=(?) OR cin=(?);";
    private static final String ajouterPatientQuery = "INSERT INTO patient (id_patient,nom, prenom, cin, telephone, email, date_naissance) VALUES(?, ?, ?, ?, ?, ?, ?);";
    private static final String supprimerPatientQuery = "DELETE FROM patient WHERE id_patient = ?; ";
    private static final String afficherConsultationsPatientQuery = "SELECT * FROM consultation WHERE id_patient = ?;";

    private static final String ajouterMedecinQuery = "INSERT INTO medecin (id_medecin, nom, prenom, email, tel) VALUES (?, ?, ?, ?, ?);";
    private static final String afficherListeMedecinsQuery = "SELECT * FROM medecin";
    private static final String supprimerMedecinQuery = "DELETE FROM medecin WHERE id_medecin = ?;";
    private static final String afficherConsultationsMedecinQuery = "SELECT * FROM consultation WHERE id_medecin = ?;";


    private static final String ajouterConsultationQuery = "INSERT INTO consultation (id_consultation, date_consultation, id_patient, id_medecin) VALUES (?, ?, ?, ?);";
    private static final String afficherListeConsultationsQuery = "SELECT * FROM consultation";
    private static final String supprimerConsultationQuery = "DELETE FROM consultation WHERE id_consultation = ?;";


    @Override
    public List<Patient> afficherListePatients() {
        List<Patient> patients = new ArrayList<Patient>();
        try (Connection connection = SignletonConnexionDB.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(afficherListePatientsQuery);
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_patient");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String cin = resultSet.getString("cin");
                String telephone = resultSet.getString("telephone");
                String email = resultSet.getString("email");
                Date date_naissance = resultSet.getDate("date_naissance");

                Patient patient = new Patient(id, nom, prenom, cin, telephone, email, date_naissance);
                patients.add(patient);
            }
            return patients;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<Patient> rechercherPatients(String motCle) {
        List<Patient> patients = new ArrayList<>();
        try (
                Connection connection = SignletonConnexionDB.getConnexion();
                PreparedStatement preparedStatement = connection.prepareStatement(rechercherPatientsQuery);

        ) {
            preparedStatement.setString(1, motCle);
            preparedStatement.setString(2, motCle);
            preparedStatement.setString(3, motCle);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_patient");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String cin = resultSet.getString("cin");
                String telephone = resultSet.getString("telephone");
                String email = resultSet.getString("email");
                Date date_naissance = resultSet.getDate("date_naissance");

                Patient patient = new Patient(id, nom, prenom, cin, telephone, email, date_naissance);
                patients.add(patient);
            }
            return patients;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return List.of();
    }

    @Override
    public Boolean ajouterPatient(Patient patient) {
        try (
                Connection connection = SignletonConnexionDB.getConnexion();
                PreparedStatement preparedStatement = connection.prepareStatement(ajouterPatientQuery);
        ) {
            preparedStatement.setInt(1, patient.getId_patient());
            preparedStatement.setString(2, patient.getNom());
            preparedStatement.setString(3, patient.getPrenom());
            preparedStatement.setString(4, patient.getCin());
            preparedStatement.setString(5, patient.getTelephone());
            preparedStatement.setString(6, patient.getEmail());
            preparedStatement.setDate(7, patient.getDate_naissance());

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean supprimerPatient(Integer id_patient) {
        try (Connection connection = SignletonConnexionDB.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(supprimerPatientQuery)) {
            preparedStatement.setInt(1, id_patient);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Consultation> afficherConsultationsPatient(Integer id) {
        List<Consultation> consultations = new ArrayList<>();
        try (Connection connection = SignletonConnexionDB.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(afficherConsultationsPatientQuery))
        {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id_consultation = resultSet.getInt("id_consultation");
                Date date_consultation = resultSet.getDate("date_consultation");
                Integer id_patient = resultSet.getInt("id_medecin");
                Integer id_medecin = resultSet.getInt("date_consultation");

                consultations.add(new Consultation(id_consultation, date_consultation, id_medecin, id_patient));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consultations;
    }

    @Override
    public Boolean ajouterMedecin(Medecin medecin) {
        try (Connection connection = SignletonConnexionDB.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(ajouterMedecinQuery)) {
            preparedStatement.setInt(1, medecin.getId_medecin());
            preparedStatement.setString(2, medecin.getNom());
            preparedStatement.setString(3, medecin.getPrenom());
            preparedStatement.setString(4, medecin.getEmail());
            preparedStatement.setString(5, medecin.getTel());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Medecin> afficherListeMedecins() {
        List<Medecin> medecins = new ArrayList<>();
        try (Connection connection = SignletonConnexionDB.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(afficherListeMedecinsQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_medecin");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                String tel = resultSet.getString("tel");

                medecins.add(new Medecin(id, nom, prenom, email,tel));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return medecins;
    }

    @Override
    public Boolean supprimerMedecin(Integer id_medecin) {
        try (Connection connection = SignletonConnexionDB.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(supprimerMedecinQuery)) {
            preparedStatement.setInt(1, id_medecin);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Consultation> afficherConsultationsMedecin(Integer id_medecin) {
        List<Consultation> consultations = new ArrayList<>();
        try (Connection connection = SignletonConnexionDB.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(afficherConsultationsMedecinQuery)) {
            preparedStatement.setInt(1, id_medecin);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_consultation");
                Date date_consultation = resultSet.getDate("date_consultation");
                Integer patientId = resultSet.getInt("id_patient");
                Integer medecinId = resultSet.getInt("id_medecin");

                consultations.add(new Consultation(id, date_consultation, medecinId, patientId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consultations;
    }

    @Override
    public Boolean ajouterConsultation(Consultation consultation) {
        try (Connection connection = SignletonConnexionDB.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(ajouterConsultationQuery)) {
            preparedStatement.setInt(1, consultation.getId_consultation());
            preparedStatement.setDate(4, consultation.getDate_consultation());
            preparedStatement.setInt(2, consultation.getMedecin().getId_medecin());
            preparedStatement.setInt(3, consultation.getPatient().getId_patient());

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Consultation> afficherListeConsultations() {
        List<Consultation> consultations = new ArrayList<>();
        try (Connection connection = SignletonConnexionDB.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(afficherListeConsultationsQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_consultation");
                Date date = resultSet.getDate("date_consultation");
                Integer patientId = resultSet.getInt("id_patient");
                Integer medecinId = resultSet.getInt("id_medecin");


                consultations.add(new Consultation(id, date,medecinId,patientId ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consultations;
    }

    @Override
    public Boolean supprimerConsultation(Integer id_consultation) {
        try (Connection connection = SignletonConnexionDB.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(supprimerConsultationQuery)) {
            preparedStatement.setInt(1, id_consultation);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
