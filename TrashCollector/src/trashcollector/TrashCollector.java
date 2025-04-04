package trashcollector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TrashCollector {
     public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("Database connection established.");
        } else {
            System.out.println("Failed to connect to the database.");
        }
     }
}