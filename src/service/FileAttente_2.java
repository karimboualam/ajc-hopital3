package service;

import model.Patient;

import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;

public class FileAttente_2 implements Observable {
	
    private Queue<Patient> file;
    private List<Observer> observers;

    public FileAttente_2() {
        file = new LinkedList<>();
        observers = new ArrayList<>();
    }

    public void ajouterPatient(Patient patient) {
        file.add(patient);
        notifyObservers();  // Notifie que la file a changé (nouveau patient dispo)
    }

    public Patient prochainPatient() {
        return file.peek();  // Affiche sans retirer
    }

    public Patient retirerPatient() {
        return file.poll();  // Retire et retourne le patient
    }

    public void afficherFile() {
        if (file.isEmpty()) {
            System.out.println("La file est vide.");
        } else {
            file.forEach(System.out::println);
        }
    }

    // Implémentation Observable
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        Patient prochain = prochainPatient();
        for (Observer obs : observers) {
            obs.update(prochain);
        }
    }
}

