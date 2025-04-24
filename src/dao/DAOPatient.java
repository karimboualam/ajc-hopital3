package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Patient;

public class DAOPatient {

    public Patient save(Patient patient) throws ClassNotFoundException, SQLException {
    	Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hopital", "root", "root");

        String sql = "INSERT INTO patients (id, nom, prenom, age, telephone, adresse) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, patient.getId());
        ps.setString(2, patient.getNom());
        ps.setString(3, patient.getPrenom());
        ps.setInt(4, patient.getAge());
        ps.setString(5, patient.getTelephone());
        ps.setString(6, patient.getAdresse());

        ps.executeUpdate();

        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
            patient.setId(generatedKeys.getInt(1));
        }

        conn.close();

        return patient;
    }

    public Patient findByID(int id) throws ClassNotFoundException, SQLException {
        Patient patient = null;
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hopital", "root", "root");

        String sql = "SELECT id, nom, prenom, age, telephone, adresse FROM patients WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            patient = new Patient();
            patient.setId(rs.getInt("id"));
            patient.setNom(rs.getString("nom"));
            patient.setPrenom(rs.getString("prenom"));
            patient.setAge(rs.getInt("age"));
            patient.setTelephone(rs.getString("telephone"));
            patient.setAdresse(rs.getString("adresse"));
        }

        conn.close();
        return patient;
    }

    public List<Patient> findAll() throws ClassNotFoundException, SQLException {
        ArrayList<Patient> liste = new ArrayList<>();

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hopital", "root", "root");

        String sql = "SELECT id, nom, prenom, age, telephone, adresse FROM patients";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            Patient p = new Patient(); 
            p.setId(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setAge(rs.getInt("age"));
            p.setTelephone(rs.getString("telephone"));
            p.setAdresse(rs.getString("adresse"));
            liste.add(p);
        }
        conn.close();
        return liste;
    }
}