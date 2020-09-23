package workingWithMSSql;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InsertData{

	private JFrame frm_insert;
	private JTextField txt_firstName;
	private JTextField txt_lastName;
	private JLabel lblAge;
	private JTextField txt_age;
	
	private String student_firstName;
	private String student_lastName;
	private int student_age = 0;
	public int selectedRow;
	
	private JTable table;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertData window = new InsertData();
					window.frm_insert.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public InsertData() {
		initialize();
		showData();
	}
	//SHOW ALL DATA QUERY
	public void showData()
	{
		Main main = new Main();
		Connection conn = main.connectDB();
		DefaultTableModel model = new DefaultTableModel(new String[]{"Student ID", "First Name", "Last Name", "Age"}, 0);
		
		String fetchQuery = "SELECT * FROM student";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(fetchQuery);
			while(rs.next()) {
				int a = rs.getInt("student_id");
				String b = rs.getString("student_firstName");
				String c = rs.getString("student_lastName");
				int d = rs.getInt("student_age");
				model.addRow(new Object[] {a, b, c, d});
				table.setModel(model);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//INSERT DATA QUERY
	public void insertData(Connection conn, String student_firstName, String student_lastName, int student_age) {
		String insertQuery = "INSERT INTO student(student_firstName, student_lastName, student_age) VALUES(?,?,?)";
		try {
			//System.out.print(student_firstName+student_lastName+student_age);
			PreparedStatement sqlStatement = conn.prepareStatement(insertQuery);
			sqlStatement.setString(1, student_firstName);
			sqlStatement.setString(2, student_lastName);
			sqlStatement.setInt(3, student_age);
			sqlStatement.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//DELETE DATA QUERY
	public void deleteData(Connection conn) {
		String temp_id = table.getValueAt(selectedRow, 0).toString();
		int student_id = Integer.parseInt(temp_id);
		String deleteQuery = "DELETE FROM student WHERE student_id = '"+student_id+"'";
		try {
			PreparedStatement sqlStatement = conn.prepareStatement(deleteQuery);
			sqlStatement.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//UPDATE DATA QUERY
	private void updateData(Connection conn, String fname, String lname, int age) {
		String temp_id = table.getValueAt(selectedRow, 0).toString();
		int student_id = Integer.parseInt(temp_id);
		String updateQuery = "UPDATE student SET student_firstName = ?, student_lastName = ?, student_age = ? WHERE student_id = ?";
		try {
			PreparedStatement sqlStatement = conn.prepareStatement(updateQuery);
			sqlStatement.setInt(4, student_id);
			sqlStatement.setString(1, fname);
			sqlStatement.setString(2, lname);
			sqlStatement.setInt(3, age);
			sqlStatement.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//Putting the selected values to textfield for update function
	public void putRowValuesToFields(String fname, String lname, String age) {
		txt_firstName.setText(fname);
		txt_lastName.setText(lname);
		txt_age.setText(age);
	}
	//Clear texts 
	private void clearText() {
		txt_firstName.setText("");
		txt_lastName.setText("");
		txt_age.setText("");
	}

	private void initialize() {
		frm_insert = new JFrame();
		frm_insert.setAlwaysOnTop(true);
		frm_insert.setAutoRequestFocus(false);
		frm_insert.setResizable(false);
		frm_insert.setTitle("MS SQL");
		frm_insert.setBounds(100, 100, 529, 171);
		frm_insert.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_insert.getContentPane().setLayout(null);
		
		JButton btnInsert = new JButton("Add");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				student_firstName = txt_firstName.getText();
				student_lastName = txt_lastName.getText();
				student_age = Integer.parseInt(txt_age.getText());
				
				Main main = new Main();
				Connection conn = main.connectDB();
				InsertData insert = new InsertData();
				insert.insertData(conn, student_firstName, student_lastName, student_age);
				showData();
				clearText();
			}
		});
		btnInsert.setBounds(10, 87, 56, 34);
		frm_insert.getContentPane().add(btnInsert);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(10, 11, 89, 14);
		frm_insert.getContentPane().add(lblNewLabel);
		
		txt_firstName = new JTextField();
		txt_firstName.setBounds(88, 8, 132, 20);
		frm_insert.getContentPane().add(txt_firstName);
		txt_firstName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name");
		lblNewLabel_1.setBounds(10, 36, 89, 14);
		frm_insert.getContentPane().add(lblNewLabel_1);
		
		txt_lastName = new JTextField();
		txt_lastName.setBounds(88, 33, 132, 20);
		frm_insert.getContentPane().add(txt_lastName);
		txt_lastName.setColumns(10);
		
		lblAge = new JLabel("Age");
		lblAge.setBounds(10, 61, 46, 14);
		frm_insert.getContentPane().add(lblAge);
		
		txt_age = new JTextField();
		txt_age.setColumns(10);
		txt_age.setBounds(88, 61, 132, 20);
		frm_insert.getContentPane().add(txt_age);
		
		//Gets the student id of selected row
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				
				selectedRow = table.getSelectedRow();
				String id = table.getValueAt(selectedRow, 0).toString();
				String fname = table.getValueAt(selectedRow, 1).toString();
				String lname = table.getValueAt(selectedRow, 2).toString();
				String age = table.getValueAt(selectedRow, 3).toString();
				
				putRowValuesToFields(fname, lname, age);
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		table.setBounds(184, 11, 277, 111);
		frm_insert.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		scrollPane.setBounds(230, 10, 273, 111);
		frm_insert.getContentPane().add(scrollPane);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main main = new Main();
				Connection conn = main.connectDB();
				String fname = txt_firstName.getText();
				String lname = txt_lastName.getText();
				String temp_age = txt_age.getText();
				int age = Integer.parseInt(temp_age);
				updateData(conn, fname, lname, age);
				showData();
				clearText();
			}
		});
		btnEdit.setBounds(76, 87, 56, 34);
		frm_insert.getContentPane().add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main main = new Main();
				Connection conn = main.connectDB();
				deleteData(conn);
				showData();
				clearText();
			}
		});
		btnDelete.setBounds(142, 87, 78, 34);
		frm_insert.getContentPane().add(btnDelete);
	}
}
