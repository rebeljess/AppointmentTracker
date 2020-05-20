/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jesslambertappointments.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jess
 */
public class City {
    
    private final IntegerProperty cityID;
    private final StringProperty city;
    
    
    public City(int cityID, String city) {
        this.cityID = new SimpleIntegerProperty(cityID);
        this.city = new SimpleStringProperty(city);
       
    }
    
//    public City(int cityID){
//        this.cityID = new SimpleIntegerProperty(cityID);
//    }
//    
//    public City(){
//        
//    }
//    
//    
//    
//    public City(int cityID, String city) {
//        this.cityID = new SimpleIntegerProperty(cityID);
//        this.city = new SimpleStringProperty(city);
//    }
    
    public IntegerProperty cityIDProperty() {
        return cityID;
    }
    
    public StringProperty cityProperty() {
        return city;
    }
    
    
    
    // Getters
    public int getCityID() {
        return cityID.get();
    }
   
    public String getCity() {
        return city.get();
    }
   
   
   
    // Setters
    public void setCityID(Integer cityID) {
        this.cityID.set(cityID);
    }
 
    public void setCity(String city) {
        this.city.set(city);
    }
  
   
}
