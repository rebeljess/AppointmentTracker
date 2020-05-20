/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jesslambertappointments.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import static utilities.DBConnection.getConnection;


/**
 *
 * @author jess
 */
public class CityData {
    private static ObservableList<String> cities = FXCollections.observableArrayList();

    
    private static int cityID;
    private static String city;
    
    @FXML
    public static ObservableList<String> getAllCities() {
    
        cities.clear();
        
        
        try{
            // Create Statement Object
        Statement stmt = getConnection().createStatement();
        
        // Write SQL statement
        String sqlStatement = "SELECT * FROM city";
        
        // Execute Statement and Create ResultSet object
        ResultSet result = stmt.executeQuery(sqlStatement);
        
        // Get all records from ResultSet Object
        while(result.next()) {
                    cityID = result.getInt("cityId"); 
                    city = result.getString("city");
                    
            
                    
            cities.add(cityID + " - " +city);}
            
            } catch (SQLException e) {
                    System.out.println("SQLException: " + e.getMessage());
                    }
            
        return cities;
    }
   
    
    
    
}
