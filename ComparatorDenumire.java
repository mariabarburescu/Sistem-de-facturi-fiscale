package SistemDeFacturiFiscale;

import java.util.Comparator;

public class ComparatorDenumire implements Comparator<Produs>{

	@Override
	public int compare(Produs p0, Produs p1) {
		return p0.getDenumire().compareTo(p1.getDenumire());
	}

}
