package service;

import model.Patient;

import java.util.ArrayList;
import java.util.List;

public class FileAttente {
    private List<Patient> file;

    public FileAttente() {
        this.file = new ArrayList<>();
    }

    // Ajouter un patient à la file (fin de file)
    public void ajouterPatient(Patient patient) {
        file.add(patient);
        System.out.println("Patient ajouté à la file : " + patient.getNom() + " " + patient.getPrenom());
    }

    // Afficher toute la file d’attente
    public void afficherFile() {
        if (file.isEmpty()) {
            System.out.println("La file d'attente est vide.");
        } else {
            System.out.println("File d'attente :");
            for (Patient patient : file) {
                System.out.println(patient);
            }
        }
    }

    // Récupérer le prochain patient sans le retirer
    public Patient prochainPatient() {
        if (!file.isEmpty()) {
            return file.get(0);
        }
        return null;
    }

    // Retirer et retourner le patient en tête de file
    public Patient retirerPatient() {
        if (!file.isEmpty()) {
            return file.remove(0);
        }
        return null;
    }

    // Vérifier si la file est vide
    public boolean estVide() {
        return file.isEmpty();
    }
    
    public List<Patient> getListe() {
        return file;
    }

}
