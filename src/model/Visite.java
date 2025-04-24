package model;

import java.time.LocalDateTime;

public class Visite {

    private int id;
    private int idPatient; 
    private LocalDateTime dateVisite;
    private String medecin;
    private int numSalle;
    private double tarif = 23.0;


    public Visite() {
        this.dateVisite = LocalDateTime.now();
    }

    public Visite(int idPatient, String medecin, int numSalle) {
        this();
        this.idPatient = idPatient;
        this.medecin = medecin;
        this.numSalle = numSalle;
    }

     public Visite(int id, int idPatient, String medecin, int numSalle) {
        this.id = id;
        this.idPatient = idPatient;
        this.dateVisite = LocalDateTime.now();
        this.medecin = medecin;
        this.numSalle = numSalle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public LocalDateTime getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(LocalDateTime dateVisite) {
        this.dateVisite = dateVisite;
    }

    public String getMedecin() {
        return medecin;
    }

    public void setMedecin(String medecin) {
        this.medecin = medecin;
    }

    public int getNumSalle() {
        return numSalle;
    }

    public void setNumSalle(int numSalle) {
        this.numSalle = numSalle;
    }

    public double getTarif() {
        return tarif;
    }
    
    public void setTarif(double tarif){
    	this.tarif=tarif;
    }

    @Override
    public String toString() {
        return "Visite [id=" + id + ", idPatient=" + idPatient + ", "
        		+ "dateVisite=" +dateVisite
                +", nomMedecin=" + medecin + ", numSalle=" + numSalle + ", tarif=" + tarif + "]";
    }
}
