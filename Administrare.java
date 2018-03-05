package SistemDeFacturiFiscale;

import javax.swing.JPanel;
import javax.swing.JList;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JScrollBar;

public class Administrare extends JPanel {
	public ArrayList<Produs> produse = new ArrayList<>();

	public Administrare(CardLayout layout, JPanel contentPane, File file_produse) {
		setLayout(null);
		
		DefaultListModel<Produs> lista = new DefaultListModel<>();
		produse = Gestiune.getInstance().getProduse();
		
		for (int i = 0; i < produse.size(); i++) 
			lista.addElement(produse.get(i));
		
		JList<Produs> list = new JList(lista);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(40, 60, 297, 258);
		list.setSelectedIndex(0);
		list.setVisibleRowCount(2);
		add(list);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(365, 215, 117, 25);
		add(btnRefresh);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Task1 citireProduse = new Task1(file_produse);
				produse = citireProduse.readProduse(file_produse);
				Vector<Produs> newList = new Vector<>();
				for (int i = 0; i < produse.size(); i++) 
					newList.addElement(produse.get(i));
				list.setListData(newList);

			}
		});
		
		JButton Denumire = new JButton("Denumire");
		Denumire.setBounds(365, 100, 117, 25);
		add(Denumire);
		Denumire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Collections.sort(produse, new ComparatorDenumire());
				Vector<Produs> lista2 = new Vector<>();
				for (int i = 0; i < produse.size(); i++) 
					lista2.addElement(produse.get(i));
				list.setListData(lista2);
			}
		});
		
		JButton Tara = new JButton("Tara");
		Tara.setBounds(365, 159, 117, 25);
		add(Tara);
		Tara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				Collections.sort(produse, new ComparatorTara());
				Vector<Produs> lista2 = new Vector<>();
				for (int i = 0; i < produse.size(); i++) 
					lista2.addElement(produse.get(i));
				list.setListData(lista2);
			}
		});
		
		JButton btnInapoi = new JButton("Back");
		btnInapoi.setBounds(365, 293, 117, 25);
		add(btnInapoi);
		btnInapoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
					layout.show(contentPane, "choice");
			}
		});
		
		JButton btnAdauga = new JButton("Add");
		btnAdauga.setBounds(40, 340, 117, 25);
		add(btnAdauga);
		btnAdauga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
					layout.show(contentPane, "add");
			}
		});
		
		JButton btnSterge = new JButton("Delete");
		btnSterge.setBounds(40, 380, 117, 25);
		add(btnSterge);
		btnSterge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layout.show(contentPane, "delete");
			}
		});
		
		JButton btnEditare = new JButton("Edit");
		btnEditare.setBounds(219, 340, 117, 25);
		add(btnEditare);
		btnEditare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layout.show(contentPane, "edit");
			}
		});
		
		JButton btnCautare = new JButton("Search");
		btnCautare.setBounds(219, 380, 117, 25);
		add(btnCautare);
		
		JLabel lblNewLabel = new JLabel("Ordonare dupa:");
		lblNewLabel.setBounds(368, 61, 127, 15);
		add(lblNewLabel);
		btnCautare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layout.show(contentPane, "search");
			}
		});
	}
}
