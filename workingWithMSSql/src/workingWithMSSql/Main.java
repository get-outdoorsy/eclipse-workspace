/**
 * 
 */
package workingWithMSSql;

//import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

	public Connection connectDB() {
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		    Connection conn = DriverManager.getConnection("jdbc:sqlserver://USER-PC\\NEWSQLSERVER;"+
		    		"databaseName=testDBMSsql;"+
		    		"user=sa;"+
		    		"password=010798");
		    //System.out.println("Connected to database.");
		    return conn;
		}catch(Exception e) {
			e.printStackTrace();
			//System.out.print("Connection failed.");
			return null;
		}
	}
	public static void main(String[] args) {
	}

}
