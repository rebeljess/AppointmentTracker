/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jesslambertappointments.view_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jesslambertappointments.model.AddressData;
import static jesslambertappointments.model.CityData.getAllCities;

import jesslambertappointments.model.CustomerData;
/**
 * FXML Controller class
 *
 * @author jess
 */
public class CustomerAddScreenController implements Initializable {
    
    
    @FXML
    private TextField customerNameField;
    
    @FXML
    private TextField addressField;
    
    @FXML
    private TextField address2Field;
    
    @FXML
    ComboBox<String> cityCB = new ComboBox<String>();
    
    @FXML
    public TextField postalCodeField;
    
    @FXML
    public TextField phoneField;
    
    private static ObservableList<Integer> addresses = FXCollections.observableArrayList();

 
    Timestamp timestamp = new Timestamp(new Date().getTime());
    public String getTimestamp(){
        return timestamp + "";
    }
    
    ObservableList<String> cityList = FXCollections.observableArrayList(getAllCities());
    
    @FXML
    void addCustomerClickSave(ActionEvent event) throws IOException {
        if (customerNameField.getText().equals("") || addressField.getText().equals("") || cityCB.getValue() == null || postalCodeField.getText().equals("") || phoneField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection Error");
            alert.setHeaderText("Fields Not Completely Filled In!");
            alert.setContentText("Please ensure that you have filled in all fields.");
            alert.setGraphic(null);
            alert.showAndWait();
        } else {
            int custID = CustomerData.getNewID();
            String customerName = customerNameField.getText();
            int addressNewID = AddressData.getNewID();
            int active = 1;
            String createDate = getTimestamp();
            String createdBy = LoginScreenController.getCurrentUser();
            String lastUpdate = getTimestamp();
            String lastUpdateBy = LoginScreenController.getCurrentUser();
            String address = addressField.getText();
            String address2 = address2Field.getText();
            int cityID = selectedCityID;
            String postalCode = postalCodeField.getText();
            String phone = phoneField.getText();
            CustomerData.addCustomer(custID, customerName, addressNewID, active, createDate, createdBy, lastUpdate, lastUpdateBy, address, address2, cityID, postalCode, phone);

            Parent customerSave = FXMLLoader.load(getClass().getResource("CustomerMainScreen.fxml"));
            Scene scene = new Scene(customerSave);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            customerSave.getStylesheets().add(getClass().getResource("sceneStyles.css").toExternalForm());
            window.setScene(scene);
            window.show();
        }
    }


    @FXML
    private void addCustomerCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK) {
            Parent cancelAddCustomer = FXMLLoader.load(getClass().getResource("CustomerMainScreen.fxml"));
            Scene scene = new Scene(cancelAddCustomer);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            cancelAddCustomer.getStylesheets().add(getClass().getResource("sceneStyles.css").toExternalForm());
            window.setScene(scene);
            window.show();
        } else {
            System.out.println("Cancel confirmed.");
        }
        
    }
    public int selectedCityID;
    public void actionPerformed(ActionEvent event) {
        ComboBox<String> cityCB = (ComboBox<String>) event.getSource();
        String selectedCity = (String)cityCB.getSelectionModel().getSelectedItem();

        switch (selectedCity) {
            case "1 - New York":
                selectedCityID = 1;
                break;
            case "2 - Los Angeles":
                selectedCityID = 2;
                break;
            case "3 - Toronto":
                selectedCityID = 3;
                break;
            case "4 - Vancouver":
                selectedCityID = 4;
                break;
            case "5 - Oslo":
               selectedCityID = 5;
                break;
                
            default:
                break;
        }
    }
    
   /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cityCB.setItems(cityList);

    }
    
}
