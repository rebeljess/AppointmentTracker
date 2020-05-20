/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jesslambertappointments.view_controller;


import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utilities.DBConnection;
import utilities.ActivityLog;

/**
 * FXML Controller class
 *
 * @author jess
 */
public class LoginScreenController implements Initializable {
    
    @FXML
    private Label loginTitle;
    
    @FXML
    private Label usernameID;
    
    @FXML
    private Label passwordID;
    
    @FXML
    private TextField usernameInput;
    
    @FXML
    private PasswordField passwordInput;
    
    @FXML
    private Button submitButton;
 
    private String alertTitle;
    private String alertHeader;
    private String alertText;
    private static String currentUser;
    
    public static String getCurrentUser() {
        return currentUser;
    }
    
    public String getUsernameInput(){
        return usernameInput.getText();
    } 
    public String getPasswordInput(){
        return passwordInput.getText();
    }
    
    @FXML 
    public void handleLogin(ActionEvent event) throws IOException {
        PreparedStatement ps;
        ResultSet rs;
        String username = usernameInput.getText();
        String pass = String.valueOf(passwordInput.getText());
        
        String query = "SELECT * FROM user WHERE userName = ? AND password = ?";
        
        try {
            ps = DBConnection.getConnection().prepareStatement(query);
            
            ps.setString(1, username);
            ps.setString(2, pass);
            currentUser = username;
            rs = ps.executeQuery();
            
            if(rs.next()) {
                currentUser = username;
                Parent addMainParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene addMainScene = new Scene(addMainParent);
                Stage addMainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                addMainStage.setTitle("Appointment Scheduler");
                addMainStage.setScene(addMainScene);
                addMainStage.show();
                addMainScene.getStylesheets().add(getClass().getResource("sceneStyles.css").toExternalForm());
                ActivityLog.log(username, true);
            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(alertTitle);
                alert.setHeaderText(alertHeader);
                alert.setContentText(alertText);
                alert.showAndWait();
                ActivityLog.log(username, false);
            }
        } catch (SQLException ex){
            ActivityLog.log(username, false);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Locale locale = Locale.getDefault();
        rb = ResourceBundle.getBundle("jesslambertappointments.resources/login", locale);
        System.out.println(Locale.getDefault());
        loginTitle.setText(rb.getString("title"));
        usernameID.setText(rb.getString("username"));
        passwordID.setText(rb.getString("password"));
        submitButton.setText(rb.getString("button"));
        alertTitle = rb.getString("alertTitle");
        alertHeader = rb.getString("alertHeader");
        alertText = rb.getString("alertText");
    }    
      
}
