package business.entitie;

public class Singer {
	
	private long id;
	private String prenom;
	private String nom;
	private int age;
	private long salaire;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getSalaire() {
		return salaire;
	}
	public void setSalaire(long salaire) {
		this.salaire = salaire;
	}
	
	public Singer(long id, String prenom, String nom, int age, long salaire) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.age = age;
		this.salaire = salaire;
	}
	
	public Singer() {}
	
}