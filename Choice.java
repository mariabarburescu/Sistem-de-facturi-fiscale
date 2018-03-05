package SistemDeFacturiFiscale;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Choice extends JPanel {

	public Choice(CardLayout layout, JPanel contentPane) {
		setLayout(null);
		
		JButton btnAdaugaFisiere = new JButton("Adauga fisiere");
		btnAdaugaFisiere.setBounds(188, 92, 150, 25);
		add(btnAdaugaFisiere);
		btnAdaugaFisiere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
					layout.show(contentPane, "fisiere");
			}
		});
		
		JButton btnAdministrare = new JButton("Administrare");
		btnAdministrare.setBounds(188, 189, 150, 25);
		add(btnAdministrare);
		btnAdministrare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
					layout.show(contentPane, "administrare");
			}
		});
		
		JButton btnStatistici = new JButton("Statistici");
		btnStatistici.setBounds(188, 293, 150, 25);
		add(btnStatistici);
		btnStatistici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
					layout.show(contentPane, "statistici");
			}
		});
	}

}
