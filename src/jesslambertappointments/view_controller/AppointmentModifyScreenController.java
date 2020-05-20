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
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jesslambertappointments.model.Appointment;
import jesslambertappointments.model.AppointmentData;
import jesslambertappointments.model.CustomerData;
import static jesslambertappointments.model.CustomerData.getAllCustomers;
import jesslambertappointments.model.UserData;
import static jesslambertappointments.model.UserData.getAllUsers;
import utilities.DBConnection;
import static utilities.DBConnection.getConnection;

/**
 * FXML Controller class
 *
 * @author jess
 */
public class AppointmentModifyScreenController implements Initializable {

    @FXML
    private ComboBox<String> customerNameCB;
    
    @FXML
    private ComboBox<String> userNameCB;
    
    @FXML
    private ComboBox<String> typeCB;
    
    @FXML
    private DatePicker datePicker;
    
    @FXML
    private ComboBox<String> startHour;
    
    @FXML
    private ComboBox<String> startMinute;
    
    @FXML
    private ComboBox<String> endHour;
    
    @FXML
    private ComboBox<String> endMinute;
    
    private static int selectedIndex;
    private static int selectedCustID;
    private static int selectedUserIndex;
    private static int selectedUserID;
    LocalDateTime rightNow = LocalDateTime.now();
    Appointment appointment = AppointmentMainScreenController.selectedAppointment;
    public static String start;
    public static String end;
    public static boolean comparison;

    ObservableList<String> customerList = FXCollections.observableArrayList(CustomerData.getCustomerList());
    
    ObservableList<String> userList = FXCollections.observableArrayList(UserData.getUserList());

    public void getSelectedCust(ActionEvent event) {
        ComboBox<String> customerNameCB = (ComboBox<String>) event.getSource();
        String selectedCust = (String)customerNameCB.getSelectionModel().getSelectedItem();
        
    }
   
