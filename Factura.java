package SistemDeFacturiFiscale;

import java.text.DecimalFormat;
import java.util.Vector;

public class Factura {
	public String denumire;
	public Vector<ProdusComandat> produse;
	
	public Factura(String denumire){
		this.denumire = denumire;
		produse = new Vector<ProdusComandat>();
	}
	
	public void addProdus(ProdusComandat produs) {
		produse.add(produs);
	}
	
	public double getTotalFaraTaxe() {
		double suma = 0;
		for (int i = 0; i < produse.size(); i++)
		{
			ProdusComandat p = produse.get(i);
			suma += p.getProdus().getPret() * p.getCantitate();
		}
		suma = Double.parseDouble(new DecimalFormat("##.####").format(suma));
		return suma;
	}
	
	public double getTotalCuTaxe() {
		double suma = 0;
		for (int i = 0; i < produse.size(); i++)
		{
			ProdusComandat p = produse.get(i);
			suma += (p.getProdus().getPret() * (100 + p.getTaxa())) / 100 * p.getCantitate();
		}
		suma = Double.parseDouble(new DecimalFormat("##.####").format(suma));
		return suma;
	}

	public double getTaxe() {
		double suma = 0;
		for (int i = 0; i < produse.size(); i++)
		{
			ProdusComandat p = produse.get(i);
			suma += p.getProdus().getPret() * p.getTaxa() / 100  * p.getCantitate();
		}
		suma = Double.parseDouble(new DecimalFormat("##.####").format(suma));
		return suma;
	}
	
	public double getTotalTaraFaraTaxe(String taraOrigine)
    {
        double suma = 0;
        for (int i = 0; i < produse.size(); i++)
        {
        	ProdusComandat p = produse.get(i);
            if (p.getProdus().getTaraOrigine().equals(taraOrigine))
                suma += p.getProdus().getPret() * p.getCantitate();
        }
        suma = Double.parseDouble(new DecimalFormat("##.####").format(suma));
        return suma;
    }
   
    public double getTotalTaraCuTaxe(String taraOrigine)
    {
        double suma = 0;
        for (int i = 0; i < produse.size(); i++)
        {
        	ProdusComandat p = produse.get(i);
            if (p.getProdus().getTaraOrigine().equals(taraOrigine))
               suma += (p.getProdus().getPret() * (100 + p.getTaxa())) / 100 * p.getCantitate();
        }
        suma = Double.parseDouble(new DecimalFormat("##.####").format(suma));
        return suma;
    }
    
    public double getTotalCategorieCuTaxe(String categorie)
    {
        double suma = 0;
        for (int i = 0; i < produse.size(); i++)
        {
        	ProdusComandat p = produse.get(i);
            if (p.getProdus().getCategorie().equals(categorie))
               suma += (p.getProdus().getPret() * (100 + p.getTaxa())) / 100 * p.getCantitate();
        }
        suma = Double.parseDouble(new DecimalFormat("##.####").format(suma));
        return suma;
    }
    
    public double getTaxeTara(String taraOrigine)
    {
        double suma = 0;
        for (int i = 0; i < produse.size(); i++)
        {
        	ProdusComandat p = produse.get(i);
            if (p.getProdus().getTaraOrigine().equals(taraOrigine))
                  suma += p.getProdus().getPret() * p.getTaxa() / 100 * p.getCantitate();
        }
        suma = Double.parseDouble(new DecimalFormat("##.####").format(suma));
        return suma;
    }
    
    public String toString() {
		String s = new String();
		s = denumire + '\n';
		for (int i = 0; i < produse.size(); i++)
			s += produse.get(i).toString();
		s += '\n';
		return s;
	}
}
