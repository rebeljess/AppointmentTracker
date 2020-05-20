/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jesslambertappointments.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import static jesslambertappointments.model.UserData.getAllUsers;
import jesslambertappointments.view_controller.AppointmentAddScreenController;
import static jesslambertappointments.view_controller.AppointmentAddScreenController.end;
import static jesslambertappointments.view_controller.AppointmentAddScreenController.start;
import jesslambertappointments.view_controller.LoginScreenController;
import utilities.DBConnection;
import static utilities.DBConnection.getConnection;

/**
 *
 * @author jess
 */
public class AppointmentData {
    private static int appointmentID;
    private static int userID;
    private static int customerID;
    private static String title;
    private static String description;
    private static String location;
    private static String contact;
    private static String type;
    private static String url;
    private static String start;
    private static String end;
    private static String createDate;
    private static String createdBy;
    private static String lastUpdate;
    private static String lastUpdateBy;
    private static int appointmentIDCount;
    
    static int appointmentNewID;
    private static int lastRowIndex;
    private static int newID;
    
    public static void addAppointment(int appointmentID, int customerID, int userID, String type, String start, String end, String createDate, String createdBy, String lastUpdate, String lastUpdateBy) {
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "INSERT INTO appointment (appointmentId, customerId, userId, title, description, location, contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES ('"+appointmentNewID+"', '"+customerID+"', '"+userID+"', '"+title+"', '"+description+"', '"+location+"', '"+contact+"', '"+type+"', '"+url+"', '"+start+"', '"+end+"', '"+createDate+"', '"+createdBy+"', '"+lastUpdate+"', '"+lastUpdateBy+"')";
            stmt.execute(query);
                        
        } catch(SQLException e) {
            System.out.println("Appt1SQLException: " + e.getMessage());
        } catch(Exception e) {
            System.out.println("Appt1Exception: " + e.getMessage());
        }
    }
    
    public static void modAppointment(int appointmentID, int customerID, int userID, String type, String start, String end, String lastUpdate, String lastUpdateBy) {
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "UPDATE appointment SET customerId = '"+ customerID +"', userId = '"+ userID +"', type = '"+ type +"', start = '"+ start +"', end = '"+ end +"', lastUpdate = '"+ lastUpdate +"', lastUpdateBy = '"+ lastUpdateBy +"' WHERE appointmentId ='" + appointmentID + "';";
            stmt.execute(query);
           
        } catch(SQLException e) {
            System.out.println("Appt2SQLException: " + e.getMessage());
        }
    }
    
    public static void deleteAppointment(int appointmentID) {
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "DELETE FROM appointment WHERE appointmentId =" + appointmentID;
            stmt.executeUpdate(query);
        } catch(SQLException e) {
            System.out.println("Appt3SQLException: " + e.getMessage());
        }
    }
    
    public static int getAppointmentIDCount() {
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT COUNT(*) FROM appointment";
            ResultSet result = stmt.executeQuery(query);
            while(result.next())
                appointmentIDCount=result.getInt(1);
            stmt.close();
        } catch(SQLException e) {
            System.out.println("Appt4SQLException: " + e.getMessage());
        }
        return appointmentIDCount;
    }
    
    public static int getNewID() {
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT COUNT(*) FROM appointment";
            ResultSet result = stmt.executeQuery(query);
           
            while(result.next())
                appointmentIDCount=result.getInt(1);
                lastRowIndex = appointmentIDCount - 1;
                newID = getAllAppointments().get(lastRowIndex).getAppointmentID() + 1;
            stmt.close();
        } catch(SQLException e) {
            System.out.println("Appt5SQLException: " + e.getMessage());
        }
        return newID;
    }
    
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    
    public static ObservableList<Appointment> getAllAppointments() {
        allAppointments.clear();
        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String sqlStatement = "SELECT * FROM appointment";
            ResultSet result = stmt.executeQuery(sqlStatement);
            while(result.next()) {
                
                        
                        appointmentID = result.getInt("appointmentId");
                        customerID = result.getInt("customerId");
                        userID = result.getInt("userId");
                        type = result.getString("type");
                        start = result.getString("start");
                        end = result.getString("end");
                        createDate = result.getString("createDate");
                        createdBy = result.getString("createdBy");
                        lastUpdate = result.getString("lastUpdate");
                        lastUpdateBy = result.getString("lastUpdateBy");
                        
                        
                        String startDate = result.getString("start");
                        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
                        LocalDateTime localDateTime = LocalDateTime.parse(startDate, inputFormatter);
                        Instant ldtToInstant = Instant.parse(outputFormatter.format(localDateTime));
                        Timestamp instantToTimestamp = Timestamp.from(ldtToInstant);
                        String newStart = instantToTimestamp.toString();
                        
                        String endDate = result.getString("end");
                        LocalDateTime localEndDateTime = LocalDateTime.parse(endDate, inputFormatter);
                        Instant ldtEndToInstant = Instant.parse(outputFormatter.format(localEndDateTime));
                        Timestamp instantEndToTimestamp = Timestamp.from(ldtEndToInstant);
                        String newEnd = instantEndToTimestamp.toString();
                
               
                allAppointments.add(new Appointment(appointmentID, customerID, userID, type, newStart, newEnd, createDate, createdBy, lastUpdate, lastUpdateBy));
            }
        } catch (SQLException e) {
            System.out.println("Appt6SQLException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Appt6Exception: " + e.getMessage());
        }
        return allAppointments;
    }
    private static ObservableList<Appointment> monthAppointments = FXCollections.observableArrayList();
    public static ObservableList<Appointment> getMonthAppointments() {
        monthAppointments.clear();
        LocalDate begin = LocalDate.now();
        LocalDate oneMonth = begin.plusMonths(1);
        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String sqlStatement = "SELECT * FROM appointment WHERE start >= '" + begin + "' AND start <= '" + oneMonth + "'";
            ResultSet result = stmt.executeQuery(sqlStatement);
            while(result.next()) {
                appointmentID = result.getInt("appointmentId");
                        customerID = result.getInt("customerId");
                        userID = result.getInt("userId");
                        type = result.getString("type");
                        start = result.getString("start");
                        end = result.getString("end");
                        createDate = result.getString("createDate");
                        createdBy = result.getString("createdBy");
                        lastUpdate = result.getString("lastUpdate");
                        lastUpdateBy = result.getString("lastUpdateBy");
                        
                        
                        String startDate = result.getString("start");
                        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
                        LocalDateTime localDateTime = LocalDateTime.parse(startDate, inputFormatter);
                        Instant ldtToInstant = Instant.parse(outputFormatter.format(localDateTime));
                        Timestamp instantToTimestamp = Timestamp.from(ldtToInstant);
                        String newStart = instantToTimestamp.toString();
                        
                        String endDate = result.getString("end");
                        LocalDateTime localEndDateTime = LocalDateTime.parse(endDate, inputFormatter);
                        Instant ldtEndToInstant = Instant.parse(outputFormatter.format(localEndDateTime));
                        Timestamp instantEndToTimestamp = Timestamp.from(ldtEndToInstant);
                        String newEnd = instantEndToTimestamp.toString();
                
               
                monthAppointments.add(new Appointment(appointmentID, customerID, userID, type, newStart, newEnd, createDate, createdBy, lastUpdate, lastUpdateBy));
            }
        } catch (SQLException e) {
            System.out.println("Appt6SQLException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Appt6Exception: " + e.getMessage());
        }
        return monthAppointments;
    }
    private static ObservableList<Appointment> weekAppointments = FXCollections.observableArrayList();
    public static ObservableList<Appointment> getWeekAppointments() {
        weekAppointments.clear();
        LocalDate begin = LocalDate.now();
        LocalDate oneWeek = begin.plusWeeks(1);
        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String sqlStatement = "SELECT * FROM appointment WHERE start >= '" + begin + "' AND start <= '" + oneWeek + "'";
            ResultSet result = stmt.executeQuery(sqlStatement);
            while(result.next()) {
                
                        appointmentID = result.getInt("appointmentId");
                        customerID = result.getInt("customerId");
                        userID = result.getInt("userId");
                        type = result.getString("type");
                        start = result.getString("start");
                        end = result.getString("end");
                        createDate = result.getString("createDate");
                        createdBy = result.getString("createdBy");
                        lastUpdate = result.getString("lastUpdate");
                        lastUpdateBy = result.getString("lastUpdateBy");
                        
                        
                        String startDate = result.getString("start");
                        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
                        LocalDateTime localDateTime = LocalDateTime.parse(startDate, inputFormatter);
                        Instant ldtToInstant = Instant.parse(outputFormatter.format(localDateTime));
                        Timestamp instantToTimestamp = Timestamp.from(ldtToInstant);
                        String newStart = instantToTimestamp.toString();
                        
                        String endDate = result.getString("end");
                        LocalDateTime localEndDateTime = LocalDateTime.parse(endDate, inputFormatter);
                        Instant ldtEndToInstant = Instant.parse(outputFormatter.format(localEndDateTime));
                        Timestamp instantEndToTimestamp = Timestamp.from(ldtEndToInstant);
                        String newEnd = instantEndToTimestamp.toString();
                
               
                weekAppointments.add(new Appointment(appointmentID, customerID, userID, type, newStart, newEnd, createDate, createdBy, lastUpdate, lastUpdateBy));
            }
        } catch (SQLException e) {
            System.out.println("Appt6SQLException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Appt6Exception: " + e.getMessage());
        }
        return weekAppointments;
    }
    
    static int currentUserID;
    public static int getCurrentUserID(){
        try{
            Statement stmt = DBConnection.getConnection().createStatement();
            String userName = LoginScreenController.getCurrentUser();
            String query = "SELECT * FROM user WHERE userName = '" + userName + "';";
            //int selectedUserID = 0;
            ResultSet result = stmt.executeQuery(query);
            
            while(result.next())
                currentUserID = result.getInt("userId");
            stmt.close();
        } catch(SQLException e) {
            System.out.println("5SQLException: " + e.getMessage());
        }
        return currentUserID;
    }
    
    public static void upcomingAppointmentAlert(){
        
        int user = getCurrentUserID();
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            String query = "SELECT * FROM appointment WHERE userId= " + user;
            ResultSet result = statement.executeQuery(query);
            while(result.next()) {
               
                        
                        customerID = result.getInt("customerId");
                        userID = result.getInt("userId");
                        type = result.getString("type");
                        start = result.getString("start");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                LocalDateTime startDT = LocalDateTime.parse(start, formatter);
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
                Instant ldtToInstant = Instant.parse(outputFormatter.format(startDT));
                Timestamp instantToTimestamp = Timestamp.from(ldtToInstant);
                LocalDateTime startLDT = instantToTimestamp.toLocalDateTime();
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime now15 = now.plusMinutes(15);
                if(startLDT.isBefore(now15) && startLDT.isAfter(now)){
                    
                    String customer = CustomerData.getCustomerName(customerID);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Upcoming Appointment");
                    alert.setHeaderText("Appointment Within 15 Minutes");
                    alert.setContentText("You have a " + type + " appointment with " + customer + " at " + start);
                    alert.showAndWait();
                }
                
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        //System.out.println(a);
    }
    public static boolean checkTime() {
        boolean checkTime = false;
        int userID = getCurrentUserID();
        try {
            Statement stmt = getConnection().createStatement();
            String sqlStatement = "SELECT start FROM appointment WHERE userId= " + userID;
            ResultSet result = stmt.executeQuery(sqlStatement);
            while(result.next()) {

                start = result.getString("start"); 

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                LocalDateTime startDT = LocalDateTime.parse(start, formatter);
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime now15 = now.plusMinutes(15);
                if(startDT.isBefore(now15) && startDT.isAfter(now)){
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
    
    
    
}
      
