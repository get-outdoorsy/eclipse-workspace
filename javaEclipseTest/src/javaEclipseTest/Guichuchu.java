package javaEclipseTest;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Guichuchu {

	private JFrame frm_login;
	private JTextField txtFld_username;
	private JPasswordField password_field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Guichuchu window = new Guichuchu();
					window.frm_login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Guichuchu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm_login = new JFrame();
		frm_login.setBackground(Color.YELLOW);
		frm_login.setFont(new Font("Bodoni MT Poster Compressed", Font.PLAIN, 14));
		frm_login.setTitle("Login");
		frm_login.setBounds(100, 100, 198, 279);
		frm_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_login.getContentPane().setLayout(null);
		
		JButton btn_login = new JButton("Login");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btn_login.setBounds(44, 195, 90, 35);
		frm_login.getContentPane().add(btn_login);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(10, 11, 90, 23);
		frm_login.getContentPane().add(lblNewLabel);
		
		txtFld_username = new JTextField();
		txtFld_username.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtFld_username.setBounds(10, 45, 158, 23);
		frm_login.getContentPane().add(txtFld_username);
		txtFld_username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(10, 93, 90, 23);
		frm_login.getContentPane().add(lblPassword);
		
		password_field = new JPasswordField();
		password_field.setBounds(10, 127, 158, 23);
		frm_login.getContentPane().add(password_field);
		
		
	}
}
