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
import jesslambertappointments.model.User;
import jesslambertappointments.model.UserData;
import static jesslambertappointments.model.UserData.getAllUsers;

/**
 * FXML Controller class
 *
 * @author jess
 */
public class UserMainScreenController implements Initializable {
    
    @FXML
    private TableView<User> usersTable;
    
    @FXML 
    private TableColumn<User, Integer> usersTableUserID;
    
    @FXML
    private TableColumn<User, String> usersTableUserName;
    
    public static User selectedUser;
    private static int selectedUserIndex;
    
    public static int userToModifyIndex() {
        return selectedUserIndex;
    }
    
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
    
    @FXML
    void handleDelete(ActionEvent event) {
        
        if(usersTable.getSelectionModel().getSelectedItem() != null) {
            selectedUser = usersTable.getSelectionModel().getSelectedItem();
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("User Deletion");
            alert.setHeaderText("Please Confirm Delete");
            alert.setContentText("Are you sure you want to delete " + selectedUser.getUsername() + "?");
            Optional<ButtonType> result = alert.showAndWait();
        
            if (result.get() == ButtonType.OK) {
                UserData.deleteUser(selectedUser.getUserID());
                usersTable.setItems(UserData.getAllUsers());
                System.out.println(selectedUser.getUsername() + " has been deleted.");
                
            } else {
                System.out.println("You clicked cancel, " + selectedUser.getUsername() + " was not deleted");
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
    private void clickAddUserScreen(ActionEvent event) throws IOException{
        
            Parent userScreenParent = FXMLLoader.load(getClass().getResource("UserAddScreen.fxml"));
            Scene userScreenScene = new Scene(userScreenParent);
            Stage userScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            userScreenStage.setTitle("Add User");
            userScreenStage.setScene(userScreenScene);
            userScreenStage.show();
            userScreenScene.getStylesheets().add(getClass().getResource("sceneStyles.css").toExternalForm());

    }
    
    public void updateUsersTable() {
        usersTable.setItems(getAllUsers());
    }
    
   @FXML
   private void clickModUserScreen(ActionEvent event) throws IOException{
       if(usersTable.getSelectionModel().getSelectedItem() != null) {
            selectedUser = usersTable.getSelectionModel().getSelectedItem();
            
            UserData.modUser(selectedUser.getUserID(), selectedUser.getUsername(), selectedUser.getPassword(), selectedUser.getActive(), selectedUser.getCreateDate(), selectedUser.getCreatedBy(), selectedUser.getLastUpdate(), selectedUser.getLastUpdateBy());
            Parent modUserParent = FXMLLoader.load(getClass().getResource("UserModifyScreen.fxml"));
            Scene modUserScene = new Scene(modUserParent);
            Stage modUserStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            modUserStage.setTitle("Modify User");
            modUserStage.setScene(modUserScene);
            modUserStage.show();
            modUserScene.getStylesheets().add(getClass().getResource("sceneStyles.css").toExternalForm());
            
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Selection Error");
            alert.setHeaderText("No User Was Selected");
            alert.setContentText("Please select a user from the table.");
            alert.showAndWait();
        }

   }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        getAllUsers();
        
        usersTableUserID.setCellValueFactory(cellData -> cellData.getValue().userIDProperty().asObject());
        usersTableUserName.setCellValueFactory(cell -> cell.getValue().usernameProperty());
        usersTable.setItems(UserData.getAllUsers());
       
    }    
    
}
