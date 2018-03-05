package SistemDeFacturiFiscale;

import java.util.Comparator;

public class ComparatorSuma implements Comparator<Magazin>{

	@Override
	public int compare(Magazin m1, Magazin m2) {
		if (m1.getTotalFaraTaxe() > m2.getTotalFaraTaxe())
			return 1;
		if (m1.getTotalFaraTaxe() < m2.getTotalFaraTaxe())
			return -1;
		return 0;
	}

}
