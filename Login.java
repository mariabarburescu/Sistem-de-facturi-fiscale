package SistemDeFacturiFiscale;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Login extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;

	public Login(CardLayout layout, JPanel contentPane) {
		setLayout(null);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(96, 107, 89, 40);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(96, 170, 70, 40);
		add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(310, 118, 114, 19);
		add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(310, 181, 114, 19);
		add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(202, 298, 117, 25);
		add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String password = passwordField.getText();
				String username = textField.getText();
				
				if (password.equals("aris") && username.equals("mada")) {
					layout.show(contentPane, "choice");
				}
				else
					JOptionPane.showMessageDialog(null,"Try again!");
			}
			
		});

	}
}
