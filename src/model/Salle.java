package model;

import java.util.ArrayList;
import java.util.List;

import service.Observer;

public class Salle {
    private int numero;
    private String medecin;
    private List<Visite> listeVisites;

    public Salle(int numero) {
        this.numero = numero;
        this.medecin = "Medecin" + numero;
        this.listeVisites = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public String getMedecin() {
        return medecin;
    }

    public List<Visite> getListeVisites() {
        return listeVisites;
    }

    public void ajouterVisite(Visite v) {
        listeVisites.add(v);
    }

    public void viderVisites() {
        listeVisites.clear();
    }
    /*
    // Pattern Observer
    public void nouveauPatientPrisEnCharge(Patient patient) {
        System.out.println("Salle " + numero + " : patient pris en charge -> " + patient);
        notifyObservers(patient);
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers(Patient patient) {
        for (Observer obs : observers) {
            obs.update(patient);
        }
    }

    // pour compatibilité avec Observable interface
    @Override
    public void notifyObservers() {
        // vide : on passe par la méthode notifyObservers(Patient p)
    }*/
}
