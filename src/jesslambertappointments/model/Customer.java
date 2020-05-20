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
public class Customer {
    private final IntegerProperty customerID;
    private final StringProperty customerName;
    private final IntegerProperty addressID;
    private final StringProperty address;
    public final StringProperty address2;
    public IntegerProperty cityID;
    private final StringProperty country;
    private final StringProperty postalCode;
    private final StringProperty phone;
    
    
    // Constructor
    public Customer(int customerID, String customerName, int addressID, String address, String address2, int cityID, String country, String postalCode, String phone) {
        this.customerID = new SimpleIntegerProperty(customerID);
        this.customerName = new SimpleStringProperty(customerName);
        this.addressID = new SimpleIntegerProperty(addressID);
        this.address = new SimpleStringProperty(address);
        this.address2 = new SimpleStringProperty(address2);
        this.cityID = new SimpleIntegerProperty(cityID);
        this.country = new SimpleStringProperty(country);
        this.postalCode = new SimpleStringProperty(postalCode);
        this.phone = new SimpleStringProperty(phone);
    }
    
    public Customer(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public IntegerProperty customerIDProperty() {
        return customerID;
    }
    
    public StringProperty customerNameProperty() {
        return customerName;
    }
    
    public IntegerProperty addressIDProperty() {
        return addressID;
    }
    
    public StringProperty addressProperty() {
        return address;
    }
    
    public StringProperty address2Property() {
        return address2;
    }
    
    public IntegerProperty cityIDProperty() {
        return cityID;
    }
    
    public StringProperty countryProperty() {
        return country;
    }
    
    public StringProperty postalCodeProperty() {
        return postalCode;
    }
    
    public StringProperty phoneProperty() {
        return phone;
    }
    
    
    // Getters
    public int getCustomerID(){
        return customerID.get();
    }
    
    public String getCustomerName(){
        return customerName.get();
    }
    
    public int getAddressID(){
        return addressID.get();
    }

    public String getAddress(){
        return address.get();
    }

    public String getAddress2(){
        return address2.get();
    }

    public int getCityID(){
        return cityID.get();
    }
    
    public String getCountry(){
        return country.get();
    }
    
    public String getPostalCode(){
        return postalCode.get();
    }
    
    public String getPhone(){
        return phone.get();
    }
    
    // Setters
    private void setCustomerID(int customerID) {
        this.customerID.set(customerID);
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }
    
    private void setAddressID(int addressID) {
        this.addressID.set(addressID);
    }
    
    public void setAddress(String address) {
        this.address.set(address);
    }
    
    public void setAddress2(String address2) {
        this.address2.set(address2);
    }
    
    private void setCityID(int cityID) {
        this.cityID.set(cityID);
    }
    
    private void setCountry(String country) {
        this.country.set(country);
    }

    private void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }

    private void setPhone(String phone) {
        this.phone.set(phone);
    }

}
