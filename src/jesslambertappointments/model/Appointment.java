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
public class Appointment {
    private final IntegerProperty appointmentID;
    private final IntegerProperty customerID;
    private final IntegerProperty userID;
    private final StringProperty type;
    private final StringProperty start;
    private final StringProperty end;
    private final StringProperty createDate;
    private final StringProperty createdBy;
    private final StringProperty lastUpdate;
    private final StringProperty lastUpdateBy;
    
    // Constructor
    public Appointment(int appointmentID, int customerID, int userID, String type, String start, String end, String createDate, String createdBy, String lastUpdate, String lastUpdateBy) {
        this.appointmentID = new SimpleIntegerProperty(appointmentID);
        this.customerID = new SimpleIntegerProperty(customerID);
        this.userID = new SimpleIntegerProperty(userID);
        this.type = new SimpleStringProperty(type);
        this.start = new SimpleStringProperty(start);
        this.end = new SimpleStringProperty(end);
        this.createDate = new SimpleStringProperty(createDate);
        this.createdBy = new SimpleStringProperty(createdBy);
        this.lastUpdate = new SimpleStringProperty(lastUpdate);
        this.lastUpdateBy = new SimpleStringProperty(lastUpdateBy);
    }
    
    public Appointment() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
    
    public IntegerProperty appointmentIDProperty() {
        return appointmentID;
    }
    
    public IntegerProperty customerIDProperty() {
        return customerID;
    }
    
    public IntegerProperty userIDProperty() {
        return userID;
    }
    
    public StringProperty typeProperty() {
        return type;
    }
    
    public StringProperty startProperty() {
        return start;
    }
    
    public StringProperty endProperty() {
        return end;
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
    public int getAppointmentID(){
        return appointmentID.get();
    }
    
    public int getCustomerID(){
        return customerID.get();
    }
    
    public int getUserID(){
        return userID.get();
    }
    
    public String getType(){
        return type.get();
    }
    
    public String getStart(){
        return start.get();
    }
    
    public String getEnd(){
        return end.get();
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
    private void setAppointmentID(int appointmentID) {
        this.appointmentID.set(appointmentID);
    }
    
    private void setCustomerID(int customerID) {
        this.customerID.set(customerID);
    }
    
    private void setUserID(int userID) {
        this.userID.set(userID);
    }
    
    private void setType(String type) {
        this.type.set(type);
    }
    
    private void setStart(String start) {
        this.start.set(start);
    }
    
    private void setEnd(String end) {
        this.end.set(end);
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
    
}
