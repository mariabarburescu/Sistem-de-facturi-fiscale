package SistemDeFacturiFiscale;

import java.text.DecimalFormat;
import java.util.Vector;

public abstract class Magazin implements IMagazin{
	public String nume;
	public Vector<Factura> facturi;
	public String tipMagazin;
	
	public Magazin(String nume) {
		this.nume = nume;
		this.tipMagazin = null;
		this.facturi = new Vector<Factura>();
	}
	
	void addFactura(Factura factura) {
		facturi.add(factura);
	}
	
	public double getTotalFaraTaxe() {
		double suma = 0;
		for (int i = 0; i < facturi.size(); i++)
		{
			Factura f = facturi.elementAt(i);
			suma += f.getTotalFaraTaxe();
		}
		suma = Double.parseDouble(new DecimalFormat("##.####").format(suma));
		return suma;
	}
	
    public double getTotalCuTaxe() {
    	double suma = 0;
		for (int i = 0; i < facturi.size(); i++)
		{
			Factura f = facturi.elementAt(i);
			suma += f.getTotalCuTaxe();
		}
		suma = Double.parseDouble(new DecimalFormat("##.####").format(suma));
		return suma;
    }
    
    public double getTotalCuTaxeScutite() {
    	Double suma = this.getTotalCuTaxe() - this.getTotalCuTaxe() * this.calculScutiriTaxe();
    	suma = Double.parseDouble(new DecimalFormat("##.####").format(suma));
		return suma;
    }
    
    public double getTotalTaraFaraTaxe(String taraOrigine) {
    	double suma = 0;
		for (int i = 0; i < facturi.size(); i++)
		{
			Factura f = facturi.elementAt(i);
			suma += f.getTotalTaraFaraTaxe(taraOrigine);
		}
		suma = Double.parseDouble(new DecimalFormat("##.####").format(suma));
		return suma;
    }
    
    public double getTotalTaraCuTaxe(String taraOrigine) {
    	double suma = 0;
		for (int i = 0; i < facturi.size(); i++)
		{
			Factura f = facturi.elementAt(i);
			suma += f.getTotalTaraCuTaxe(taraOrigine);
		}
		suma = Double.parseDouble(new DecimalFormat("##.####").format(suma));
		return suma;
    }
    
    public double getTotalTaraCuTaxeScutite(String taraOrigine) {
    	double suma = this.getTotalTaraCuTaxe(taraOrigine) - this.getTotalTaraCuTaxe(taraOrigine) * this.calculScutiriTaxe();
    	suma = Double.parseDouble(new DecimalFormat("##.####").format(suma));
		return suma;
    }
    
    public double getTotalCategorieCuTaxe(String categorie) {
    	double suma = 0;
    	for (int i = 0; i < facturi.size(); i++) {
    		Factura f = facturi.elementAt(i);
    		suma += f.getTotalCategorieCuTaxe(categorie);
    	}
    	suma = Double.parseDouble(new DecimalFormat("##.####").format(suma));
    	return suma;
    }
    
    public String getNume() {
    	return nume;
    }

	public String toString() {
		String s = new String();
		s = nume + '\n';
		for (int i = 0; i < facturi.size(); i++)
			s += facturi.get(i).toString();
		s += '\n';
		return s;
	}
}