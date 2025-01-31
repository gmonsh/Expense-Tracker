Expense Tracker System
Overview
The Expense Tracker System is a console-based Java application designed to help users efficiently track their expenses. It allows users to register, log in, add expenses, view expenses, update or delete entries, and generate reports. The system is built using Core Java, JDBC, and MySQL.

Features
•	User Authentication: Register and log in to manage personal expenses.
•	CRUD Operations: 
o	Add new expenses
o	View all recorded expenses
o	Update existing expense records
o	Delete unnecessary expenses
•	Data Persistence: Uses MySQL database to store expense records securely.
•	User-Friendly Console Interface
Technologies Used
•	Programming Language: Java (Core Java)
•	Database: MySQL
•	Database Connectivity: JDBC
•	IDE: Eclipse
Prerequisites
Before running the project, ensure you have the following installed:
•	Java Development Kit (JDK)
•	MySQL Database
•	Eclipse IDE
•	JDBC Driver for MySQL
Database Setup
1.	Create a MySQL database named expense_tracker.
2.	Execute the following SQL queries to create the required table:
CREATE DATABASE expense_tracker;
USE expense_tracker;

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE expense (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    date DATE NOT NULL,
    category VARCHAR(255) NOT NULL,
    amount decimal(10,2) NOT NULL,
    description TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
How to Run the Project
1.	Clone the Repository
2.	git clone https://github.com/gmonsh/expense-tracker.git
3.	cd expense-tracker
4.	Import the Project into Eclipse
o	Open Eclipse
o	Click on File > Open Projects from File System
o	Select the cloned folder and click Finish
5.	Update Database Configuration
o	Open DBconnection.java
o	Modify the database URL, username, and password as per your MySQL setup:
6.	private static final String URL = "jdbc:mysql://localhost:3306/expense_tracker";
7.	private static final String USER = "root";  // Change to your MySQL username
8.	private static final String PASSWORD = "password";  // Change to your MySQL password
9.	Run the Application
o	Open Mainclass.java
o	Run the file
Project Structure
Expense-Tracker/
│── src/
│   ├── firstProject/
│   │   ├── Mainclass.java
│   │   ├── UserAuth.java
│   │   ├── DBOperations.java
│   │   ├── DBconnection.java
│   │   ├── ExpenseList.java
│── README.md
│── .gitignore

Contact
•	Email: monishadinesh.cse@gmail.com
•	GitHub: gmonsh
