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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jesslambertappointments.model.UserData;

/**
 * FXML Controller class
 *
 * @author jess
 */
public class UserAddScreenController implements Initializable {

    @FXML
    private TextField userIDField;
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private TextField passwordField;
    
    @FXML
    private RadioButton activeUser;
    
    @FXML
    private RadioButton notActive;
    
    private boolean isActive;
    
    @FXML
    private TextField createDateField;
    
    @FXML
    private TextField createdByField;
    
    @FXML
    private TextField lastUpdateField;
    
    @FXML
    private TextField lastUpdateByField;
    
//    private String timestamp;
    
    Timestamp timestamp = new Timestamp(new Date().getTime());
 
    private String currentUser;
    
    int active;

    public int getActive(){
        if (activeUser.isSelected()){
          this.active = 1;  
        } else if (notActive.isSelected()) {
          this.active = 0;  
        }
        return active;
    }    
    
    @FXML 
    void addActiveUser(ActionEvent event) {
        isActive = true;
        activeUser.setSelected(true);
    }
    
    @FXML
    void addNotActiveUser(ActionEvent event) {
        isActive = false;
        notActive.setSelected(true);
    }
    
    @FXML
    void addUserClickSave(ActionEvent event) throws IOException {

        if (usernameField.getText().equals("") || passwordField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection Error");
            alert.setHeaderText("Fields Not Completely Filled In!");
            alert.setContentText("Please ensure that you have filled in all fields.");
            alert.setGraphic(null);
            alert.showAndWait();
        } else {
            int userID = Integer.parseInt(userIDField.getText());
            String username = usernameField.getText();
            String password = passwordField.getText();
            int active = getActive();
            String createDate = createDateField.getText();
            String createdBy = createdByField.getText();
            String lastUpdate = lastUpdateField.getText();
            String lastUpdateBy = lastUpdateByField.getText();
            UserData.addUser(userID, username, password, active, createDate, createdBy, lastUpdate, lastUpdateBy);

            Parent userSave = FXMLLoader.load(getClass().getResource("UserMainScreen.fxml"));
            Scene scene = new Scene(userSave);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            userSave.getStylesheets().add(getClass().getResource("sceneStyles.css").toExternalForm());
            window.setScene(scene);
            window.show();
        }
    }
    
    @FXML
    private void addUserCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK) {
            Parent cancelAddUser = FXMLLoader.load(getClass().getResource("UserMainScreen.fxml"));
            Scene scene = new Scene(cancelAddUser);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            cancelAddUser.getStylesheets().add(getClass().getResource("sceneStyles.css").toExternalForm());
            window.setScene(scene);
            window.show();
        } else {
            System.out.println("Cancel confirmed.");
        }
    }
    
    private int userID;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        userID = UserData.getNewID();
        userIDField.setText(userID + "");
        createDateField.setText(timestamp +"");
        createdByField.setText(LoginScreenController.getCurrentUser());
        lastUpdateField.setText(timestamp + "");
        lastUpdateByField.setText(LoginScreenController.getCurrentUser());
    }    
    
}
