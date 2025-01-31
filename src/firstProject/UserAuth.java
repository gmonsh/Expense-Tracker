package firstProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuth {
             static Connection conn = DBconnection.getConnection();
             private static int LoginUserId = -1;
             
             public static boolean login(String email,String pwd){
            	 try {
            	 String query = "select * from user where emailId=? and password=?";
            	 PreparedStatement pst = conn.prepareStatement(query);
            	 pst.setString(1, email);
            	 pst.setString(2, pwd);
            	 ResultSet res = pst.executeQuery();
            	 if(res.next()) {
            		 LoginUserId = res.getInt("id");
            		 return true;
            	 }
            	 }
            	 catch(SQLException e) {
            		 System.out.println(e.getMessage());
            	 }
            	 return false;
             }
           //register user:
       	  public boolean register(String name,String emailId,String pwd) {
       		  
       		  try {
       			String query = "insert into user(Name,EmailId,password) values(?,?,?)";
       		    PreparedStatement pst = conn.prepareStatement(query);
       		    pst.setString(1, name);
       		    pst.setString(2, emailId);
       		    pst.setString(3, pwd);
       		    int res = pst.executeUpdate();
       		    if(res>0) {
       		    return true;	
       		    }
       		    return false;
       		  }
       		  catch(SQLException e) {
       			  System.out.println(e.getMessage());
       		  }
       		 return false;
       	  }
             public static int getLoginUserID() {
            	 return LoginUserId;
             }
             public static void logout() {
            	 LoginUserId = -1;
            	 System.out.println("You have been logged out successfully!!!");
             }
}
