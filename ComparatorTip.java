package SistemDeFacturiFiscale;

import java.util.Comparator;

public class ComparatorTip implements Comparator<Magazin>{

	@Override
	public int compare(Magazin m1, Magazin m2) {
		if (m1.tipMagazin.equals("MiniMarket") && m2.tipMagazin.equals("MiniMarket"))
			return 0;
		if (m1.tipMagazin.equals("MediumMarket") && m2.tipMagazin.equals("MediumMarket"))
			return 0;
		if (m1.tipMagazin.equals("HyperMarket") && m2.tipMagazin.equals("HyperMarket"))
			return 0;
		if (m1.tipMagazin.equals("MiniMarket"))
			return -1;
		if (m1.tipMagazin.equals("HyperMarket"))
			return 1;
		if (m2.tipMagazin.equals("MiniMarket"))
			return 1;
		if (m2.tipMagazin.equals("HyperMarket"))
			return -1;	
		return 0;
	}
}
