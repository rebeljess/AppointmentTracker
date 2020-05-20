/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author jess
 */
public class DBConnection {
    private static final String databaseName = "U04Zvu";
    private static final String DB_URL = "jdbc:mysql://3.227.166.251/" + databaseName;
    private static final String DBusername = "U04Zvu";
    private static final String DBpassword = "53688388979";
    private static final String driver = "com.mysql.jdbc.Driver";
    static Connection conn;
    
    public static void makeConnection() throws ClassNotFoundException, SQLException, Exception {
        Class.forName(driver);
        conn = (Connection) DriverManager.getConnection(DB_URL, DBusername, DBpassword);
        System.out.println("Connection Successful");
    }
    
    public static void closeConnection() throws ClassNotFoundException, SQLException, Exception {
        conn.close();
        System.out.println("Connection Closed");
    }
    
    // Return Database Connection
    public static Connection getConnection() {
        return conn;
    }
}
