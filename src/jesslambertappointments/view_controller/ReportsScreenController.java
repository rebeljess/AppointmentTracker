/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jesslambertappointments.view_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import utilities.DBConnection;

/**
 * FXML Controller class
 *
 * @author jess
 */
public class ReportsScreenController implements Initializable {
    
    @FXML
    private TextArea apptsContent;
    
    @FXML
    private TextArea userSchedContent;
    
    @FXML
    private TextArea custApptContent;
    
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

    public void apptList(){
    try {
            Statement statement = DBConnection.getConnection().createStatement();
            String query = "SELECT type, MONTHNAME(start) as 'Month', COUNT(*) as 'Count' FROM appointment GROUP BY MONTH(start), type";
            ResultSet results = statement.executeQuery(query);
            StringBuilder apptReport = new StringBuilder();
            apptReport.append(String.format("%1$-10s %2$-15s %3$-5s \n", "Month", "Type", "Count"));
            apptReport.append(String.join("",Collections.nCopies(33, ".")));
            apptReport.append("\n");
            
            while(results.next()) {
                apptReport.append(String.format("%1$-10s %2$-17s %3$-10s \n",results.getString("Month"), results.getString("type"), results.getInt("Count")));
            }
            statement.close();
            apptsContent.setText(apptReport.toString());
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
 
    }
    
    public void userSched() {
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            String query = "SELECT user.userName, appointment.type, customer.customerName, start, end " +
                    "FROM appointment JOIN customer ON customer.customerId = appointment.customerId " +
                    "JOIN user ON user.userId = appointment.userId " +
                    "GROUP BY appointment.userId, MONTH(start), start";
            ResultSet results = statement.executeQuery(query);
            StringBuilder userSchedReport = new StringBuilder();
            userSchedReport.append(String.format("%1$-5s %2$-15s %3$-15s %4$-22s %5$s \n", 
                    "User", "Appointment", "Customer", "Start", "End"));
            userSchedReport.append(String.join("", Collections.nCopies(82, ".")));
            userSchedReport.append("\n");
            while(results.next()) {
                userSchedReport.append(String.format("%1$-5s %2$-15s %3$-15s %4$-22s %5$s \n", 
                    results.getString("userName"), results.getString("type"), results.getString("customerName"),
                    results.getString("start"), results.getString("end")));
            }
            statement.close();
            userSchedContent.setText(userSchedReport.toString());
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
    
    public void custAppt() {
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            String query = "SELECT customer.customerName, appointment.type, COUNT(*) as 'Count' FROM customer JOIN appointment " +
                "ON customer.customerId = appointment.customerId GROUP BY customerName, type";
            ResultSet results = statement.executeQuery(query);
            StringBuilder custApptReport = new StringBuilder();
            custApptReport.append(String.format("%1$-15s %2$-15s %3$-5s \n", "Customer", "Type", "Count"));
            custApptReport.append(String.join("", Collections.nCopies(39, ".")));
            custApptReport.append("\n");
            while(results.next()) {
                custApptReport.append(String.format("%1$-15s %2$-17s %3$-10s \n", 
                    results.getString("customerName"), results.getString("type"), results.getInt("Count")));
            }
            statement.close();
            custApptContent.setText(custApptReport.toString());
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        apptList();
        userSched();
        custAppt();
    }    
}
