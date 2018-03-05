package SistemDeFacturiFiscale;

import java.util.Comparator;

public class ComparatorCuTaxe implements Comparator<Magazin>{

	@Override
	public int compare(Magazin m0, Magazin m1) {
		if (m0.getTotalCuTaxe() > m1.getTotalCuTaxe())
			return 1;
		if (m0.getTotalCuTaxe() < m1.getTotalCuTaxe())
			return -1;
		return 0;
	}

}
