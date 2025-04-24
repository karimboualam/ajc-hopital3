package model;

public class Authentification {

	private int id;
	private String login;
	private String password;
	private String nom;
	private int metier; 
	
	public Authentification() {
	}
	
	public Authentification(int id, String login, String nom , int metier) {
		this.id = id;
		this.login = login;
		//this.password = password;
		this.nom = nom;
		this.metier = metier;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getMetier() {
		return metier;
	}

	public void setMetier(int metier) {
		this.metier = metier;
	}

	@Override
	public String toString() {
		return "Authentification [id =" + id + " , login=" + login + ", nom=" + nom + ", métier=" + metier + "]";
	}
}
