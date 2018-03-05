package SistemDeFacturiFiscale;

public class HyperMarket extends Magazin{
	
	public HyperMarket(String nume) {
		super(nume);
		tipMagazin = "HyperMarket";
	}
		
	public double calculScutiriTaxe() {
		for (int i = 0; i < facturi.size(); i++)
		{
			Factura f = facturi.get(i);
			if (f.getTotalCuTaxe() > 0.1 * this.getTotalCuTaxe())
				return 0.01;
		}
		return 0;
	}
}
