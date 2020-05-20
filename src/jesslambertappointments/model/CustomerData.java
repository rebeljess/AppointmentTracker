/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jesslambertappointments.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jesslambertappointments.view_controller.CustomerAddScreenController;
import static jesslambertappointments.view_controller.CustomerAddScreenController.*;
import jesslambertappointments.view_controller.CustomerModifyScreenController;
import static jesslambertappointments.view_controller.CustomerModifyScreenController.*;
import jesslambertappointments.view_controller.LoginScreenController;
import utilities.DBConnection;
import static utilities.DBConnection.getConnection;

/**
 *
 * @author jess
 */
public class CustomerData {
    private static int customerIDCount;
    private static int count;
    private static int lastRowIndex;
    private static int newID;
    private static int customerID;
    private static String customerName;
    private static String address;
    public static String address2;
    public static int cityID;
    public static int addressID;
    private static String country;
    private static String postalCode;
    private static String phone;
    

    static int addressNewID;

    
    public static void addCustomer(int custID, String customerName, int addressNewID, int active, String createDate, String createdBy, String lastUpdate, String lastUpdateBy, String address, String address2, int cityID, String postalCode, String phone) {
        try{
            Statement stmt1 = DBConnection.getConnection().createStatement();
            String query1 = "INSERT INTO address (addressId, address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES ('"+addressNewID+"', '"+address+"', '"+address2+"', '"+cityID+"', '"+postalCode+"', '"+phone+"', '"+createDate+"', '"+createdBy+"', '"+lastUpdate+"', '"+lastUpdateBy+"')";
            stmt1.execute(query1);
            
            Statement stmt2 = DBConnection.getConnection().createStatement();
            String query2 = "INSERT INTO customer (customerId, customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES ('"+custID+"', '"+customerName+"', '"+addressNewID+"', '"+active+"', '"+createDate+"', '"+createdBy+"', '"+lastUpdate+"', '"+lastUpdateBy+"')";
            stmt2.execute(query2);
            
        } catch(SQLException e) {
            System.out.println("1SQLException: " + e.getMessage());
        } catch(Exception e) {
            System.out.println("1Exception: " + e.getMessage());
        }
    }
    
    public static void modCustomer(String address, String address2, int cityID, String postalCode, String phone, String lastUpdate, String lastUpdateBy, int customerID, String customerName, int addressID) {
        try{
            Statement stmt1 = DBConnection.getConnection().createStatement();
            String query1 = "UPDATE address SET address = '"+ address +"', address2 = '"+ address2 +"', cityId = '"+ cityID +"', postalCode = '"+ postalCode +"', phone = '"+ phone +"', lastUpdate = '"+ lastUpdate +"', lastUpdateBy = '"+ lastUpdateBy +"' WHERE address.addressId ='" + addressID + "';";
            stmt1.execute(query1);
            
            Statement stmt2 = DBConnection.getConnection().createStatement();
            String query2 = "UPDATE customer SET customerName = '"+ customerName +"', lastUpdate = '"+ lastUpdate +"', lastUpdateBy = '"+ lastUpdateBy +"' WHERE customerId ='" + customerID + "';";
            stmt2.execute(query2);

        } catch(SQLException e) {
            System.out.println("2SQLException: " + e.getMessage());
        }
    }
    
    
    public static void deleteCustomer(int customerId) {
        try{

            Statement stmt1 = DBConnection.getConnection().createStatement();
            String query1 = "DELETE FROM appointment WHERE customerId =" + customerId;
            stmt1.executeUpdate(query1);
            
            Statement stmt2 = DBConnection.getConnection().createStatement();
            String query2 = "DELETE FROM customer WHERE customerId =" + customerId;
            stmt2.executeUpdate(query2);
            
        } catch(SQLException e) {
            System.out.println("3SQLException: " + e.getMessage());
        }
    }
    
    public static int getCustomerIDCount() {
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT COUNT(*) FROM customer";
            ResultSet result = stmt.executeQuery(query);
            while(result.next())
                //count = stmt.getUpdateCount();
                customerIDCount=result.getInt(1);
            stmt.close();
        } catch(SQLException e) {
            System.out.println("4SQLException: " + e.getMessage());
        }
        return customerIDCount;
    }
    public static int getNewID() {
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT COUNT(*) FROM customer";
            ResultSet result = stmt.executeQuery(query);
            while(result.next())
                customerIDCount=result.getInt(1);
                lastRowIndex = customerIDCount - 1;
                newID = getAllCustomers().get(lastRowIndex).getCustomerID() + 1;
            stmt.close();
        } catch(SQLException e) {
            System.out.println("5SQLException: " + e.getMessage());
        }
        return newID;
    }
    
    
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    
    public static ObservableList<Customer> getAllCustomers() {
        
        allCustomers.clear();
        try {
 
        // Create Statement Object
        PreparedStatement stmt = DBConnection.getConnection().prepareStatement(
            "SELECT customer.customerId, customer.customerName, customer.addressId, address.address, address.address2, address.postalCode, city.cityId, city.city, country.country, address.phone " +
            "FROM customer, address, city, country " + 
            "WHERE customer.addressId = address.addressId AND address.cityId = city.cityId AND city.countryId = country.countryId " +
            "ORDER BY customer.customerId");

        // Execute Statement and Create ResultSet object
        ResultSet result = stmt.executeQuery();
        
        // Get all records from ResultSet Object
        while(result.next()) {
            
                    customerID = result.getInt("customer.customerId"); 
                    customerName = result.getString("customer.customerName");
                    addressID = result.getInt("customer.addressId");
                    address = result.getString("address.address");
                    address2 = result.getString("address.address2");
                    cityID = result.getInt("city.cityId");
                    country = result.getString("country.country");
                    postalCode = result.getString("address.postalCode");
                    phone = result.getString("address.phone");
                    
                    allCustomers.add(new Customer(customerID, customerName, addressID, address, address2, cityID, country, postalCode, phone));
                    
            
            
            }
        
            } catch (SQLException ex) {
            System.out.println("6SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

            return allCustomers;
            
    } 
    
    private static ObservableList<String> customerList = FXCollections.observableArrayList();

    public static ObservableList<String> getCustomerList() {
        
        customerList.clear();
        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String sqlStatement = "SELECT * FROM customer";
            ResultSet result = stmt.executeQuery(sqlStatement);
            while(result.next()) {
                 
                customerName = result.getString("customer.customerName");
                
                customerList.add(customerName);
            }
        } catch (SQLException e) {
            System.out.println("CD7SQLException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("CD7Exception: " + e.getMessage());
        }

            return customerList;
            
    } 
    public static String custName;
    public static String getCustomerName(int id) {
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            String query = "SELECT * FROM customer WHERE customerId='" + id + "'";
            ResultSet results = statement.executeQuery(query);
            if(results.next()) {
                
                custName = results.getString("customerName");
                statement.close();
                return custName;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return null;
    }
    
   
    
}
