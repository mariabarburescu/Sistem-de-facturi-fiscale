package SistemDeFacturiFiscale;

import java.util.Vector;

public class MiniMarket extends Magazin{
	
	public MiniMarket(String nume) {
		super(nume);
		tipMagazin = "MiniMarket";
	}
	
	public double calculScutiriTaxe() {
		int n = 0;
		Vector<String> tari = new Vector<String>();
		for (int i = 0; i < facturi.size(); i++) {
			Factura f = facturi.get(i);
			for (int j = 0; j < f.produse.size(); j++)
				if (tari.contains(f.produse.get(j).getProdus().getTaraOrigine()) != true) //Daca tara nu se afla in vectorul de tari
				{
					tari.add(f.produse.get(j).getProdus().getTaraOrigine()); 
					if (this.getTotalTaraCuTaxe(tari.get(n++)) > 0.5 * this.getTotalCuTaxe())
						return 0.1;
				}
		}
		return 0;
	}
}