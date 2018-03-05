package SistemDeFacturiFiscale;

public class Produs {
	private String denumire;
	private String categorie;
	private String taraOrigine;
	private double pret;
	
	public Produs() {
		denumire = null;
		categorie = null;
		taraOrigine = null;
		pret = 0;
	}
	
	public Produs(String denumire, String categorie, String taraOrigine, double pret) {
		this.denumire = denumire;
		this.categorie = categorie;
		this.taraOrigine = taraOrigine;
		this.pret = pret;
}
	
	void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	
	String getDenumire() {
		return this.denumire;
	}
	
	void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	String getCategorie() {
		return this.categorie;
	}
	
	void setTaraOrigine(String taraOrigine) {
		this.taraOrigine = taraOrigine;
	}
	
	String getTaraOrigine() {
		return this.taraOrigine;
	}
	
	void setPret(double pret) {
		this.pret = pret;
	}
	
	double getPret() {
		return this.pret;
	}
	
	public String toString() {
		return new String(denumire + " : " + categorie + " : " + taraOrigine + " : " + pret);		
	}
}