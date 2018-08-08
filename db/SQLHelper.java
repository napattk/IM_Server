package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SQLHelper {
	
		String url = "jdbc:mysql://127.0.0.1:3306/chatDB";
		String username = "root";
		String password = "admin";

   public List<List<String>> getUserData() throws SQLException {
	   
	   ResultSet resultSet = null;
	   System.out.println("Connecting database...");
	   try {
			Connection connection = DriverManager.getConnection(url, username, password);
		    System.out.println("Database connected!");
		    
		    String query = "SELECT * FROM `chatDB`.`Users`;";
		    
		    resultSet = connection.prepareStatement(query).executeQuery();
		    
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	   
	   List<List<String>>Data = new ArrayList<List<String>>();
	   int i = 0;
        while (resultSet.next()) {
            String user = resultSet.getString("userID");
            String password = resultSet.getString("password");

            i++;
            
            Data.add(Arrays.asList(user,password));
            
            System.out.println("User: " + user);
            System.out.println("Password: " + password);
        }
		return Data;
    }
   
   public boolean checkPass(String userID, String givenPass) throws SQLException {
	   System.out.println("Connecting database...");
	   
	   ResultSet resultSet = null;
	   try {
			Connection connection = DriverManager.getConnection(url, username, password);
		    System.out.println("Database connected!");
		    
		    String query = "SELECT * FROM `chatDB`.`Users` WHERE userID = '" + userID + "';";
		    System.out.println("Running query: " + query);
		    
		    resultSet = connection.prepareStatement(query).executeQuery();
		    
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	   
	   if (!resultSet.next() ) {
		    System.out.println("User does not exist.");
		} 
	   else {
           String dbPass = resultSet.getString("password");
           if(givenPass.equals(dbPass)) {
        	   return true;
           }
           else {
        	   System.out.println("Password incorrect.");
           }
	   }
	   
	   
	   return false;
   }
   
   public boolean newUser(String userID, String givenPass) throws SQLException {
	   try {
		   Connection connection = DriverManager.getConnection(url, username, password);
		    System.out.println("Database connected!");
		    
		    String query = 
		    		"INSERT INTO `Users` (`userID`,`password`) VALUES "+
		    		"('"+ userID +"','" + givenPass + "');";
		    		
		    System.out.println("Running query: " + query);
		    
		    Statement statement = connection.createStatement();
		    statement.executeUpdate(query);
		    return true;
	   } catch (SQLException e) {
		  e.printStackTrace();
		   	return false;
		}

   }
   
   public boolean addFriend(String userID, String friendID) throws SQLException {
	   try {
		   Connection connection = DriverManager.getConnection(url, username, password);
		    System.out.println("Database connected!");
		    
		    String query = 
		    		"INSERT INTO `Friends` (`userID`,`friendID`) VALUES "+
		    		"('"+ userID +"','" + friendID + "');";
		    		
		    System.out.println("Running query: " + query);
		    
		    Statement statement = connection.createStatement();
		    statement.executeUpdate(query);
		    return true;
	   } catch (SQLException e) {
		  e.printStackTrace();
		   	return false;
		}
   }
   
   public List<List<String>> getFriend(String userID) throws SQLException {
	   List<List<String>>Data = new ArrayList<List<String>>();
	   try {
		   Connection connection = DriverManager.getConnection(url, username, password);
		    System.out.println("Database connected!");
		    
		    ResultSet resultSet = null;
		    
		    String query = "SELECT * FROM `chatDB`.`Friends` WHERE userID = '" + userID + "';";
		    System.out.println("Running query: " + query);
		    
		    resultSet = connection.prepareStatement(query).executeQuery();
		    
		    
			int i = 0;
		    while (resultSet.next()) {
	            String user = resultSet.getString("userID");
	            String friend = resultSet.getString("friendID");

	            i++;
	            
	            Data.add(Arrays.asList(user,friend));
	            
	            System.out.println("User: " + user);
	            System.out.println("Friend: " + friend);

	        }
		    
	   } catch (SQLException e) {
		  e.printStackTrace();
		}
	   
	return Data;
   }
   
}