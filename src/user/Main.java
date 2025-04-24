package user;

import dao.DAOAuthentification;
import service.SecretaireService;
import service.MedecinService;
import model.Authentification;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DAOAuthentification daoAuth = new DAOAuthentification();

        while (true) {
            System.out.println("Bienvenue à l'hopital!");
            System.out.print("login: ");
            String login = scanner.nextLine();
            System.out.print("mdp: ");
            String password = scanner.nextLine();

            try {
                Authentification user = daoAuth.authenticate(login, password);

                if (user == null) {
                    System.out.println("Authentification échouée.\n");
                    continue;
                }

                System.out.println("Bienvenue " + user.getNom());

                if (user.getMetier() == 0) { // secrétaire
                    SecretaireService secretaire = new SecretaireService();
                    int choix;
                    do {
                        System.out.println("\nBienvenue dans l'interface Secretaire");
                        System.out.println("1 - Rajouter un patient");
                        System.out.println("2 - Afficher la file d'attente");
                        System.out.println("3 - Afficher les visites d'un patient");
                        System.out.println("4 - Afficher toutes les visites");
                        System.out.println("5 - Afficher toutes les visites d'un médecin");
                        System.out.println("6 - Quitter l'interface secretaire");
                        System.out.print("Entrez votre choix: ");
                        choix = Integer.parseInt(scanner.nextLine());

                        switch (choix) {
                            case 1:
                                secretaire.ajouterPatient();
                                break;
                            case 2:
                                secretaire.afficherFileAttente();
                                break;
                            case 3:
                                System.out.print("ID du patient : ");
                                int idp = Integer.parseInt(scanner.nextLine());
                                secretaire.afficherVisitesPatient(idp);
                                break;
                            case 4:
                                secretaire.afficherToutesVisites();
                                break;
                            case 5:
                                System.out.print("Nom médecin (ex: Medecin1) : ");
                                String nom = scanner.nextLine();
                                secretaire.afficherVisitesMedecin(nom);
                                break;
                            case 6:
                                System.out.println("Fermeture interface Secretaire\n");
                                break;
                            default:
                                System.out.println("Choix invalide.");
                        }
                    } while (choix != 6);

                } else if (user.getMetier() == 1 || user.getMetier() == 2) { // médecin
                    MedecinService medecin = new MedecinService();
                    int choix;
                    do {
                        System.out.println("\nBienvenue dans l'interface Medecin");
                        System.out.println("1 - Afficher la file d'attente");
                        System.out.println("2 - Rendre la salle disponible (prendre le prochain patient)");
                        System.out.println("3 - Sauvegarder la liste des visites en base");
                        System.out.println("4 - Quitter");
                        System.out.print("Veuillez entrer votre choix: ");
                        choix = Integer.parseInt(scanner.nextLine());

                        switch (choix) {
                            case 1:
                                medecin.afficherFileAttente();
                                break;
                            case 2:
                                int salle = user.getMetier(); // 1 ou 2
                                medecin.rendreSalleDisponible(salle);
                                break;
                            case 3:
                                int s = user.getMetier();
                                medecin.sauvegarderVisites(s);
                                break;
                            case 4:
                                System.out.println("Fermeture interface Medecin\n");
                                break;
                            default:
                                System.out.println("Choix invalide.");
                        }
                    } while (choix != 4);
                }

            } catch (Exception e) {
                System.err.println("Erreur: " + e.getMessage());
            }
        }
    }
}
