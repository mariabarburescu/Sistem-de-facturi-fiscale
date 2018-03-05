package SistemDeFacturiFiscale;

import java.util.Comparator;

public class ComparatorTara implements Comparator<Produs>{

	public int compare(Produs p0, Produs p1) {
		return p0.getTaraOrigine().compareTo(p1.getTaraOrigine());
	}
}
