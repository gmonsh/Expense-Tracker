package firstProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mainclass {

	public static void main(String[] args) {
		UserAuth user = new UserAuth();
		DBOperations db = new DBOperations();
		Scanner inp = new Scanner(System.in);
		boolean loggedIn = false;
		while(!loggedIn) {
			System.out.println("Welcome to EXPENSE TRACKER SYSTEM");
			System.out.println("1.Register");
			System.out.println("2.Login");
			System.out.println("3.Exit");
			int choice = inp.nextInt();
			inp.nextLine();
			switch(choice) {
			    case 1:
			    	// Register
                    System.out.println("Enter your Name:");
                    String name = inp.nextLine();
                    System.out.println("Enter your E-mail Id:");
                    String mailId = inp.nextLine();
                    System.out.println("Enter your Password:");
                    String pwd = inp.nextLine();
                    if (user.register(name, mailId, pwd)) {
                        System.out.println("User added successfully! Please login to continue.");
                    } else {
                        System.out.println("Could not add user. Try again.");
                    }
                    break;
			    case 2:
			    	//Login
					 System.out.println("Enter E-mail Id:");
					 String email = inp.next();
					 System.out.println("Enter password:");
					 String userpwd = inp.next();
					 if(UserAuth.login(email, userpwd))
				      {
						 loggedIn = true;
						 System.out.println("Login successful!Welcome back");
					  }
					 else
					  {
						 System.out.println("Invalid emailId or password!!!\nPlease try again");
					  }
					 break;
			    case 3:
			    	 System.out.println("Exiting!!!Thank you");
			    	 System.exit(0);
			    default:
			    	System.out.println("Enter a valid choice");
			}
		}
		while(loggedIn) {
			System.out.println("\nExpense Tracker Menu:");
			System.out.println("1.Add expense");
			System.out.println("2.View expense");
			System.out.println("3.Update expense");
			System.out.println("4.Delete expense");
			System.out.println("5.Generate Report");
			System.out.println("6.Logout");
			System.out.println("Enter your choice");
			int opt = inp.nextInt();
			inp.nextLine();
			switch(opt) {
		     case 1:
				  //add Expense
		    	 System.out.println("Enter \ndate:");
				 String date = inp.nextLine();
				 System.out.println("cateogry:");
				 String category = inp.nextLine();
				 System.out.println("amount of expense:");
				 Double amount = inp.nextDouble();
				 inp.nextLine();
				 System.out.println("description(optional):");
				 String desc = inp.nextLine();
				 db.addExpense(date, category, amount, desc);
				 System.out.println("press enter to continue");
				 inp.nextLine();
				 break;
			case 2:
				 //View expense
				 List<ExpenseList> eList = new ArrayList<>();
				 eList = db.viewExpense();
				 for(ExpenseList item:eList) {
					System.out.println("Id: "+item.getId());
					 System.out.println("Date: "+item.getDate());
					 System.out.println("Category: "+item.getCategory());
					 System.out.println("Amount: "+item.getAmount());
					 System.out.println("Description: "+item.getDescription());
					 System.out.println();
				 }
				 break;
			case 3:
				//update expense:
				 System.out.println("Enter Expense ID to update:");
                 int expenseId = inp.nextInt();
                 inp.nextLine();
                 System.out.println("Enter New Date (YYYY-MM-DD):");
                 String newDate = inp.nextLine();
                 System.out.println("Enter New Category:");
                 String newCategory = inp.nextLine();
                 System.out.println("Enter New Amount:");
                 double newAmount = inp.nextDouble();
                 inp.nextLine();
                 System.out.println("Enter New Description:");
                 String newDescription = inp.nextLine();
                 db.updateExpense(expenseId, newDate, newCategory, newAmount, newDescription);
                 break;
			case 4:
				//delete expense
				System.out.println("Enter expense id to delete:");
				int expId = inp.nextInt();
				db.deleteExpense(expId);
				System.out.println("press enter to continue");
				inp.nextLine();
				break;
			case 5:
				//generate report
				db.generateReport();
				break;
			case 6:
				//logout
				UserAuth.logout();
				loggedIn = false;
				System.out.println("Redirecting to Login/Register...");
				main(args);
				System.exit(0);
				break;
			default:
				System.out.println("Enter a valid choice");
			}
		}
	}

}
