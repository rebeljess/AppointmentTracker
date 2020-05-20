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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jesslambertappointments.model.Appointment;
import jesslambertappointments.model.AppointmentData;
import static jesslambertappointments.model.AppointmentData.getAllAppointments;
import static jesslambertappointments.model.AppointmentData.getMonthAppointments;
import static jesslambertappointments.model.AppointmentData.getWeekAppointments;

/**
 * FXML Controller class
 *
 * @author jess
 */
public class AppointmentMainScreenController implements Initializable {

    @FXML
    private TableView<Appointment> appointmentsTable;
    
    @FXML
    private TableColumn<Appointment, Integer> appointmentsTableAppointmentID;
    
    @FXML
    private TableColumn<Appointment, Integer> appointmentsTableCustomerID;
    
    @FXML
    private TableColumn<Appointment, Integer> appointmentsTableUserID;
    
    @FXML
    private TableColumn<Appointment, String> appointmentsTableAppointmentType;
    
    @FXML
    private TableColumn<Appointment, String> appointmentsTableStart;
    
    @FXML
    private TableColumn<Appointment, String> appointmentsTableEnd;
    
    @FXML
    private TableView<Appointment> appointmentsMonthTable;
    
    @FXML
    private TableColumn<Appointment, Integer> appointmentsMonthTableAppointmentID;
    
    @FXML
    private TableColumn<Appointment, Integer> appointmentsMonthTableCustomerID;
    
    @FXML
    private TableColumn<Appointment, Integer> appointmentsMonthTableUserID;
    
    @FXML
    private TableColumn<Appointment, String> appointmentsMonthTableAppointmentType;
    
    @FXML
    private TableColumn<Appointment, String> appointmentsMonthTableStart;
    
    @FXML
    private TableColumn<Appointment, String> appointmentsMonthTableEnd;
    
    @FXML
    private TableView<Appointment> appointmentsWeekTable;
    
    @FXML
    private TableColumn<Appointment, Integer> appointmentsWeekTableAppointmentID;
    
    @FXML
    private TableColumn<Appointment, Integer> appointmentsWeekTableCustomerID;
    
    @FXML
    private TableColumn<Appointment, Integer> appointmentsWeekTableUserID;
    
    @FXML
    private TableColumn<Appointment, String> appointmentsWeekTableAppointmentType;
    
    @FXML
    private TableColumn<Appointment, String> appointmentsWeekTableStart;
    
    @FXML
    private TableColumn<Appointment, String> appointmentsWeekTableEnd;
            
    public static Appointment selectedAppointment;
    
    
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
    private void clickAddAppointmentScreen(ActionEvent event) throws IOException{
        
        Parent appointmentScreenParent = FXMLLoader.load(getClass().getResource("AppointmentAddScreen.fxml"));
        Scene appointmentScreenScene = new Scene(appointmentScreenParent);
        Stage appointmentScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appointmentScreenStage.setTitle("Add Appointment");
        appointmentScreenStage.setScene(appointmentScreenScene);
        appointmentScreenStage.show();
        appointmentScreenScene.getStylesheets().add(getClass().getResource("sceneStyles.css").toExternalForm());
    }
    
    @FXML
    private void clickModAppointmentScreen(ActionEvent event) throws IOException{
        if(appointmentsTable.getSelectionModel().getSelectedItem() != null) {
            selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();

            Parent appointmentScreenParent = FXMLLoader.load(getClass().getResource("AppointmentModifyScreen.fxml"));
            Scene appointmentScreenScene = new Scene(appointmentScreenParent);
            Stage appointmentScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appointmentScreenStage.setTitle("Modify Appointment");
            appointmentScreenStage.setScene(appointmentScreenScene);
            appointmentScreenStage.show();
            appointmentScreenScene.getStylesheets().add(getClass().getResource("sceneStyles.css").toExternalForm());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection Error");
            alert.setHeaderText("No Appointment Was Selected");
            alert.setContentText("Please select an appointment from the table.");
            alert.showAndWait();
        }
    }
    
    @FXML
    void handleDelete(ActionEvent event) {
        if(appointmentsTable.getSelectionModel().getSelectedItem() != null) {
            selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Deleting Appointment");
            alert.setHeaderText("Please Confirm Delete");
            alert.setContentText("Are you sure you want to delete the selected appointment?");

            // Lambda used to simplify response handling on alert
            // You can compare the lambda used here to handle the response to the alert
            // to the ones used in CustomerMainScreenController(lines 74 - 83) 
            // and UserMainScreenController(lines 74 - 83)
            // Even though the lambda here doesn't really save any lines, 
            // it does clarify what the response is connected to
            alert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {
                    AppointmentData.deleteAppointment(selectedAppointment.getAppointmentID());
                    appointmentsTable.setItems(AppointmentData.getAllAppointments());
                    AppointmentData.getAppointmentIDCount();
                    System.out.println("Appointment has been deleted.");
                } else {
                    System.out.println("You clicked cancel, appointment was not deleted.");
                }
            }));
            
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Selection Error");
            alert.setHeaderText("No Appointment Was Selected");
            alert.setContentText("Please select an appointment from the table.");
            alert.showAndWait();
        }
            
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getAllAppointments();
        // Lambdas used to simplify setting tableview values.
        // Rather than creating a block of code for each column in each table,
        // Using a lambda for each column makes the data seem more manageable
        // In this case, the lambda makes it easier to see what is going where.
        // It saves about 4 lines of code per column
        
        appointmentsTableAppointmentID.setCellValueFactory(cellData -> cellData.getValue().appointmentIDProperty().asObject());
        appointmentsTableCustomerID.setCellValueFactory(cellData -> cellData.getValue().customerIDProperty().asObject());
        appointmentsTableUserID.setCellValueFactory(cellData -> cellData.getValue().userIDProperty().asObject());
        appointmentsTableAppointmentType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        appointmentsTableStart.setCellValueFactory(cellData -> cellData.getValue().startProperty());
        appointmentsTableEnd.setCellValueFactory(cellData -> cellData.getValue().endProperty());
        appointmentsTable.setItems(AppointmentData.getAllAppointments());
        
        getMonthAppointments();
        
        appointmentsMonthTableAppointmentID.setCellValueFactory(cellData -> cellData.getValue().appointmentIDProperty().asObject());
        appointmentsMonthTableCustomerID.setCellValueFactory(cellData -> cellData.getValue().customerIDProperty().asObject());
        appointmentsMonthTableUserID.setCellValueFactory(cellData -> cellData.getValue().userIDProperty().asObject());
        appointmentsMonthTableAppointmentType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        appointmentsMonthTableStart.setCellValueFactory(cellData -> cellData.getValue().startProperty());
        appointmentsMonthTableEnd.setCellValueFactory(cellData -> cellData.getValue().endProperty());
        appointmentsMonthTable.setItems(AppointmentData.getMonthAppointments());
        
        getWeekAppointments();
        
        appointmentsWeekTableAppointmentID.setCellValueFactory(cellData -> cellData.getValue().appointmentIDProperty().asObject());
        appointmentsWeekTableCustomerID.setCellValueFactory(cellData -> cellData.getValue().customerIDProperty().asObject());
        appointmentsWeekTableUserID.setCellValueFactory(cellData -> cellData.getValue().userIDProperty().asObject());
        appointmentsWeekTableAppointmentType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        appointmentsWeekTableStart.setCellValueFactory(cellData -> cellData.getValue().startProperty());
        appointmentsWeekTableEnd.setCellValueFactory(cellData -> cellData.getValue().endProperty());
        appointmentsWeekTable.setItems(AppointmentData.getWeekAppointments());
    }    
    
}
