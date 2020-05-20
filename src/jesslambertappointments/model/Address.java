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
public class Address {
    private final IntegerProperty addressID;
    private final StringProperty address;
    private final StringProperty address2;
    private final IntegerProperty cityID;
    private final StringProperty postalCode;
    private final StringProperty phone;
    private final StringProperty aCreateDate;
    private final StringProperty aCreatedBy;
    private final StringProperty aLastUpdate;
    private final StringProperty aLastUpdateBy;
    


    // Constructor
    public Address(int addressID, String address, String address2, int cityID, String postalCode, String phone, String aCreateDate, String aCreatedBy, String aLastUpdate, String aLastUpdateBy){
        this.addressID = new SimpleIntegerProperty(addressID);
        this.address = new SimpleStringProperty(address);
        this.address2 = new SimpleStringProperty(address2);
        this.cityID = new SimpleIntegerProperty(cityID);
        this.postalCode = new SimpleStringProperty(postalCode);
        this.phone = new SimpleStringProperty(phone);
        this.aCreateDate = new SimpleStringProperty(aCreateDate);
        this.aCreatedBy = new SimpleStringProperty(aCreatedBy);
        this.aLastUpdate = new SimpleStringProperty(aLastUpdate);
        this.aLastUpdateBy = new SimpleStringProperty(aLastUpdateBy);
    }

    public Address(String address) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public IntegerProperty cityID() {
        return cityID;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty aCreateDateProperty() {
        return aCreateDate;
    }

    public StringProperty aCreatedByProperty() {
        return aCreatedBy;
    }

    public StringProperty aLastUpdateProperty() {
        return aLastUpdate;
    }

    public StringProperty aLastUpdateByProperty() {
        return aLastUpdateBy;
    }

    // Getters
    public int getAddressID() {
        return addressID.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getAddress2() {
        return address2.get();
    }

    public int getCityID() {
        return cityID.get();
    }

    public String getPostalCode() {
        return postalCode.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public String getACreateDate() {
        return aCreateDate.get();
    }

    public String getACreatedBy() {
        return aCreatedBy.get();
    }

    public String getALastUpdate() {
        return aLastUpdate.get();
    }

    public String getALastUpdateBy() {
        return aLastUpdateBy.get();
    }

    // Setters
    public void setAddressID(int addressID) {
        this.addressID.set(addressID);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setAddress2(String address2) {
        this.address2.set(address2);
    }

    public void setCityID(int cityID) {
        this.cityID.set(cityID);
    }

    public void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public void setACreateDate(String aCreateDate) {
        this.aCreateDate.set(aCreateDate);
    }

    public void setACreatedBy(String aCreatedBy) {
        this.aCreatedBy.set(aCreatedBy);
    }

    public void setALastUpdate(String aLastUpdate) {
        this.aLastUpdate.set(aLastUpdate);
    }

    public void setALastUpdateBy(String aLastUpdateBy) {
        this.aLastUpdateBy.set(aLastUpdateBy);
    }

}
