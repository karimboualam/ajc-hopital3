package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Visite;

public class DAOVisite {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hopital";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    public Visite save(Visite visite) throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String sql = "INSERT INTO visites (id, id_patient, date, medecin, num_salle, tarif) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, visite.getId());
                ps.setInt(2, visite.getIdPatient());
                ps.setTimestamp(3, Timestamp.valueOf(visite.getDateVisite()));
                ps.setString(4, visite.getMedecin());
                ps.setInt(5, visite.getNumSalle());
                ps.setDouble(6, visite.getTarif());

                ps.executeUpdate();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys != null && generatedKeys.next()) {
                        visite.setId(generatedKeys.getInt(1));
                    }
                }
            }
        }
        return visite;
    }

    public void saveAll(List<Visite> visites) throws ClassNotFoundException, SQLException {
        if (visites == null || visites.isEmpty()) return;

        Class.forName(DB_DRIVER);
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            boolean originalAutoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);

            String sql = "INSERT INTO visites (id, id_patient, date, medecin, num_salle, tarif) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (Visite visite : visites) {
                    ps.setInt(1, visite.getId());
                    ps.setInt(2, visite.getIdPatient());
                    ps.setTimestamp(3, Timestamp.valueOf(visite.getDateVisite()));
                    ps.setString(4, visite.getMedecin());
                    ps.setInt(5, visite.getNumSalle());
                    ps.setDouble(6, visite.getTarif());
                    ps.addBatch();
                }

                ps.executeBatch();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                System.err.println("Rollback effectu√© pour saveAll Visites");
                throw e;
            } finally {
                conn.setAutoCommit(originalAutoCommit);
            }
        }
    }

    public List<Visite> findByPatientId(int patientId) throws ClassNotFoundException, SQLException {
        List<Visite> liste = new ArrayList<>();

        Class.forName(DB_DRIVER);
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement("SELECT id, id_patient, date, medecin, num_salle, tarif FROM visites WHERE id_patient = ?")) {

            ps.setInt(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Visite v = new Visite();
                    v.setId(rs.getInt("id"));
                    v.setIdPatient(rs.getInt("id_patient"));
                    Timestamp ts = rs.getTimestamp("date");
                    v.setDateVisite(ts != null ? ts.toLocalDateTime() : null);
                    v.setMedecin(rs.getString("medecin"));
                    v.setNumSalle(rs.getInt("num_salle"));
                    v.setTarif(rs.getDouble("tarif"));
                    liste.add(v);
                }
            }
        }
        return liste;
    }

    public List<Visite> findByMedecin(String nomMedecin) throws ClassNotFoundException, SQLException {
        List<Visite> liste = new ArrayList<>();

        Class.forName(DB_DRIVER);
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement("SELECT id, id_patient, date, medecin, num_salle, tarif FROM visites WHERE medecin = ?")) {

            ps.setString(1, nomMedecin);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Visite v = new Visite();
                    v.setId(rs.getInt("id"));
                    v.setIdPatient(rs.getInt("id_patient"));
                    Timestamp ts = rs.getTimestamp("date");
                    v.setDateVisite(ts != null ? ts.toLocalDateTime() : null);
                    v.setMedecin(rs.getString("medecin"));
                    v.setNumSalle(rs.getInt("num_salle"));
                    v.setTarif(rs.getDouble("tarif"));
                    liste.add(v);
                }
            }
        }
        return liste;
    }

    public void afficherVisitesPatient(int patientId) {
        try {
            List<Visite> visites = findByPatientId(patientId);
            if (visites.isEmpty()) {
                System.out.println("Aucune visite trouv√©e pour le patient ID: " + patientId);
            } else {
                for (Visite v : visites) {
                    System.out.println(v);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Erreur lors de la r√©cup√©ration des visites: " + e.getMessage());
        }
    }

    /*public void afficherToutesVisites() {
        try {
            List<Visite> visites = findByMedecin(""); 
            if (visites.isEmpty()) {
                System.out.println("Aucune visite enregistÈe.");
            } else {
                for (Visite v : visites) {
                    System.out.println(v);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Erreur lors de la rÈcupÈration des visites: " + e.getMessage());
        }
    }*/
    public void afficherToutesVisites() {
        try {
            Class.forName(DB_DRIVER);
            List<Visite> visites = new ArrayList<>();
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                 PreparedStatement ps = conn.prepareStatement("SELECT * FROM visites");
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Visite v = new Visite();
                    v.setId(rs.getInt("id"));
                    v.setIdPatient(rs.getInt("id_patient"));
                    Timestamp ts = rs.getTimestamp("date");
                    v.setDateVisite(ts != null ? ts.toLocalDateTime() : null);
                    v.setMedecin(rs.getString("medecin"));
                    v.setNumSalle(rs.getInt("num_salle"));
                    v.setTarif(rs.getDouble("tarif"));
                    visites.add(v);
                }
            }

            if (visites.isEmpty()) {
                System.out.println("Aucune visite enregistrÈe.");
            } else {
                for (Visite v : visites) {
                    System.out.println(v);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Erreur lors de la rÈcupÈration des visites: " + e.getMessage());
        }
    }


    public void afficherVisitesMedecin(int medecinId) {
        try {
            List<Visite> visites = findByMedecin("Medecin" + medecinId);  
            if (visites.isEmpty()) {
                System.out.println("Aucune visite trouv√©e pour le m√©decin ID: " + medecinId);
            } else {
                for (Visite v : visites) {
                    System.out.println(v);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Erreur lors de la r√©cup√©ration des visites: " + e.getMessage());
        }
    }

    public void sauvegarderVisites() throws ClassNotFoundException, SQLException {
        List<Visite> visites = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM visites")) {
            
            while (rs.next()) {
                Visite v = new Visite();
                v.setId(rs.getInt("id"));
                v.setIdPatient(rs.getInt("id_patient"));
                Timestamp ts = rs.getTimestamp("date");
                v.setDateVisite(ts != null ? ts.toLocalDateTime() : null);
                v.setMedecin(rs.getString("medecin"));
                v.setNumSalle(rs.getInt("num_salle"));
                v.setTarif(rs.getDouble("tarif"));
                visites.add(v);
            }
        }

        if (!visites.isEmpty()) {
            saveAll(visites);
        }
    }
}
