package trashcollector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/trash_db"; // Replace with your database URL
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = ""; // Replace with your MySQL password

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    // Method to get total collected weight of all members
    public static double getTotalCollectedWeight() {
        double totalWeight = 0;
        String query = "SELECT SUM(plastic + metal + paper + glass + organic + ewaste) AS total FROM trash_collections";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                totalWeight = rs.getDouble("total");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching total collected weight: " + e.getMessage());
        }
        return totalWeight;
    }
}
