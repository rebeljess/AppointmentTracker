/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jesslambertappointments.view_controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jesslambertappointments.model.Customer;
import jesslambertappointments.model.CustomerData;
import static jesslambertappointments.model.CustomerData.getAllCustomers;
import static jesslambertappointments.model.CustomerData.getCustomerIDCount;

/**
 * FXML Controller class
 *
 * @author jess
 */
public class CustomerMainScreenController implements Initializable {
    
    @FXML
    private TableView<Customer> customersTable;
    
    @FXML 
    private TableColumn<Customer, Integer> customersTableCustomerID;
    
    @FXML
    private TableColumn<Customer, String> customersTableCustomerName;
    
    @FXML
    private void goBack(ActionEvent event) throws IOException{
                Parent addMainParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene addMainScene = new Scene(addMainParent);
                Stage addMainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                addMainStage.setTitle("Appointment Scheduler");
                addMainStage.setScene(addMainScene);
                addMainStage.show();
                addMainScene.getStylesheets().add(getClass().getResource("sceneStyles.css").toExternalForm());
    }
    
    public static Customer selectedCustomer;
    private static int selectedCustomerIndex;
    
    public static int customerToModifyIndex() {
        return selectedCustomerIndex;
    }
    
    @FXML
    void handleDelete(ActionEvent event) {
        if(customersTable.getSelectionModel().getSelectedItem() != null) {
            selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Deleting Customer");
            alert.setHeaderText("Please Confirm Delete");
            alert.setContentText("Are you sure you want to delete " + selectedCustomer.getCustomerName() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            
            if (result.get() == ButtonType.OK) {
                CustomerData.deleteCustomer(selectedCustomer.getCustomerID());
                customersTable.setItems(CustomerData.getAllCustomers());
                CustomerData.getCustomerIDCount();
                System.out.println(selectedCustomer.getCustomerName() + " has been deleted.");
            } else {
                System.out.println("You clicked cancel, " + selectedCustomer.getCustomerName() + " was not deleted.");
            }
            
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Selection Error");
            alert.setHeaderText("No User Was Selected");
            alert.setContentText("Please select a user from the table.");
            alert.showAndWait();
        }
            
    }
    
    @FXML
    private void clickAddCustomerScreen(ActionEvent event) throws IOException{
        
        Parent customerScreenParent = FXMLLoader.load(getClass().getResource("CustomerAddScreen.fxml"));
        Scene customerScreenScene = new Scene(customerScreenParent);
        Stage customerScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerScreenStage.setTitle("Add Customer");
        customerScreenStage.setScene(customerScreenScene);
        customerScreenStage.show();
        customerScreenScene.getStylesheets().add(getClass().getResource("sceneStyles.css").toExternalForm());
    }
    
    public void updateCustomersTable() {
        customersTable.setItems(getAllCustomers());
    }
    
    @FXML
    private void clickModCustomerScreen(ActionEvent event) throws IOException {
        if(customersTable.getSelectionModel().getSelectedItem() != null) {
            selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
            
            //CustomerData.modCustomer();
            Parent customerModScreenParent = FXMLLoader.load(getClass().getResource("CustomerModifyScreen.fxml"));
            Scene customerModScreenScene = new Scene(customerModScreenParent);
            Stage customerModScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            customerModScreenStage.setTitle("Modify Customer");
            customerModScreenStage.setScene(customerModScreenScene);
            customerModScreenStage.show();
            customerModScreenScene.getStylesheets().add(getClass().getResource("sceneStyles.css").toExternalForm());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection Error");
            alert.setHeaderText("No User Was Selected");
            alert.setContentText("Please select a user from the table.");
            alert.showAndWait();
        }
            
    }
    public static int testid = getCustomerIDCount() - 1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getAllCustomers();
        
        customersTableCustomerID.setCellValueFactory(cellData -> cellData.getValue().customerIDProperty().asObject());
        customersTableCustomerName.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        customersTable.setItems(CustomerData.getAllCustomers());
    }    
    
}
