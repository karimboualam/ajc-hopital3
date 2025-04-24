package service;

import model.Patient;

import java.util.ArrayList;
import java.util.List;

public class FileAttente {
    private List<Patient> file;

    public FileAttente() {
        this.file = new ArrayList<>();
    }

    // Ajouter un patient � la file (fin de file)
    public void ajouterPatient(Patient patient) {
        file.add(patient);
        System.out.println("Patient ajout� � la file : " + patient.getNom() + " " + patient.getPrenom());
    }

    // Afficher toute la file d�attente
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

    // R�cup�rer le prochain patient sans le retirer
    public Patient prochainPatient() {
        if (!file.isEmpty()) {
            return file.get(0);
        }
        return null;
    }

    // Retirer et retourner le patient en t�te de file
    public Patient retirerPatient() {
        if (!file.isEmpty()) {
            return file.remove(0);
        }
        return null;
    }

    // V�rifier si la file est vide
    public boolean estVide() {
        return file.isEmpty();
    }
    
    public List<Patient> getListe() {
        return file;
    }

}
