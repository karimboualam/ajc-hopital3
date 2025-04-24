package model;

import java.util.ArrayList;
import java.util.List;

public class Hopital {

    private static Hopital instance;
    
    private List<Patient> fileAttente;
    private Salle salle1, salle2;

    private Hopital() {
        fileAttente = new ArrayList<>();
        salle1 = new Salle(1);
        salle2 = new Salle(2);
    }

    public static Hopital getInstance() {
        if (instance == null) {
            instance = new Hopital();
        }
        return instance;
    }

    public void ajouterPatientFile(Patient p) {
        fileAttente.add(p);
    }

    public List<Patient> getFileAttente() {
        return fileAttente;
    }

    public Patient retirerPremierPatient() {
        return fileAttente.isEmpty() ? null : fileAttente.remove(0);
    }

    public Salle getSalle(int num) {
        return (num == 1) ? salle1 : salle2;
    }
} 
