package model;

public class Patient {
	 private int id;
	    private String nom;
	    private String prenom;
	    private int age;
	    private String telephone;
	    private String adresse;
	    
	    public Patient(){
	    	
	    }

	    public Patient(int id, String nom, String prenom, int age, String telephone, String adresse) {
	        this.id = id;
	        this.nom = nom;
	        this.prenom = prenom;
	        this.age = age;
	        this.telephone = telephone;
	        this.adresse = adresse;
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		public String getAdresse() {
			return adresse;
		}

		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}

		@Override
		public String toString() {
			return "Patient [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", telephone="
					+ telephone + ", adresse=" + adresse + "]";
		}
	

}
