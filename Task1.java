package SistemDeFacturiFiscale;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

public class Task1 {
	File fisierTaxe;
	File fisierProduse;
	File fisierMagazine;
	
	public Task1(File produse) {
		this.fisierProduse = produse;
	}
	
	public Task1(File taxe, File produse, File magazine) {
		this.fisierTaxe = taxe;
		this.fisierProduse = produse;
		this.fisierMagazine = magazine;
	}
	
	public TreeMap<String, HashMap<String, Double>> readTaxe(File fisierTaxe) {
		TreeMap<String, HashMap<String, Double>> taxe = new TreeMap<>();
		int firstLine = 1;
		String s;
		try {
			Vector<String> tari = new Vector<String>();
			BufferedReader br =  new BufferedReader(new FileReader(fisierTaxe));
			while ((s = br.readLine()) != null) {
				String[] r = s.split(" ");
				//Citesc tarile
				if (firstLine == 1) {
					for (int i = 1; i < r.length; i++) {
						tari.add(r[i]);
						taxe.put(r[i], new HashMap<String, Double>());
					}
					firstLine = 0;
				}
				//Citesc categoriile si procentele
				else {
					String categorie = r[0];
					for (int i = 1; i < r.length; i++) {
						Double procent = Double.parseDouble(r[i]);
						taxe.get(tari.get(i-1)).put(categorie, procent);
					}
				}
			}
//			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return taxe;
	}
	
	public ArrayList<Produs> readProduse(File fisierProduse) {
		ArrayList<Produs> produse = new ArrayList<>();
		int firstLine = 1;
		String s;
		try {
			Vector<String> tari = new Vector<String>();
			BufferedReader br =  new BufferedReader(new FileReader(fisierProduse));
			while ((s = br.readLine()) != null) {
				String[] r = s.split(" ");
				//Citesc tarile
				if (firstLine == 1) {
					for (int i = 2; i < r.length; i++)
						tari.add(r[i]);
					firstLine = 0;
				}
				//Citesc produsele
				else {
					String denumire = r[0];
					String categorie = r[1];
					for (int i = 2; i < r.length; i++) {
						Double pret = Double.parseDouble(r[i]);
						Produs produs = new Produs(denumire, categorie, tari.get(i-2), pret);
						if (pret != 0.0)
							produse.add(produs);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return produse;
	}
	
	public ArrayList<Magazin> readMagazine(File fisierMagazine) {
		ArrayList<Magazin> magazine = new ArrayList<>();
		String s;
		try {
			BufferedReader f =  new BufferedReader(new FileReader(fisierMagazine));
			int nrMagazine = -1;
			int nrFacturi = -1;
			while ((s = f.readLine()) != null) {
				if (s.equals("")) {
					s = f.readLine();
					StringTokenizer st = new StringTokenizer(s," :");
					String t = st.nextToken();
					//Avem un magazin nou
					if (t.equals("Magazin")) {
						String tipMagazin = st.nextToken();
						String nume = st.nextToken();
						MagazinFactory magazin = new MagazinFactory();
						magazine.add(magazin.creareMagazin(tipMagazin, nume));
						nrMagazine++;
						nrFacturi = -1;
					}
					//Avem o alta factura
					else {
						Factura factura = new Factura(t);
						magazine.get(nrMagazine).addFactura(factura);
						nrFacturi++;
					}
				}
				else {
					StringTokenizer st = new StringTokenizer(s," :");
					while (st.hasMoreTokens()) {
						String t = st.nextToken();
						//Avem un magazin nou
						if (t.equals("Magazin")) {
							String tipMagazin = st.nextToken();
							String nume = st.nextToken();
							MagazinFactory magazin = new MagazinFactory();
							magazine.add(magazin.creareMagazin(tipMagazin, nume));
							nrMagazine++;
							nrFacturi = -1;
						}
						else {
							if (t.equals("DenumireProdus"))
								s = f.readLine();
							st = new StringTokenizer(s, " :");
							String denumire = st.nextToken();
							String taraOrigine = st.nextToken();
							int cantitate = Integer.parseInt(st.nextToken());
							Produs produs = new Produs(denumire, null, taraOrigine, 0);
							ProdusComandat produsComandat = new ProdusComandat(produs, cantitate, 0.0);
							magazine.get(nrMagazine).facturi.get(nrFacturi).addProdus(produsComandat);
						}
					}
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return magazine;
	}
	
	public void afisare(File fisTaxe, File fisProduse, File fisMagazine) throws IOException {
		Task1 citire = new Task1(fisTaxe, fisProduse, fisMagazine);
		ArrayList<Produs> produse = new ArrayList<>();
		ArrayList<Magazin> magazine = new ArrayList<>();
		TreeMap<String, HashMap<String, Double>> taxe = new TreeMap<>();
		produse = citire.readProduse(citire.fisierProduse);
		magazine = citire.readMagazine(citire.fisierMagazine);
		taxe = citire.readTaxe(citire.fisierTaxe);
		Gestiune.getInstance().setMagazine(magazine);
		Gestiune.getInstance().setProduse(produse);
		Gestiune.getInstance().setTaxe(taxe);
		Gestiune.getInstance().afisare(citire.fisierTaxe);
	}	
}
