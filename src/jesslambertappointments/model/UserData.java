/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jesslambertappointments.model;

import java.io.PrintStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import utilities.ActivityLog;
import utilities.DBConnection;
import static utilities.DBConnection.getConnection;


/**
 *
 * @author jess
 */
public class UserData {
    private static String currentUser;
    private static int lastRowIndex;
    private static int newID;
    private static int userIDCount;
    private static int userID;
    private static String userName;
    private static String password;
    private static int active;
    private static String createDate;
    private static String createdBy;
    private static String lastUpdate;
    private static String lastUpdateBy;
    
    public static String getCurrentUser() {
        return currentUser;
    }
    
    public static void addUser(int userID, String username, String password, int active, String createDate, String createdBy, String lastUpdate, String lastUpdateBy) {
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "INSERT INTO user (userId, userName, password, active, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES ('"+userID+"', '"+username+"', '"+password+"', '"+active+"', '"+createDate+"', '"+createdBy+"', '"+lastUpdate+"', '"+lastUpdateBy+"')";
            stmt.execute(query);
        } catch(SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
    
    public static void modUser(int userID, String username, String password, int active, String createDate, String createdBy, String lastUpdate, String lastUpdateBy) {
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "UPDATE user SET userId ='" + userID + "', userName='" + username + "', password='" + password + "', active='" + active + "', createDate='" + createDate + "', createdBy='" + createdBy + "', lastUpdate='" + lastUpdate + "', lastUpdateBy='" + lastUpdateBy + "' WHERE userId='" + userID + "';";
           
            stmt.executeUpdate(query);
        } catch(SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
    
    public static void deleteUser(int userId) {
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "DELETE FROM user WHERE userId =" + userId;
            stmt.executeUpdate(query);
        } catch(SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
    
    public static int getUserIDCount() {
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT COUNT(*) FROM user";
            ResultSet result = stmt.executeQuery(query);
            while(result.next())
                userIDCount=result.getInt(1);
            stmt.close();
        } catch(SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return userIDCount;
    }
    
        public static int getNewID() {
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT COUNT(*) FROM user";
            ResultSet result = stmt.executeQuery(query);
            while(result.next())
                userIDCount=result.getInt(1);
                lastRowIndex = userIDCount - 1;
                newID = getAllUsers().get(lastRowIndex).getUserID() + 1;
            stmt.close();
        } catch(SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return newID;
    }
    
    public boolean checkUsername(String username){
        
        PreparedStatement ps;
        ResultSet rs;
        boolean checkUser = false;
        String query = "SELECT * FROM user WHERE userName= ?";
//        currentUser = username;
        
        try {
            ps = DBConnection.getConnection().prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            
            if(rs.next()){
                
                checkUser = true;
                
            }
        } catch (SQLException ex) {
            ActivityLog.log(username, true);
        }
        return checkUser;
        
    }

    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    
    public static ObservableList<User> getAllUsers() {
        
        allUsers.clear();
        try {
 
        // Create Statement Object
        Statement stmt = getConnection().createStatement();
        
        // Write SQL statement
        String sqlStatement = "SELECT * FROM user";
        
        // Execute Statement and Create ResultSet object
        ResultSet result = stmt.executeQuery(sqlStatement);
        
        // Get all records from ResultSet Object
        while(result.next()) {
            User u = new User(
                    
                    result.getInt("userId"), 
                    result.getString("userName"),
                    result.getString("password"),
                    result.getInt("active"),
                    result.getString("createDate"),
                    result.getString("createdBy"),
                    result.getString("lastUpdate"),
                    result.getString("lastUpdateBy")
                    
            );
            
            allUsers.add(u);}
        
            } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

            return allUsers;
            
    } 
   private static ObservableList<String> userList = FXCollections.observableArrayList();
   
   public static ObservableList<String> getUserList() {
       
       userList.clear();
       
       try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String sqlStatement = "SELECT * FROM user";
            ResultSet result = stmt.executeQuery(sqlStatement);
            while(result.next()) {
                 
                userName = result.getString("user.userName");
                
                userList.add(userName);
            }
        } catch (SQLException e) {
            System.out.println("UD7SQLException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("UD7Exception: " + e.getMessage());
        }

            return userList;
   }
    
}
    
    
    

