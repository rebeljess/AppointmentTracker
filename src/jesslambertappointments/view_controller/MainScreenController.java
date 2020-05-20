/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jesslambertappointments.view_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jesslambertappointments.model.AppointmentData;

/**
 * FXML Controller class
 *
 * @author jess
 */
public class MainScreenController implements Initializable {
    
    @FXML
    private void openUserScreen(ActionEvent event) throws IOException{
        
            Parent userScreenParent = FXMLLoader.load(getClass().getResource("UserMainScreen.fxml"));
            Scene userScreenScene = new Scene(userScreenParent);
            Stage userScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            userScreenStage.setTitle("Manage Users");
            userScreenStage.setScene(userScreenScene);
            userScreenStage.show();
            userScreenScene.getStylesheets().add(getClass().getResource("sceneStyles.css").toExternalForm());

    }
    
    @FXML
    private void openCustomerScreen(ActionEvent event) throws IOException{
        
            Parent customerScreenParent = FXMLLoader.load(getClass().getResource("CustomerMainScreen.fxml"));
            Scene customerScreenScene = new Scene(customerScreenParent);
            Stage customerScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            customerScreenStage.setTitle("Manager Customers");
            customerScreenStage.setScene(customerScreenScene);
            customerScreenStage.show();
            customerScreenScene.getStylesheets().add(getClass().getResource("sceneStyles.css").toExternalForm());

    }
    
    @FXML
    private void openAppointmentScreen(ActionEvent event) throws IOException{
        
            Parent appointmentScreenParent = FXMLLoader.load(getClass().getResource("AppointmentMainScreen.fxml"));
            Scene appointmentScreenScene = new Scene(appointmentScreenParent);
            Stage appointmentScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appointmentScreenStage.setTitle("Manage Appointments");
            appointmentScreenStage.setScene(appointmentScreenScene);
            appointmentScreenStage.show();
            appointmentScreenScene.getStylesheets().add(getClass().getResource("sceneStyles.css").toExternalForm());

    }
    
    @FXML
    private void openReportsScreen(ActionEvent event) throws IOException{
        
            Parent appointmentScreenParent = FXMLLoader.load(getClass().getResource("ReportsScreen.fxml"));
            Scene appointmentScreenScene = new Scene(appointmentScreenParent);
            Stage appointmentScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appointmentScreenStage.setTitle("Reports");
            appointmentScreenStage.setScene(appointmentScreenScene);
            appointmentScreenStage.show();
            appointmentScreenScene.getStylesheets().add(getClass().getResource("sceneStyles.css").toExternalForm());

    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        AppointmentData.upcomingAppointmentAlert();
        
    }
    
}
