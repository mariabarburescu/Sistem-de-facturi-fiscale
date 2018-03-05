package SistemDeFacturiFiscale;

import java.util.Vector;

public class MediumMarket extends Magazin{
	
	public MediumMarket(String nume) {
		super(nume);
		tipMagazin = "MediumMarket";
	}
		
	public double calculScutiriTaxe() {
		int n = 0;
		Vector<String> categorii = new Vector<String>();
		for (int i = 0; i < facturi.size(); i++)
		{
			Factura f = facturi.get(i);
			for (int j = 0; j < f.produse.size(); j++)
				if (categorii.contains(f.produse.get(j).getProdus().getCategorie()) != true) {
					categorii.add(f.produse.get(j).getProdus().getCategorie());
					if (this.getTotalCategorieCuTaxe(categorii.get(n++)) > 0.5 * this.getTotalCuTaxe())
						return 0.05;
				}
		}
		return 0;
	}
}