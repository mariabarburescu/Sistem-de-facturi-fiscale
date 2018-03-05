package SistemDeFacturiFiscale;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Vector;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Gestiune<E> {
	public ArrayList<Produs> produse;
	public ArrayList<Magazin> magazine;
	public TreeMap<String, HashMap<String, Double>> taxe;
	public Vector<String> tari;
	public Vector<String> tariReturn;
	public Vector<String> categorii;
	
	private static Gestiune instance = null;
	
	public Gestiune() {
		produse = new ArrayList<>();
		magazine = new ArrayList<>();
		taxe = new TreeMap<>();
		tari = new Vector<>();
		tariReturn = new Vector<>();
		categorii = new Vector<>();
	}
	
	public static Gestiune getInstance() {
		if (instance == null)
			instance = new Gestiune();
		return instance;
	}
	
	void setProduse(ArrayList<Produs> produse) {
		this.produse = produse;
	}
	
	ArrayList<Produs> getProduse(){
		return this.produse;
	}
	
	ArrayList<Magazin> getMagazine(){
		return this.magazine;
	}
	
	TreeMap<String, HashMap<String, Double>> getTaxe(){
		return this.taxe;
	}
	
	Magazin getMagazin() {
		return magazine.get(0);
	}
	
	Magazin getMagazinVanzariMax() {
		Collections.sort(magazine, new ComparatorCuTaxe());
		return magazine.get(0);
	}
	
	Vector<String> getTari() {
		return this.tariReturn;
	}
	
	Vector<String> getCategorii(){
		return this.categorii;
	}
	
	void setMagazine(ArrayList<Magazin> magazine) {
		this.magazine = magazine;
	}
	
	void setTaxe(TreeMap<String, HashMap<String, Double>> taxe) {
		this.taxe = taxe;
	}
	
	public String getCategorieProdus(String produs) {
		for (int i = 0; i < produse.size(); i++)
			if (produse.get(i).getDenumire().equals(produs))
				return produse.get(i).getCategorie();
		return null;
	}
	
	public Double getPretProdus(String produs, String tara) {
		for (int i = 0; i < produse.size(); i++) {
			if (produse.get(i).getDenumire().equals(produs))
				if (produse.get(i).getTaraOrigine().equals(tara))
					return produse.get(i).getPret();
		}
		return 0.0;
	}
	
	public Double getTaxaProdus(String categorie, String tara) {
		return taxe.get(tara).get(categorie);
	}
	
	public void afisare(File fisierTaxe) throws IOException{
		for (int indexMagazine = 0; indexMagazine < magazine.size(); indexMagazine++) {
			for (int indexFacturi = 0; indexFacturi < magazine.get(indexMagazine).facturi.size(); indexFacturi++) {
				for (int indexProduse = 0; indexProduse < magazine.get(indexMagazine).facturi.get(indexFacturi).produse.size(); indexProduse++) {
					String produs = magazine.get(indexMagazine).facturi.get(indexFacturi).produse.get(indexProduse).getProdus().getDenumire();
					String categorie = getCategorieProdus(produs);
					magazine.get(indexMagazine).facturi.get(indexFacturi).produse.get(indexProduse).getProdus().setCategorie(categorie);
					String tara = magazine.get(indexMagazine).facturi.get(indexFacturi).produse.get(indexProduse).getProdus().getTaraOrigine();
					Double pret = getPretProdus(produs, tara);
					magazine.get(indexMagazine).facturi.get(indexFacturi).produse.get(indexProduse).getProdus().setPret(pret);
					Double taxa = getTaxaProdus(categorie, tara);
					magazine.get(indexMagazine).facturi.get(indexFacturi).produse.get(indexProduse).setTaxa(taxa);
				}
			}
		}
		Collections.sort(magazine, new ComparatorSuma());
		Collections.sort(magazine, new ComparatorTip());
		String s;
		int firstLine = 1;
		try {
			BufferedReader f =  new BufferedReader(new FileReader(fisierTaxe));
			while ((s = f.readLine()) != null) {
				String[] r = s.split(" ");
				//Citesc tarile
				if (firstLine == 1) {
					for (int j = 1; j < r.length; j++) {
						tari.add(r[j]);
						tariReturn.add(r[j]);
					}
					firstLine = 0;
				}
				else
					categorii.add(r[0]);
			}
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		Collections.sort(tari);
		RandomAccessFile writer = new RandomAccessFile("out.txt", "rw");
		writer.writeBytes(magazine.get(0).tipMagazin + '\n');
		for (int i = 0; i < magazine.size(); i++) {
			if (i > 0 && (magazine.get(i-1).tipMagazin).compareTo(magazine.get(i).tipMagazin) != 0)
			{
				writer.writeBytes('\n' + magazine.get(i).tipMagazin + '\n');
				writer.writeBytes(magazine.get(i).nume + '\n' + '\n');
			}
			else
				if (i == 0)
					writer.writeBytes(magazine.get(i).nume + '\n' + '\n');
				else
					writer.writeBytes('\n' + magazine.get(i).nume + '\n' + '\n');
			writer.writeBytes("Total " + magazine.get(i).getTotalFaraTaxe() + " " + magazine.get(i).getTotalCuTaxe() + " " + magazine.get(i).getTotalCuTaxeScutite() + '\n' + '\n');
			writer.writeBytes("Tara" + '\n');
			for (int k = 0; k < tari.size(); k++) {
				if (magazine.get(i).getTotalTaraFaraTaxe(tari.get(k)) == 0)
					writer.writeBytes(tari.get(k) + " " + 0 + '\n');
				else
					writer.writeBytes(tari.get(k) + " " + magazine.get(i).getTotalTaraFaraTaxe(tari.get(k)) + " " + magazine.get(i).getTotalTaraCuTaxe(tari.get(k)) + " " + magazine.get(i).getTotalTaraCuTaxeScutite(tari.get(k)) + '\n');
			}
			Collections.sort(magazine.get(i).facturi, new ComparatorFacturi());
			int numarFacturi = magazine.get(i).facturi.size();
			for (int j = 0; j < numarFacturi; j++) {
				writer.writeBytes('\n' + magazine.get(i).facturi.get(j).denumire + '\n' + '\n');
				writer.writeBytes("Total " + magazine.get(i).facturi.get(j).getTotalFaraTaxe() + " " + magazine.get(i).facturi.get(j).getTotalCuTaxe()  + '\n' + '\n');
				writer.writeBytes("Tara" + '\n');
				for (int k = 0; k < tari.size(); k++) {
					if (magazine.get(i).facturi.get(j).getTotalTaraFaraTaxe(tari.get(k)) == 0)
						writer.writeBytes(tari.get(k) + " " + 0 + '\n');
					else
						writer.writeBytes(tari.get(k) + " " + magazine.get(i).facturi.get(j).getTotalTaraFaraTaxe(tari.get(k)) + " " + magazine.get(i).facturi.get(j).getTotalTaraCuTaxe(tari.get(k)) + '\n');
				}
			}	
		}
		writer.close();
	}
}
