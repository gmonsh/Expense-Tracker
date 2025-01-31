package firstProject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
      public static Connection getConnection() {
    	  Connection connection = null;
    	  try {
    	  String url = "jdbc:mysql://localhost:3306/project";
    	  String user = "root";
    	  String password = "Md@171090";
    	  connection =  DriverManager.getConnection(url, user, password);
    	  }
    	  catch(SQLException e) {
    		  System.out.println("Error while connecting to database" + e.getMessage());
    	  }
    	  return connection;
      }
}
