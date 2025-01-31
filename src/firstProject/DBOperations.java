package firstProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBOperations{
	static Connection conn = DBconnection.getConnection();
	  //add Expense:
	   public boolean addExpense(String date,String category,Double amount,String desc) {
		   int userId = UserAuth.getLoginUserID();
		   if(userId == -1) {
			   System.out.println("Please login");
			   return false;
		   }
		   try {
			   String query = "insert into expense(User_Id,Date,Category,Amount,Description) values(?,?,?,?,?)";
			   PreparedStatement pst = conn.prepareStatement(query);
			   pst.setInt(1, userId);
			   pst.setString(2, date);
			   pst.setString(3, category);
			   pst.setDouble(4, amount);
			   pst.setString(5, desc);
			   int val = pst.executeUpdate();
			   if(val>0) {
				   System.out.println("Expense added successfully");
				   return true;
			   }
			   System.out.println("Could not add your expense.Please try again");
		   }
		   catch(SQLException e) {
			   System.out.println(e.getMessage());
		   }
		   return false;
	   }
	  //view expense:
       public List<ExpenseList> viewExpense() {
    	   List<ExpenseList> ExpList = new ArrayList<>();
    	   int userId = UserAuth.getLoginUserID();
    	   try {
    	   if(userId == -1) {
    		   System.out.println("Please Login");
    	   }
    	   String query = "select * from expense where user_id = ?";
    	   PreparedStatement pst = conn.prepareStatement(query);
    	   pst.setInt(1, userId);
    	   ResultSet rs = pst.executeQuery();
    	   while(rs.next()) {
    		   int id = rs.getInt(1);
    		   String date = rs.getString(3);
    		   String category = rs.getString(4);
    		   double amount = rs.getDouble(5);
    		   String desc = rs.getString(6);
    		   ExpenseList exp = new ExpenseList(id,date,category,amount,desc);
    		   ExpList.add(exp);
    	   }
    	  
       }
    	   catch(SQLException e) {
    		   System.out.println(e.getMessage());
    	   }
    	   return ExpList;
       }
       //Delete expense:
       public boolean deleteExpense(int id) {
    	   int userId = UserAuth.getLoginUserID();
    	   if(userId == -1) {
    		   System.out.println("Please Login");
    	   }
    	   try {
    		   String query = " delete from expense where id = ? and user_id = ?";
    		   PreparedStatement pst = conn.prepareStatement(query);
    		   pst.setInt(1,id);
    		   pst.setInt(2,userId);
    		   int val = pst.executeUpdate();
    		   if(val>0) {
    			   System.out.println("Expense deleted successfully");
    			   return true;
    		   }
    		   System.out.println("Could not delete expense.Enter valid epense id");
    	   }
    	   catch(SQLException e) {
    		   System.out.println(e.getMessage());
    	   }
    	   return false;
       }
       //Update expense:
       public boolean updateExpense(int exp_id,String date,String category,double amount,String desc) {
    	   int userId = UserAuth.getLoginUserID();
    	   if(userId == -1) {
    		   System.out.println("Please Login");
    	   }
    	   try {
    		   String query = "update expense set date=?,category=?,amount=?,description =? where id =? and user_id =?";
        	   PreparedStatement pst = conn.prepareStatement(query);
        	   pst.setString(1,date);
        	   pst.setString(2,category);
        	   pst.setDouble(3,amount);
        	   pst.setString(4,desc);
        	   pst.setInt(5,exp_id);
        	   pst.setInt(6,userId);
        	   int val = pst.executeUpdate();
        	   if(val>0) {
        		   System.out.println("Expense updated successfully");
        		   return true;
        	   }
    	   }
    	   catch(SQLException e) {
    		   System.out.println(e.getMessage());
    	   }
    	   return false;
       }
       //Generate record:
       public void generateReport() {
    	   int userId = UserAuth.getLoginUserID();
    	   if(userId == -1) {
    		   System.out.println("Please Login");
    	   }
    	   try {
    	   //total expenses
    	   String query = "select sum(amount) from expense where user_id = ?";
    	   PreparedStatement pst =conn.prepareStatement(query);
    	   pst.setInt(1, userId);
    	   ResultSet rs = pst.executeQuery();
    	   if(rs.next()) {
    		   System.out.println("\nExpense Report:");
    		   System.out.println("\nTotal Expenses: "+rs.getDouble(1));
    	   }
    	   // Expense by category
    	   String CategoryQuery = "select category,Sum(amount) from expense where user_id=? group by category";
    	   PreparedStatement catpst =conn.prepareStatement(CategoryQuery);
    	   catpst.setInt(1, userId);
    	   ResultSet catrs = catpst.executeQuery();
    	   System.out.println("\nExpense Breakdown by Category:");
    	   while(catrs.next()) {
    		   System.out.println(catrs.getString(1) + ": ₹" + catrs.getDouble(2));
    	   }
    	   // Highest and lowest expenses
    	   String MinMaxQuery = "select max(amount),min(amount) from expense where user_id=?";
    	   PreparedStatement MinMaxpst =conn.prepareStatement(MinMaxQuery);
    	   MinMaxpst.setInt(1, userId);
    	   ResultSet MinMaxrs = MinMaxpst.executeQuery();
    	   while(MinMaxrs.next()) {
    		   System.out.println("\nLowest Expense: ₹" + MinMaxrs.getDouble(1));
               System.out.println("Highest Expense: ₹" + MinMaxrs.getDouble(2));
    	   }
    	   //Recent 5 records:
    	   String recentQuery = "select date,category,amount from expense where user_id=? order by date desc limit 5";
    	   PreparedStatement recentpst = conn.prepareStatement(recentQuery);
    	   recentpst.setInt(1, userId);
    	   ResultSet recentrs = recentpst.executeQuery();
    	   System.out.println();
    	   while(recentrs.next()) {
    		   System.out.println(recentrs.getString(1) + " | " + recentrs.getString(2) + " | ₹" + recentrs.getDouble(3));
    	   }
    	   }
    	   catch(SQLException e) {
    		   System.out.println(e.getMessage());
    	   }
       }
}