    public int getSelectedCustID(){
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT COUNT(*) FROM customer";
            ResultSet result = stmt.executeQuery(query);
            while(result.next())
                //customerCount=result.getInt(1);
                selectedIndex = customerNameCB.getSelectionModel().getSelectedIndex();
                selectedCustID = getAllCustomers().get(selectedIndex).getCustomerID();
            stmt.close();
        } catch(SQLException e) {
            System.out.println("5SQLException: " + e.getMessage());
        }
        return selectedCustID;
    }
    
    public String getSelectedCustName(){
        Appointment appointment = AppointmentMainScreenController.selectedAppointment;
        String selectedCustName = null;
        int customerID = appointment.getCustomerID();
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT * FROM customer WHERE customerId = " + customerID + ";";
            ResultSet result = stmt.executeQuery(query);
            while(result.next())
                //selectedCustID = appointment.getCustomerID();
                selectedCustName = result.getString("customerName");
            stmt.close();
        } catch(SQLException e) {
            System.out.println("5SQLException: " + e.getMessage());
        }
        return selectedCustName;
    }
    
    public void getSelectedUser(ActionEvent event) {
        ComboBox<String> customerNameCB = (ComboBox<String>) event.getSource();
        String selectedUser = (String)customerNameCB.getSelectionModel().getSelectedItem();
        
    }

    public int getSelectedUserID(){
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT COUNT(*) FROM user";
            ResultSet result = stmt.executeQuery(query);
            while(result.next())
                
                selectedUserIndex = userNameCB.getSelectionModel().getSelectedIndex();
                selectedUserID = getAllUsers().get(selectedUserIndex).getUserID();
            stmt.close();
        } catch(SQLException e) {
            System.out.println("5SQLException: " + e.getMessage());
        }
        return selectedUserID;
    }
    
    public String getSelectedUserName(){
        Appointment appointment = AppointmentMainScreenController.selectedAppointment;
        String selectedUserName = null;
        int userID = appointment.getUserID();
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT * FROM user WHERE userId = " + userID + ";";
            ResultSet result = stmt.executeQuery(query);
            while(result.next())
                //selectedCustID = appointment.getCustomerID();
                selectedUserName = result.getString("userName");
            stmt.close();
        } catch(SQLException e) {
            System.out.println("5SQLException: " + e.getMessage());
        }
        return selectedUserName;
    }
    
    public void getSelectedType(ActionEvent event) {
        ComboBox<String> typeCB = (ComboBox<String>) event.getSource();
        String selectedType = (String)typeCB.getSelectionModel().getSelectedItem();
    }
    Timestamp timestamp = new Timestamp(new Date().getTime());
    public String getTimestamp(){
        return timestamp + "";
    }
    
    @FXML
    void modAppointmentClickSave(ActionEvent event) throws IOException {
              
        if (customerNameCB.getValue() == null || userNameCB.getValue() == null || typeCB.getValue() == null || datePicker.getValue() == null || startHour.getValue() == null || startMinute.getValue() == null || endHour.getValue() == null || endMinute.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection Error");
            alert.setHeaderText("Fields Not Completely Filled In!");
            alert.setContentText("Please ensure that you have selected values for all fields.");
            alert.setGraphic(null);
            alert.showAndWait();
        } else if (getEndDateTime().isBefore(getStartDateTime())){
            String start = formatStartDateTime();
            String end = formatEndDateTime();
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Time Error");
            alert.setHeaderText("Invalid Timeframe for Appointment");
            alert.setContentText("Please select an end time that is after the start time.");
            alert.showAndWait();
            
        } else if (getStartDateTime().isBefore(rightNow)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Time Error");
            alert.setHeaderText("Selected Appointment Is In The Past");
            alert.setContentText("Please select an appointment time that is in the future.");
            alert.showAndWait();
            
            String start = formatStartDateTime();    
            
        } else if (checkOverlap() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Overlapping Appointment");
            alert.setHeaderText("Selected Appointment Overlaps Existing Appointment");
            alert.setContentText("Please select an appointment time that does not conflict with an existing appointment.");
            alert.showAndWait();
            
            String start = formatStartDateTime();
            String end = formatEndDateTime();
        
        } else {
            int appointmentID = appointment.getAppointmentID();
            int customerID = getSelectedCustID();
            int userID = getSelectedUserID();
            String type = (String)typeCB.getSelectionModel().getSelectedItem();
            String start = formatStartInstant();
            String end = formatEndInstant();
            String createDate = getTimestamp();
            String createdBy = LoginScreenController.getCurrentUser();
            String lastUpdate = getTimestamp();
            String lastUpdateBy = LoginScreenController.getCurrentUser();
            AppointmentData.modAppointment(appointmentID, customerID, userID, type, start, end, lastUpdate, lastUpdateBy);
            //System.out.println(getSelectedDateToInstant());
            Parent appointmentSave = FXMLLoader.load(getClass().getResource("AppointmentMainScreen.fxml"));
            Scene scene = new Scene(appointmentSave);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appointmentSave.getStylesheets().add(getClass().getResource("sceneStyles.css").toExternalForm());
            window.setScene(scene);
            window.show();
        }

    }
    
    @FXML
    private void modAppointmentCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK) {
            Parent cancelModAppointment = FXMLLoader.load(getClass().getResource("AppointmentMainScreen.fxml"));
            Scene scene = new Scene(cancelModAppointment);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            cancelModAppointment.getStylesheets().add(getClass().getResource("sceneStyles.css").toExternalForm());
            window.setScene(scene);
            window.show();
        
        } else {
            System.out.println("Cancel confirmed.");
        }
    }

    public LocalDateTime getStartDateTime(){
        LocalDate date = datePicker.getValue();
        String hour = startHour.getValue();
        String minute = startMinute.getValue();
        
        LocalDateTime startDateTime = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), Integer.parseInt(hour), Integer.parseInt(minute));
        
        return startDateTime;
    }
    
    public String formatStartDateTime() {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        return customFormatter.format(getStartDateTime());
    }
    
    public LocalDateTime getEndDateTime(){
        LocalDate date = datePicker.getValue();
        String hour = endHour.getValue();
        String minute = endMinute.getValue();
        
        LocalDateTime endDateTime = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), Integer.parseInt(hour), Integer.parseInt(minute));
        
        return endDateTime;
    }
    
    public String formatEndDateTime() {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return customFormatter.format(getEndDateTime());
    }
    
    public Instant getStartInstant(){
        Timestamp timestamp = Timestamp.valueOf(getStartDateTime());
        Instant startInstant = timestamp.toInstant();
        return startInstant;
    }
    public String formatStartInstant() {
        String instantToString = getStartInstant().toString();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime toLDT = LocalDateTime.parse(instantToString, inputFormatter);
        String ldtToString = toLDT.format(outputFormatter);
        return ldtToString;
        
    }
    public Instant getEndInstant(){
        Timestamp timestamp = Timestamp.valueOf(getEndDateTime());
        Instant endInstant = timestamp.toInstant();
        return endInstant;
    }
    public String formatEndInstant() {
        String instantToString = getEndInstant().toString();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime toLDT = LocalDateTime.parse(instantToString, inputFormatter);
        String ldtToString = toLDT.format(outputFormatter);
        return ldtToString;
        
    }
    
    public boolean checkOverlap() {
        boolean checkTime = false;
        try {
            Statement stmt = getConnection().createStatement();
            String sqlStatement = "SELECT start, end FROM appointment";
            ResultSet result = stmt.executeQuery(sqlStatement);
            while(result.next()) {

                start = result.getString("start"); 
                end = result.getString("end");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                LocalDateTime startDT = LocalDateTime.parse(start, formatter);
                LocalDateTime endDT = LocalDateTime.parse(end, formatter);
                if(getStartDateTime().isAfter(startDT) && getStartDateTime().isBefore(endDT) || getEndDateTime().isAfter(startDT) && getEndDateTime().isBefore(endDT)){
                    checkTime = true;
                } 
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return checkTime;
        
    }

    public static String getSelectedDate(){
        Appointment appointment = AppointmentMainScreenController.selectedAppointment;
        String startDate = appointment.getStart();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(startDate, inputFormatter);
        String selectedStartDate = localDate.format(outputFormatter);
        return selectedStartDate;
}
    
    public static final LocalDate localDate (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.parse(getSelectedDate(), formatter);
    return localDate;
}
    public static String getSelectedStartHour(){
        Appointment appointment = AppointmentMainScreenController.selectedAppointment;
        String startDate = appointment.getStart();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("H");
        LocalTime localTime = LocalTime.parse(startDate, inputFormatter);
        String selectedStartHour = localTime.format(outputFormatter);
        return selectedStartHour;
    }
    public static String getSelectedStartMinute(){
        Appointment appointment = AppointmentMainScreenController.selectedAppointment;
        String startDate = appointment.getStart();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("mm");
        LocalTime localTime = LocalTime.parse(startDate, inputFormatter);
        String selectedStartMinute = localTime.format(outputFormatter);
        return selectedStartMinute;
    }
    public static String getSelectedEndHour(){
        Appointment appointment = AppointmentMainScreenController.selectedAppointment;
        String endDate = appointment.getEnd();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("H");
        LocalTime localTime = LocalTime.parse(endDate, inputFormatter);
        String selectedEndHour = localTime.format(outputFormatter);
        return selectedEndHour;
    }
    public static String getSelectedEndMinute(){
        Appointment appointment = AppointmentMainScreenController.selectedAppointment;
        String endDate = appointment.getEnd();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("mm");
        LocalTime localTime = LocalTime.parse(endDate, inputFormatter);
        String selectedEndMinute = localTime.format(outputFormatter);
        return selectedEndMinute;
    }
    
   @FXML
   void handleDatePicker(ActionEvent event) throws IOException {
       
   }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        customerNameCB.setItems(customerList);
        userNameCB.setItems(userList);

        typeCB.getItems().add("Scrum");
        typeCB.getItems().add("Presentation");
        typeCB.getItems().add("Team Building");
        typeCB.getItems().add("Problem Solving");
        typeCB.getItems().add("Status Update");
        typeCB.getItems().add("Innovation");
        
        for (int i = 8; i < 17; i++) {
            startHour.getItems().add(Integer.toString(i));
        }
        
        for (int i = 8; i < 18; i++) {
            endHour.getItems().add(Integer.toString(i));
        }

        startMinute.getItems().add("00");
        startMinute.getItems().add("15");
        startMinute.getItems().add("30");
        startMinute.getItems().add("45");
        
        endMinute.getItems().add("00");
        endMinute.getItems().add("15");
        endMinute.getItems().add("30");
        endMinute.getItems().add("45");
        
        
        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(
                    empty || 
                    date.getDayOfWeek() == DayOfWeek.SATURDAY || 
                    date.getDayOfWeek() == DayOfWeek.SUNDAY ||
                    date.isBefore(LocalDate.now()));
                if(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY || date.isBefore(LocalDate.now())) {
                    setStyle("-fx-background-color: #999999;");
                }
            }
        });
        
        customerNameCB.setValue(getSelectedCustName());
        userNameCB.setValue(getSelectedUserName());
        typeCB.setValue(appointment.getType());
        datePicker.setValue(localDate(getSelectedDate()));
        startHour.setValue(getSelectedStartHour());
        startMinute.setValue(getSelectedStartMinute());
        endHour.setValue(getSelectedEndHour());
        endMinute.setValue(getSelectedEndMinute());
    }    
    
    
}
