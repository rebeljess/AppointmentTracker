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
public class User {
        
    // Instance Variables
    private final IntegerProperty userID;
    private final StringProperty username;
    private final StringProperty password;
    public final IntegerProperty active;
    private final StringProperty createDate;
    private final StringProperty createdBy;
    private final StringProperty lastUpdate;
    private final StringProperty lastUpdateBy;
    
    /**
     *
     */
   
    
    // Constructor
    public User(int userID, String username, String password, int active, String createDate, String createdBy, String lastUpdate, String lastUpdateBy) {
        this.userID = new SimpleIntegerProperty(userID);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.active = new SimpleIntegerProperty(active);
        this.createDate = new SimpleStringProperty(createDate);
        this.createdBy = new SimpleStringProperty(createdBy);
        this.lastUpdate = new SimpleStringProperty(lastUpdate);
        this.lastUpdateBy = new SimpleStringProperty(lastUpdateBy);
    }

    public User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public IntegerProperty userIDProperty() {
        return userID;
    }
    
    public StringProperty usernameProperty() {
        return username;
    }
    
    public StringProperty passwordProperty() {
        return password;
    }
    
    public IntegerProperty activeProperty() {
        return active;
    }
    
    public StringProperty createDateProperty() {
        return createDate;
    }
    
    public StringProperty createdByProperty() {
        return createdBy;
    }
    
    public StringProperty lastUpdateProperty() {
        return lastUpdate;
    }
    
    public StringProperty lastUpdateByProperty() {
        return lastUpdateBy;
    }
    
    // Getters
    public int getUserID(){
        return userID.get();
    }
    
    public String getUsername(){
        return username.get();
    }

    public String getPassword(){
        return password.get();
    }

    public int getActive(){
        return active.get();
    }

    public String getCreateDate(){
        return createDate.get();
    }
    
    public String getCreatedBy(){
        return createdBy.get();
    }
    
    public String getLastUpdate(){
        return lastUpdate.get();
    }
    
    public String getLastUpdateBy(){
        return lastUpdateBy.get();
    }
    
    // Setters
    private void setUserID(int userID) {
        this.userID.set(userID);
    }

    public void setUsername(String username) {
        this.username.set(username);
    }
    
    public void setPassword(String password) {
        this.password.set(password);
    }
    
    private void setActive(int active) {
        this.active.set(active);
    }
    
    private void setCreateDate(String createDate) {
        this.createDate.set(createDate);
    }
    
    private void setCreatedBy(String createdBy) {
        this.createdBy.set(createdBy);
    }

    private void setLastUpdate(String lastUpdate) {
        this.lastUpdate.set(lastUpdate);
    }

    private void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy.set(lastUpdateBy);
    }

    public boolean validate() throws AssertionError {
        assert !this.username.equals("");
        assert !this.password.equals("");
        
        return true;
    }
    
}
