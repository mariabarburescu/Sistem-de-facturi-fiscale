package SistemDeFacturiFiscale;

import java.util.Comparator;

public class ComparatorFacturi implements Comparator<Factura>{
	public int compare(Factura f1, Factura f2) {
		if (f1.getTotalCuTaxe() > f2.getTotalCuTaxe())
			return 1;
		if (f1.getTotalCuTaxe() < f2.getTotalCuTaxe())
			return -1;
		return 0;
	}
}
