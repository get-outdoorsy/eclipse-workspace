package crudSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Sql_process{
	public Connection connectDb() {
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		    Connection conn = DriverManager.getConnection("jdbc:sqlserver://USER-PC\\NEWSQLSERVER;"
		    		+"databaseName=testDBMSsql;"
		    		+"user=sa;"
		    		+"password=010798");
		    return conn;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	// insert data to database table
	public void insertData(String firstName, String lastName, String birthDate, int age) {
		
		Connection conn = connectDb();
		String insertQuery = "INSERT INTO [user](user_firstname, user_lastname, user_birthdate, user_age) VALUES(?,?,?,?)";
		try {
			PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
			insertStatement.setString(1, firstName);
			insertStatement.setString(2, lastName);
			insertStatement.setString(3, birthDate);
			insertStatement.setInt(4, age);
			insertStatement.executeUpdate();
			
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
