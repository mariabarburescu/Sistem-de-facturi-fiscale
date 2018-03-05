package SistemDeFacturiFiscale;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class Statistici extends JPanel {

	public Statistici(CardLayout layout, JPanel contentPane) {
		setLayout(null);
		
		StringBuilder afisare;
		
		JTextPane magazinVanzariMax = new JTextPane();
		magazinVanzariMax.setBounds(12, 12, 224, 92);
		add(magazinVanzariMax);
		
		Magazin max = Gestiune.getInstance().getMagazinVanzariMax();
		afisare = new StringBuilder();
		
		afisare.append("Denumire: " + max.nume + "\n");
		afisare.append("Total fara taxe: " + max.getTotalFaraTaxe() + "\n");
		afisare.append("Total cu taxe: " + max.getTotalCuTaxe() + "\n");
		afisare.append("Total cu taxe scutite: " + max.getTotalCuTaxeScutite() + "\n");
		magazinVanzariMax.setText(afisare.toString());
		
		JTextPane magazinVanzariMaxTara = new JTextPane();
		magazinVanzariMaxTara.setBounds(275, 158, 224, 277);
		add(magazinVanzariMaxTara);
		
		Vector<String> tari = new Vector<>();
		tari = Gestiune.getInstance().getTari();
		afisare = new StringBuilder();
		for (int i = 0; i < tari.size(); i++) {
			max = Gestiune.getInstance().getMagazin();
			for (int j = 1; j < Gestiune.getInstance().getMagazine().size(); j++) {
				Double current = ((Magazin) Gestiune.getInstance().getMagazine().get(j)).getTotalTaraCuTaxe(tari.get(i));
				if (current > max.getTotalTaraCuTaxe(tari.get(i)))
					max = (Magazin)Gestiune.getInstance().getMagazine().get(j);
			}
			afisare.append("Tara: " + tari.get(i) + "\n");
			afisare.append("Denumire: " + max.nume + "\n");
			afisare.append("Total fara taxe: " + max.getTotalFaraTaxe() + "\n");
			afisare.append("Total cu taxe: " + max.getTotalCuTaxe() + "\n");
			afisare.append("Total cu taxe scutite: " + max.getTotalCuTaxeScutite() + "\n\n");
			magazinVanzariMaxTara.setText(afisare.toString());	
		}
		
		JTextPane magazinVanzariMaxCategorie = new JTextPane();
		magazinVanzariMaxCategorie.setBounds(12, 158, 224, 277);
		add(magazinVanzariMaxCategorie);
		
		Vector<String> categorii = new Vector<>();
		categorii = Gestiune.getInstance().getCategorii();
		afisare = new StringBuilder();
		for (int i = 0; i < categorii.size(); i++) {
			max = Gestiune.getInstance().getMagazin();
			for (int j = 1; j < Gestiune.getInstance().getMagazine().size(); j++) {
				Double current = ((Magazin) Gestiune.getInstance().getMagazine().get(j)).getTotalCategorieCuTaxe(categorii.get(i));
				if (current > max.getTotalCategorieCuTaxe(categorii.get(i)))
					max = (Magazin)Gestiune.getInstance().getMagazine().get(j);
			}
			afisare.append("Categorie: " + categorii.get(i) + "\n");
			afisare.append("Denumire: " + max.nume + "\n");
			afisare.append("Total fara taxe: " + max.getTotalFaraTaxe() + "\n");
			afisare.append("Total cu taxe: " + max.getTotalCuTaxe() + "\n");
			afisare.append("Total cu taxe scutite: " + max.getTotalCuTaxeScutite() + "\n\n");
			magazinVanzariMaxCategorie.setText(afisare.toString());	
		}
		
		
		JTextPane facturaMax = new JTextPane();
		facturaMax.setBounds(275, 12, 224, 92);
		add(facturaMax);
		
		afisare = new StringBuilder();
		Factura maxim = ((Magazin)Gestiune.getInstance().getMagazine().get(0)).facturi.get(0);
		for (int i = 0; i < Gestiune.getInstance().getMagazine().size(); i++)
			for (int j = 0; j < ((Magazin)Gestiune.getInstance().getMagazine().get(i)).facturi.size(); j++) {
				Double current = ((Magazin)Gestiune.getInstance().getMagazine().get(i)).facturi.get(j).getTotalFaraTaxe();
				if (current > max.getTotalFaraTaxe())
					maxim = ((Magazin)Gestiune.getInstance().getMagazine().get(0)).facturi.get(j);
			}
		afisare.append(maxim.denumire + "\n");
		afisare.append("Total fara taxe: " + maxim.getTotalFaraTaxe() + "\n");
		afisare.append("Total cu taxe: " + maxim.getTotalCuTaxe() + "\n");
		facturaMax.setText(afisare.toString());
				
		JButton btnInapoi = new JButton("Back");
		btnInapoi.setBounds(197, 116, 117, 25);
		add(btnInapoi);
		btnInapoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
					layout.show(contentPane, "choice");
			}
		});

	}
}
