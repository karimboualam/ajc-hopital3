package service;

import dao.DAOVisite;
import dao.DAOPatient;
import log.FichierLog;
import model.Hopital;
import model.Patient;
import model.Visite;

import java.util.List;
import java.util.Scanner;

public class SecretaireService {

    private Hopital hopital = Hopital.getInstance();
    private Scanner scanner = new Scanner(System.in);
    private DAOVisite daoVisite = new DAOVisite();

    public void ajouterPatient() throws Exception {
        System.out.print("Veuillez saisir un identifiant: ");
        int id = Integer.parseInt(scanner.nextLine());

        DAOPatient dao = new DAOPatient();
        Patient patient = dao.findByID(id);

        if (patient == null) {
            System.out.println("Veuillez saisir le nom: ");
            String nom = scanner.nextLine();
            System.out.println("Veuillez saisir le prenom: ");
            String prenom = scanner.nextLine();
            System.out.println("Veuillez saisir l'âge: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Voulez-vous renseigner l'adresse et le numéro de téléphone du patient? oui/non\n");
            String reponse = scanner.nextLine();

            String adresse = "";
            String telephone = "";

            if (reponse.equalsIgnoreCase("oui")) {
                System.out.print("Veuillez saisir votre adresse: ");
                adresse = scanner.nextLine();
                System.out.print("Veuillez saisir votre numero de telephone: ");
                telephone = scanner.nextLine();
            }

            patient = new Patient(id, nom, prenom, age, telephone, adresse);
            dao.save(patient);
        }

        hopital.ajouterPatientFile(patient);
        FichierLog.logArriveePatient(id);
        System.out.println("Patient ajouté à la file d'attente.");
    }

    public void afficherFileAttente() {
        List<Patient> liste = hopital.getFileAttente();
        System.out.println("Nombre de Patient dans la file: " + liste.size());
        System.out.println("Liste des patients dans la salle d'attente:");
        for (Patient p : liste) {
            System.out.println(p);
        }
    }

    public void afficherVisitesPatient(int idPatient) {
        daoVisite.afficherVisitesPatient(idPatient);
    }

    public void afficherToutesVisites() {
        daoVisite.afficherToutesVisites();
    }

    public void afficherVisitesMedecin(String nomMedecin) {
        try {
            List<Visite> visites = daoVisite.findByMedecin(nomMedecin);
            if (visites.isEmpty()) {
                System.out.println("Aucune visite trouvée pour le médecin " + nomMedecin);
            } else {
                for (Visite v : visites) {
                    System.out.println(v);
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des visites du médecin: " + e.getMessage());
        }
    }
}
