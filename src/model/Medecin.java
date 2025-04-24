package model;

public class Medecin {
        private String nom;
        private int numeroSalle;

        public Medecin(String name, int numeroSalle) {
            this.nom = name;
            this.numeroSalle = numeroSalle;
        }

        public String getNom() { return nom; }
        public int getRoomNumber() { return numeroSalle; }
    }

