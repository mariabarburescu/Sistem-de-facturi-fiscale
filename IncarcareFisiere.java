package SistemDeFacturiFiscale;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class IncarcareFisiere extends JPanel {
	private JTextField produse;
	private JTextField taxe;
	private JTextField magazine;
	
	File file_taxe;
	File file_produse;
	File file_magazine;
	
	public IncarcareFisiere(CardLayout layout, JPanel contentPane) {
		setLayout(null);
		
		JButton btnTaxe = new JButton("Taxe");
		btnTaxe.setBounds(51, 38, 117, 25);
		add(btnTaxe);
		btnTaxe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				JFileChooser f = new JFileChooser();
				JButton open = new JButton();
				f.setDialogTitle("Incarcare date...");
				f.setDialogType(JFileChooser.OPEN_DIALOG);
				f.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				if (f.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
					taxe.setText(f.getSelectedFile().getAbsolutePath());
					file_taxe = f.getSelectedFile();
				}
			}
		});
		
		JButton btnProduse = new JButton("Produse");
		btnProduse.setBounds(51, 139, 117, 25);
		add(btnProduse);
		btnProduse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				JFileChooser f = new JFileChooser();
				JButton open = new JButton();
				f.setDialogTitle("Incarcare date...");
				f.setDialogType(JFileChooser.OPEN_DIALOG);
				f.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
				if (f.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
					produse.setText(f.getSelectedFile().getAbsolutePath());
					file_produse = f.getSelectedFile();
				}	
			}
		});
		
		JButton btnMagazine = new JButton("Magazine");
		btnMagazine.setBounds(51, 232, 117, 25);
		add(btnMagazine);
		btnMagazine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				JFileChooser f = new JFileChooser();
				JButton open = new JButton();
				f.setDialogTitle("Incarcare date...");
				f.setDialogType(JFileChooser.OPEN_DIALOG);
				f.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
				if (f.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
					magazine.setText(f.getSelectedFile().getAbsolutePath());
					file_magazine = f.getSelectedFile();
				}
			}
		});
		
		JButton btnGenereazaOutput = new JButton("Genereaza output");
		btnGenereazaOutput.setBounds(279, 331, 164, 25);
		add(btnGenereazaOutput);
		btnGenereazaOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				Task1 genOut = new Task1(file_taxe, file_produse, file_magazine);
				try {
					genOut.afisare(file_taxe, file_produse, file_magazine);
					Administrare administrare = new Administrare(layout, contentPane, file_produse);
					Statistici statistici = new Statistici(layout, contentPane);
					Search search = new Search(layout, contentPane);
					AddProdus add = new AddProdus(layout, contentPane, file_produse);
					Delete delete = new Delete(layout, contentPane, file_produse);
					Edit edit = new Edit(layout, contentPane, file_produse);
					contentPane.add(administrare, "administrare");
					contentPane.add(statistici, "statistici");
					contentPane.add(search, "search");
					contentPane.add(add, "add");
					contentPane.add(delete, "delete");
					contentPane.add(edit, "edit");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		produse = new JTextField();
		produse.setBounds(279, 142, 114, 19);
		add(produse);
		produse.setColumns(10);
		
		taxe = new JTextField();
		taxe.setBounds(279, 41, 114, 19);
		add(taxe);
		taxe.setColumns(10);
		
		magazine = new JTextField();
		magazine.setBounds(279, 235, 114, 19);
		add(magazine);
		magazine.setColumns(10);
		
		JButton btnInapoi = new JButton("Back");
		btnInapoi.setBounds(51, 331, 117, 25);
		add(btnInapoi);
		btnInapoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
					layout.show(contentPane, "choice");
			}
		});
	}
}
