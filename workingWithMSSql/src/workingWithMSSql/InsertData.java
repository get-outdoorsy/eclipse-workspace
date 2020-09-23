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

public class InsertData{

	private JFrame frm_insert;
	private JTextField txt_firstName;
	private JTextField txt_lastName;
	private JLabel lblAge;
	private JTextField txt_age;
	private String student_firstName;
	private String student_lastName;
	private int student_age = 0;
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
	//SHOW DATA
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
	//INSERT DATA METHOD
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

	private void initialize() {
		frm_insert = new JFrame();
		frm_insert.setTitle("Insert");
		frm_insert.setBounds(100, 100, 487, 171);
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
				
			}
		});
		btnInsert.setBounds(88, 92, 86, 23);
		frm_insert.getContentPane().add(btnInsert);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(10, 11, 89, 14);
		frm_insert.getContentPane().add(lblNewLabel);
		
		txt_firstName = new JTextField();
		txt_firstName.setBounds(88, 8, 86, 20);
		frm_insert.getContentPane().add(txt_firstName);
		txt_firstName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name");
		lblNewLabel_1.setBounds(10, 36, 89, 14);
		frm_insert.getContentPane().add(lblNewLabel_1);
		
		txt_lastName = new JTextField();
		txt_lastName.setBounds(88, 33, 86, 20);
		frm_insert.getContentPane().add(txt_lastName);
		txt_lastName.setColumns(10);
		
		lblAge = new JLabel("Age");
		lblAge.setBounds(10, 61, 46, 14);
		frm_insert.getContentPane().add(lblAge);
		
		txt_age = new JTextField();
		txt_age.setColumns(10);
		txt_age.setBounds(88, 61, 86, 20);
		frm_insert.getContentPane().add(txt_age);
		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Student ID", "Student First Name", "Student Last Name", "Student Age"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(112);
		table.getColumnModel().getColumn(2).setPreferredWidth(108);
		table.getColumnModel().getColumn(3).setPreferredWidth(76);
		table.setBounds(184, 11, 277, 111);
		frm_insert.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(188, 11, 273, 111);
		frm_insert.getContentPane().add(scrollPane);
	}
}
