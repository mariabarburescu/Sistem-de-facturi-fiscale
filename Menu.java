package SistemDeFacturiFiscale;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame {

	CardLayout layout = new CardLayout();
	private JPanel contentPane = new JPanel();
	Login login = new Login(layout, contentPane);
	Choice choice = new Choice(layout, contentPane);
	IncarcareFisiere fisiere = new IncarcareFisiere(layout, contentPane);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu() {
		contentPane.setLayout(layout);
		contentPane.add(login, "login");
		contentPane.add(choice, "choice");
		contentPane.add(fisiere, "fisiere");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 400, 520, 445);
		setContentPane(contentPane);
	}

}