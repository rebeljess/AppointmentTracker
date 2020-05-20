/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

/**
 *
 * @author jess
 */
public class ActivityLog {
    private static final String FILENAME = "log.txt";
    
    public ActivityLog() {}
    
    public static void log (String username, boolean success) {

        try (FileWriter fw = new FileWriter(FILENAME, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)){
            pw.println(ZonedDateTime.now() + " " + username + (success ? " Success" : " Failure"));
        
    } catch (IOException ex) {
            System.out.println("Logger Error: " + ex.getMessage());
        } 
    }
}

