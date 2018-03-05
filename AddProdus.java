package SistemDeFacturiFiscale;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddProdus extends JPanel {


	private JTextField textDenumire;
	private JTextField textCategorie;
	private JTextField textTara;
	private JTextField textPret;

	public ArrayList<Produs> produse = new ArrayList<>();
	
	public AddProdus(CardLayout layout, JPanel contentPane, File file_produse) {
		setLayout(null);
		
		JLabel lblDenumire = new JLabel("Denumire");
		lblDenumire.setBounds(60, 58, 70, 15);
		add(lblDenumire);
		
		JLabel lblTara = new JLabel("Tara");
		lblTara.setBounds(60, 132, 70, 15);
		add(lblTara);
		
		JLabel lblCategorie = new JLabel("Categorie");
		lblCategorie.setBounds(60, 98, 70, 15);
		add(lblCategorie);
		
		JLabel lblPret = new JLabel("Pret");
		lblPret.setBounds(60, 169, 70, 15);
		add(lblPret);
		
		textDenumire = new JTextField();
		textDenumire.setBounds(214, 56, 114, 19);
		add(textDenumire);
		textDenumire.setColumns(10);
		
		textCategorie = new JTextField();
		textCategorie.setBounds(214, 96, 114, 19);
		add(textCategorie);
		textCategorie.setColumns(10);
		
		textTara = new JTextField();
		textTara.setBounds(214, 130, 114, 19);
		add(textTara);
		textTara.setColumns(10);
		
		textPret = new JTextField();
		textPret.setBounds(214, 167, 114, 19);
		add(textPret);
		textPret.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(214, 211, 117, 25);
		add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Produs produs = new Produs(textDenumire.getText(), textCategorie.getText(), textTara.getText(), Double.parseDouble(textPret.getText()));
				Task1 citireProduse = new Task1(file_produse);
				produse = citireProduse.readProduse(file_produse);
				boolean ok = false;
				boolean okCategorie = false;
				boolean okTara = false;
				boolean okDenumire = false;
				for (int i = 0; i < produse.size(); i++) {
					if (produse.get(i).getDenumire().equals(produs.getDenumire()))
						okDenumire = true;
					if (produse.get(i).getCategorie().equals(produs.getCategorie()))
						okCategorie = true;
					if (produse.get(i).getTaraOrigine().equals(produs.getTaraOrigine()))
						okTara = true;
					if (okDenumire == true && okCategorie == true && okTara == true)
						if (produse.get(i).getPret() == produs.getPret())
							ok = true;
				}
				if (ok == true)
					JOptionPane.showMessageDialog(null,"Produsul exista deja!");	
				else {
					if (okCategorie == false || okTara == false) {
						JOptionPane.showMessageDialog(null,"Eroare la adaugare!");
					}
					else {
						if(okDenumire == true && okCategorie == true && okTara == true) {
							try {
								String lineReplace = new String();
								String produsComp = new String();
								lineReplace = produs.getDenumire() + " " + produs.getCategorie(); 
								produsComp = produs.getDenumire() + " " + produs.getCategorie(); 
								File temp = new File(file_produse.getAbsolutePath() + ".tmp");
								BufferedReader br = new BufferedReader(new FileReader(file_produse));
								PrintWriter pw = new PrintWriter(new FileWriter(temp));
								
								String line = null;
								
								while ((line = br.readLine()) != null) {
									String[] p = line.split(" ");
									if (p[0].equals(produs.getDenumire()) && p[1].equals(produs.getCategorie())) {
										Vector<String> tari = Gestiune.getInstance().getTari();
										for (int i = 2; i < p.length; i++) {
											if (produs.getTaraOrigine().equals(tari.get(i-2))) {
												lineReplace += " " + produs.getPret();
											}
											else
												lineReplace += " " + p[i];
											produsComp += " " + p[i];
										}
										lineReplace += "\n";
									}
									if (!line.trim().equals(produsComp)) {
										pw.println(line);
										pw.flush();
									}
									else {
										pw.print(lineReplace);
										pw.flush();
									}
								}
								pw.close();
								br.close();
								
								file_produse.delete();
								temp.renameTo(file_produse);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						else {
							try(FileWriter fw = new FileWriter(file_produse, true);
									BufferedWriter bw = new BufferedWriter(fw);
									PrintWriter out = new PrintWriter(bw)) 
									{
									String tara = new String();
									Vector<String> tari = Gestiune.getInstance().getTari();
									for (int i = 0; i < tari.size(); i++) {
										if (produs.getTaraOrigine().equals(tari.get(i)))
											tara += produs.getPret() + " ";
										else
											tara += "0 ";
									}
									out.println(produs.getDenumire() + " " + produs.getCategorie() + " " + tara);
									}catch (IOException e) {
										e.printStackTrace();
									}
						}
						
					}
				}
			}
			
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(60, 211, 117, 25);
		add(btnBack);
		btnBack.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				layout.show(contentPane, "administrare");
				
			}
			
		});

	}

}
