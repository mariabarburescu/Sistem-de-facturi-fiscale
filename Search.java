package SistemDeFacturiFiscale;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class Search extends JPanel {
	private JTextField textDenumire;
	private JTextField textCategorie;
	private JTextField textTara;
	private JTextField textPret;

	public ArrayList<Produs> produse = new ArrayList<>();
	
	public Search(CardLayout layout, JPanel contentPane) {
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
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(214, 211, 117, 25);
		add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Produs produs = new Produs(textDenumire.getText(), textCategorie.getText(), textTara.getText(), Double.parseDouble(textPret.getText()));
				produse = Gestiune.getInstance().getProduse();
				boolean ok = false;
				for (int i = 0; i < produse.size(); i++) {
					if (produse.get(i).getDenumire().equals(produs.getDenumire()))
						if (produse.get(i).getCategorie().equals(produs.getCategorie()))
							if (produse.get(i).getTaraOrigine().equals(produs.getTaraOrigine()))
								if (produse.get(i).getPret() == produs.getPret())
									ok = true;
				}
				if (ok == true)
					JOptionPane.showMessageDialog(null,"Produs gasit!");	
				else
					JOptionPane.showMessageDialog(null,"Produsul nu a fost gasit!");
			}
			
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(60, 211, 117, 25);
		add(btnBack);
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				layout.show(contentPane, "administrare");
				
			}
			
		});

	}
}
