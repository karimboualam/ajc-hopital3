package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Authentification;

public class DAOAuthentification {

    public Authentification authenticate(String login, String password) throws ClassNotFoundException, SQLException {
    	Authentification auth = null;

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hopital", "root", "root");

        String sql = "SELECT * FROM authentification WHERE login = ? AND password = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, login);
        ps.setString(2, password); 

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
        	int id=rs.getInt("id");
            String dbLogin = rs.getString("login");
            String nom = rs.getString("nom");
            int role = rs.getInt("metier"); 
            auth= new Authentification(id, dbLogin, nom, role); 
        }

        conn.close();
        return auth; 
    }
}
