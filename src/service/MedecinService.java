package service;

import dao.DAOVisite;
import model.Hopital;
import model.Patient;
import model.Salle;
import model.Visite;

public class MedecinService {

    private Hopital hopital = Hopital.getInstance();

    public void rendreSalleDisponible(int numSalle) throws Exception {
        Salle salle = hopital.getSalle(numSalle);
        Patient patient = hopital.retirerPremierPatient();

        if (patient == null) {
            System.out.println("Aucun patient dans la file.");
            return;
        }

        Visite v = new Visite(patient.getId(), salle.getMedecin(), numSalle);
        salle.ajouterVisite(v);
        System.out.println("Patient en salle : " + patient);
    }

    public void afficherFileAttente() {
        for (Patient p : hopital.getFileAttente()) {
            System.out.println(p);
        }
    }

    public void sauvegarderVisites(int numSalle) throws Exception {
        Salle salle = hopital.getSalle(numSalle);
        DAOVisite dao = new DAOVisite();
        dao.saveAll(salle.getListeVisites());
        salle.viderVisites();
        System.out.println("Visites sauvegardées en base.");
    }
} 
