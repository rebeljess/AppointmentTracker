/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jesslambertappointments.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javax.swing.DefaultComboBoxModel;
import utilities.DBConnection;
import static utilities.DBConnection.getConnection;

/**
 *
 * @author jess
 */
public class AddressData {
    private static int addressIDCount;
    private static int lastRowIndex;
    private static int newID;
    private static String addresses;
    private int addressID;
    private String address;
    private String address2;
    private int cityID;
    private String postalCode;
    private String phone;
    private String aCreateDate;
    private String aCreatedBy;
    private String aLastUpdate;
    private String aLastUpdateBy;
    
    public static void addAddress(int addressID, String address, String address2, int cityID, String postalCode, String phone, String aCreateDate, String aCreatedBy, String aLastUpdate, String aLastUpdateBy) {
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "INSERT INTO address (addressId, address, address2, cityId, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES ('"+addressID+"', '"+address+"', '"+address2+"', '"+cityID+"', '"+aCreateDate+"', '"+aCreatedBy+"', '"+aLastUpdate+"', '"+aLastUpdateBy+"')";
            stmt.execute(query);
        } catch(SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
    
    public static void modAddress(int addressID, String address, String address2, int cityID, String postalCode, String phone, String aCreateDate, String aCreatedBy, String aLastUpdate, String aLastUpdateBy) {
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "UPDATE address SET addressId = '" + addressID + "', address='" + address + "', address2='" + address2 + "', cityId='" + cityID + "', createDate='" + aCreateDate + "', createdBy='" + aCreatedBy + "', lastUpdate='" + aLastUpdate +"', lastUpdateBy='" + aLastUpdateBy + "' WHERE addressId= '" + addressID + "';";
            stmt.execute(query);
        } catch(SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
    
    public static void deleteAddress(int addressId) {
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "DELETE FROM address WHERE addressId =" + addressId;
            stmt.executeUpdate(query);
        } catch(SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
    
    public static int getAddressIDCount() {
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT COUNT(*) FROM address";
            ResultSet result = stmt.executeQuery(query);
            while(result.next())
                addressIDCount=result.getInt(1);
        } catch(SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return addressIDCount;
    }
    public static int getNewID() {
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT COUNT(*) FROM address";
            ResultSet result = stmt.executeQuery(query);
            while(result.next())
                addressIDCount=result.getInt(1);
                lastRowIndex = addressIDCount - 1;
                newID = getAllAddresses().get(lastRowIndex).getAddressID() + 1;
            stmt.close();
        } catch(SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return newID;
    }
    
    private static ObservableList<Address> allAddresses = FXCollections.observableArrayList();
    
    public static ObservableList<Address> getAllAddresses(){
        allAddresses.clear();
        try{
            
            // Create Statement Object
            Statement stmt = getConnection().createStatement();
            
            //Write SQL statement
            String sqlStatement = "SELECT * FROM address";
            
            // Execute Statememt and Create ResultSet Object
            ResultSet result = stmt.executeQuery(sqlStatement);
            
            // Get all records from ResultSet Object
            while(result.next()) {
                Address a = new Address(
                
                        result.getInt("addressId"),
                        result.getString("address"),
                        result.getString("address2"),
                        result.getInt("cityId"),
                        result.getString("postalCode"),
                        result.getString("phone"),
                        result.getString("createDate"),
                        result.getString("createdBy"),
                        result.getString("lastUpdate"),
                        result.getString("lastUpdateBy")
                                
                );
                
                allAddresses.add(a);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        
        return allAddresses;
        
    }
    

    
public static String getCBAddresses(){
    try{
        Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT * FROM address";
            ResultSet result = stmt.executeQuery(query);
            while(result.next())
                addresses=result.getString(2);
                
            stmt.close();
        } catch(SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return addresses;
    }
       
    
}
