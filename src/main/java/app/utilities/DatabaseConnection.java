package app.utilities;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DatabaseConnection {

	public static Connection getConnection() {
		Connection connection = null;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// getting database connection to MySQL server
			connection =DriverManager.getConnection(ApplicationUtility.getPropertyValue("local_connectionUrl"), 
													 ApplicationUtility.getPropertyValue("local_username"), 
					                                 ApplicationUtility.getPropertyValue("local_password"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return connection;
	}
	
	public static void closeConnection(Connection con) throws SQLException
	{
		if(!(con==null) && !con.isClosed())
		{
			con.close();
		}
	}

}