package javaEclipseTest;
import java.io.*;
import java.sql.*;
import java.io.*;
import java.math.*;
import java.security.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
	
import javax.swing.JOptionPane;

public class IndexClass {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		try {
		    DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		    Connection conn = DriverManager.getConnection("jdbc:sqlserver://USER-PC\\NEWSQLSERVER;user=sa;password=010798");
		            System.out.println("Connected");
		        //return conn;
		    } catch (Exception e) {
		        System.err.println(e.getMessage());
		        e.printStackTrace();
		        System.out.println("Database connection error.");
		        //return null;
		    }
	
	}	
}
