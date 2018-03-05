package SistemDeFacturiFiscale;

public class MagazinFactory{
	public Magazin creareMagazin(String tipMagazin, String nume) {
		if (tipMagazin == null)
			return null;
		if (tipMagazin.equals("MiniMarket"))
			return new MiniMarket(nume);
		if (tipMagazin.equals("MediumMarket"))
			return new MediumMarket(nume);
		if (tipMagazin.equals("HyperMarket"))
			return new HyperMarket(nume);
		return null;
	}
}
