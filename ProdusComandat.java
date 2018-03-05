package SistemDeFacturiFiscale;

public class ProdusComandat {
	private Produs produs;
	private double taxa;
	private int cantitate;
	
	public ProdusComandat() {
		produs.setDenumire(null);
		produs.setCategorie(null);
		produs.setTaraOrigine(null);
		produs.setPret(0);
		taxa = 0;
		cantitate = 0;		
	}
	
	public ProdusComandat(Produs produs, int cantitate, Double taxa) {
		this.produs = produs;
		this.cantitate = cantitate;
		this.taxa = taxa;
	}
	
	public void setProdus(Produs produs) {
		this.produs = produs;
	}
	
	public Produs getProdus() {
		return this.produs;
	}
	
	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}
	
	public Double getTaxa() {
		return this.taxa;
	}
	
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	
	public int getCantitate() {
		return this.cantitate;
	}
	
	public String toString() {
		return produs.toString() + " : " + cantitate + " : " + taxa + '\n';
	}
}