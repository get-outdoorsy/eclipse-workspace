package crudSQL;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.util.Properties;
import java.util.*;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Crud extends JPanel {

	private JFrame frm_signup;
	private JTextField txt_firstname;
	private JTextField txt_lastname;
	private JTextField txt_age;
	private JTable tbl_display;
	Sql_process process = new Sql_process();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Crud window = new Crud();
					window.frm_signup.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Crud() {
		initialize();	
		showData();
	}
	
	private void initialize() {
		frm_signup = new JFrame();
		frm_signup.setResizable(false);
		frm_signup.setTitle("CRUD");
		frm_signup.setBounds(100, 100, 606, 286);
		frm_signup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_signup.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 11, 72, 28);
		frm_signup.getContentPane().add(lblNewLabel);
		
		txt_firstname = new JTextField();
		txt_firstname.setBounds(85, 16, 114, 20);
		frm_signup.getContentPane().add(txt_firstname);
		txt_firstname.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLastName.setBounds(10, 50, 72, 28);
		frm_signup.getContentPane().add(lblLastName);
		
		txt_lastname = new JTextField();
		txt_lastname.setColumns(10);
		txt_lastname.setBounds(85, 55, 114, 20);
		frm_signup.getContentPane().add(txt_lastname);
		
		JLabel lblBirthDate = new JLabel("Birth date");
		lblBirthDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBirthDate.setBounds(10, 89, 72, 28);
		frm_signup.getContentPane().add(lblBirthDate);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker_dob = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker_dob.setSize(114, 28);
		datePicker_dob.setLocation(85, 89);
		datePicker_dob.setDoubleClickAction(true);
		datePicker_dob.setTextEditable(true);
		datePicker_dob.setShowYearButtons(true);
		frm_signup.getContentPane().add(datePicker_dob);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAge.setBounds(10, 128, 72, 28);
		frm_signup.getContentPane().add(lblAge);
		
		txt_age = new JTextField();
		txt_age.setColumns(10);
		txt_age.setBounds(85, 128, 114, 20);
		frm_signup.getContentPane().add(txt_age);
		
		JButton btn_reset = new JButton("Clear");
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				datePicker_dob.getJFormattedTextField().setText("");
				clearInput();
			}
		});
		btn_reset.setBounds(109, 213, 89, 23);
		frm_signup.getContentPane().add(btn_reset);
		
		JButton btn_insert = new JButton("Insert");
		btn_insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String firstName = txt_firstname.getText();
				String lastName = txt_lastname.getText();
				String birthDate = datePicker_dob.getJFormattedTextField().getText();
				String age = txt_age.getText();
				
				boolean isEmpty = emptyInput(firstName, lastName, birthDate, age);
				
				if(isEmpty) {
					JOptionPane.showMessageDialog(null, "Incomplete input.", "Warning", JOptionPane.WARNING_MESSAGE);
					txt_firstname.requestFocusInWindow();
				}else {
					int temp_age = Integer.parseInt(age);
					process.insertData(firstName, lastName, birthDate, temp_age);
					showData();
					clearInput();
					datePicker_dob.getJFormattedTextField().setText("");
				}
			}
		});
		btn_insert.setBounds(10, 179, 89, 23);
		frm_signup.getContentPane().add(btn_insert);
		
		tbl_display = new JTable();
		tbl_display.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int selectedRow = tbl_display.getSelectedRow();
				String fname = tbl_display.getValueAt(selectedRow, 1).toString();
				String lname = tbl_display.getValueAt(selectedRow, 2).toString();
				String dob = tbl_display.getValueAt(selectedRow, 3).toString();
				datePicker_dob.getJFormattedTextField().setText(dob);
				String age = tbl_display.getValueAt(selectedRow, 4).toString();
				
				putRowValuesToFields(fname, lname, age);
			}
		});
		tbl_display.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbl_display.setBounds(209, 19, 373, 184);
		frm_signup.getContentPane().add(tbl_display);
		
		JScrollPane scrollPane = new JScrollPane(tbl_display);
		scrollPane.setBounds(209, 19, 373, 217);
		frm_signup.getContentPane().add(scrollPane);
		
		JButton btn_delete = new JButton("Delete");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = getRowID();
				
				if(id == -1) {
					JOptionPane.showMessageDialog(null, "No data selected.");
				}else {
					deleteData(id);
					clearInput();
					datePicker_dob.getJFormattedTextField().setText("");
				}
				
			}
		});
		btn_delete.setBounds(10, 213, 89, 23);
		frm_signup.getContentPane().add(btn_delete);
		
		JButton btn_update = new JButton("Update");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String fname = txt_firstname.getText();
				String lname = txt_lastname.getText();
				String dob = datePicker_dob.getJFormattedTextField().getText();
				String temp_age = txt_age.getText();
				
				int id = getRowID();
				boolean isEmpty = emptyInput(fname, lname, dob, temp_age);
				
				if(isEmpty) {
					JOptionPane.showMessageDialog(null, "No data selected.");
				}else {
					int age = Integer.parseInt(temp_age);
					updateData(id, fname, lname, dob, age);
					clearInput();
					datePicker_dob.getJFormattedTextField().setText("");
					showData();
				}
			}
		});
		btn_update.setBounds(110, 179, 89, 23);
		frm_signup.getContentPane().add(btn_update);
	}
	
	//update data row 
	private void updateData(int id, String fname, String lname, String dob, int age) {
		Connection conn = process.connectDb();
		try {
			String updateQuery = "UPDATE [user] SET user_firstname = ?, user_lastname = ?, user_birthdate = ?, user_age = ? WHERE user_id = ?";
			PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
			updateStatement.setString(1, fname);
			updateStatement.setString(2, lname);
			updateStatement.setString(3, dob);
			updateStatement.setInt(4, age);
			updateStatement.setInt(5, id);
			updateStatement.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//delete data row 
	private void deleteData(int id) {
		Connection conn = process.connectDb();
		try {
			String deleteQuery = "DELETE FROM [user] WHERE user_id = '"+id+"'";
			PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
			deleteStatement.executeUpdate();
			showData();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// display data from database
	private void showData() {
		Connection conn = process.connectDb();
		DefaultTableModel model = new DefaultTableModel(new String[]{"User ID", "First Name", "Last Name", "Birth Date", "Age"}, 0);
		
		try {
			String displayQuery = "SELECT * FROM [user]";
			Statement showStatement = conn.createStatement();
			ResultSet rs = showStatement.executeQuery(displayQuery);
			
			while(rs.next()) {
				int id = rs.getInt("user_id");
				String fname = rs.getString("user_firstname");
				String lname = rs.getString("user_lastname");
				String dob = rs.getString("user_birthdate");
				int age = rs.getInt("user_age");
				
				model.addRow(new Object[] {id, fname, lname, dob, age});
				tbl_display.setModel(model);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// get selected row and put the row values to text fields
	private void putRowValuesToFields(String fname, String lname, String age) {
	
		txt_firstname.setText(fname);
		txt_lastname.setText(lname);
		txt_age.setText(age);
	}
	
	// check if inputs are empty
	private boolean emptyInput(String firstName, String lastName, String birthDate, String age) {
		boolean inputEmpty; 
		return inputEmpty = (firstName.equals("")||lastName.equals("")||birthDate.equals("")||age.equals("")) ? true : false;
	}
	
	//get selected row id
	private int getRowID() {
		int row;
		if(tbl_display.getSelectedRow() == -1) {
			return row = tbl_display.getSelectedRow();
			
		}else {
			row = tbl_display.getSelectedRow();
			String temp_id = tbl_display.getModel().getValueAt(row, 0).toString();
			int id = Integer.parseInt(temp_id);
			return id;
		}
	}
	
	// clear input text fields
	private void clearInput() {
		txt_firstname.setText("");
		txt_lastname.setText("");
		txt_age.setText("");
		txt_firstname.requestFocusInWindow();
	}
}
