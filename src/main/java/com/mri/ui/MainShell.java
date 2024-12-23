
package com.mri.ui;

import java.sql.Date;
import java.util.Scanner;

public class MainShell {
    private static final CabinetMetierImpl cabinetMetier = new CabinetMetierImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Bienvenue au Cabinet");
            System.out.println("1. Gérer les Patients");
            System.out.println("2. Gérer les Médecins");
            System.out.println("3. Gérer les consultations");
            System.out.println("0. Quitter");

            System.out.print("Choisissez une option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    gererPatients();
                    break;
                case 2:
                    gererMedecins();
                    break;
                case 3:
                    gererConsultations();
                case 0:
                    System.out.println("Programme terminé");
                    break;
                default:
                    System.out.println("Choisissez une option valide");
            }
        }
    }

    private static void gererPatients() {
        while (true) {
            System.out.println("1.Afficher la liste des patients");
            System.out.println("2.Rechercher des patients par mot Clé.");
            System.out.println("3.Ajouter un patient.");
            System.out.println("4.Supprimer un patient.");
            System.out.println("5.Afficher la liste des consultations d’un patient.");
            System.out.println("0. Retour au menu principal");
            System.out.print("Choisissez une option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    afficherListePatients();
                    break;
                case 2:
                    rechercherPatient();
                    break;
                case 3:
                    ajouterPatient();
                    break;
                case 4:
                    supprimerPatient();
                    break;
                case 5:
                    consultationPatient();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Option invalide !");
            }
        }
    }

    private static void afficherListePatients() {
        System.out.println("Liste des patients");
        System.out.println(cabinetMetier.afficherListePatients());
    }

    private static void ajouterPatient() {
        System.out.println("Ajouter un nouveau patient:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Prénom: ");
        String prenom = scanner.nextLine();
        System.out.print("CIN: ");
        String cin = scanner.nextLine();
        System.out.print("Téléphone: ");
        String telephone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Date de naissance (YYYY-MM-DD): ");
        String dateNaissance = scanner.nextLine();

        Patient patient = new Patient(id, nom, prenom, cin, telephone, email, Date.valueOf(dateNaissance));
        if (cabinetMetier.ajouterPatient(patient)) {
            System.out.println("Patient ajouté avec succès !");
        } else {
            System.out.println("Erreur lors de l'ajout du patient.");
        }
    }

    private static void supprimerPatient() {
        System.out.print("ID du patient à supprimer: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (cabinetMetier.supprimerPatient(id)) {
            System.out.println("Patient supprimé avec succès !");
        } else {
            System.out.println("Erreur lors de la suppression du patient.");
        }
    }

    private static void consultationPatient() {
        System.out.print("ID du patient: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Consultations:");
        System.out.println(cabinetMetier.afficherConsultationsPatient(id));
    }

    private static void rechercherPatient() {
        System.out.println("Rechercher un patient par mot Clé:");
        String motClé = scanner.nextLine();
        System.out.println(cabinetMetier.rechercherPatients(motClé));
    }

    private static void gererMedecins() {
        while (true) {
            System.out.println("1. Afficher la liste des médecins");
            System.out.println("2. Ajouter un médecin");
            System.out.println("3. Supprimer un médecin");
            System.out.println("4. Afficher les consultations d’un médecin");
            System.out.println("0. Retour au menu principal");
            System.out.print("Choisissez une option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Liste des médecins:");
                    System.out.println(cabinetMetier.afficherListeMedecins());
                    break;
                case 2:
                    ajouterMedecin();
                    break;
                case 3:
                    supprimerMedecin();
                    break;
                case 4:
                    consultationMedecin();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Option invalide !");
            }
        }
    }

    private static void ajouterMedecin() {
        System.out.println("Ajouter un nouveau médecin:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Prénom: ");
        String prenom = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Téléphone: ");
        String tel = scanner.nextLine();

        Medecin medecin = new Medecin(id, nom, prenom, email, tel);
        if (cabinetMetier.ajouterMedecin(medecin)) {
            System.out.println("Médecin ajouté avec succès !");
        } else {
            System.out.println("Erreur lors de l'ajout du médecin.");
        }
    }

    private static void supprimerMedecin() {
        System.out.print("ID du médecin à supprimer: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (cabinetMetier.supprimerMedecin(id)) {
            System.out.println("Médecin supprimé avec succès !");
        } else {
            System.out.println("Erreur lors de la suppression du médecin.");
        }
    }

    private static void consultationMedecin() {
        System.out.print("ID du médecin: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Consultations:");
        System.out.println(cabinetMetier.afficherConsultationsMedecin(id));
    }

    private static void gererConsultations() {
        while (true) {
            System.out.println("1. Afficher la liste des consultations");
            System.out.println("2. Ajouter une consultation");
            System.out.println("3. Supprimer une consultation");
            System.out.println("0. Retour au menu principal");
            System.out.print("Choisissez une option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Liste des consultations:");
                    System.out.println(cabinetMetier.afficherListeConsultations());
                    break;
                case 2:
                    ajouterConsultation();
                    break;
                case 3:
                    supprimerConsultation();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Option invalide !");
            }
        }
    }

    private static void ajouterConsultation() {
        System.out.println("Ajouter une nouvelle consultation:");
        System.out.print("ID de la consultation: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Date de consultation (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("ID du patient: ");
        int idPatient = scanner.nextInt();
        System.out.print("ID du médecin: ");
        int idMedecin = scanner.nextInt();

        Consultation consultation = new Consultation(id, Date.valueOf(date), idMedecin, idPatient);
        if (cabinetMetier.ajouterConsultation(consultation)) {
            System.out.println("Consultation ajoutée avec succès !");
        } else {
            System.out.println("Erreur lors de l'ajout de la consultation.");
        }
    }

    private static void supprimerConsultation() {
        System.out.print("ID de la consultation à supprimer: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (cabinetMetier.supprimerConsultation(id)) {
            System.out.println("Consultation supprimée avec succès !");
        } else {
            System.out.println("Erreur lors de la suppression de la consultation.");
        }
    }

}
