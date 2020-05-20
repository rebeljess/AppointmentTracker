/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jesslambertappointments;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ResourceBundle;
import utilities.DBConnection;


/**
 *
 * @author jess
 */
public class JessLambertAppointments extends Application {
    
    static Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) throws IOException{
    ResourceBundle rb = ResourceBundle.getBundle("jesslambertappointments.resources/login");
        // load login fxml file and display it in the stage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view_controller/LoginScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle(rb.getString("programTitle"));
        scene.getStylesheets().add("jesslambertappointments/styles.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
        
        //Locale.setDefault(new Locale("es", "ES"));
        Locale myLocale = Locale.getDefault();
        
        // Make Database Connection
        DBConnection.makeConnection();


        launch(args);
        DBConnection.closeConnection();
    }
    
}
