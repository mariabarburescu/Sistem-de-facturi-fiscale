package SistemDeFacturiFiscale;

public interface IMagazin {
	public double getTotalFaraTaxe();
    public double getTotalCuTaxe();
    public double getTotalCuTaxeScutite();
    public double getTotalTaraFaraTaxe(String taraOrigine);
    public double getTotalTaraCuTaxe(String taraOrigine);
    public double getTotalTaraCuTaxeScutite(String taraOrigine);
    public double calculScutiriTaxe();
}
