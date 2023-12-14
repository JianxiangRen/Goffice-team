package backend.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {

    private static final String DATABASE_URL = "jdbc:sqlite:mydatabase.db";

    public static void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL)) {
            if (conn != null) {
                createUsersTable(conn);
                System.out.println("Database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createUsersTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                     "userID TEXT PRIMARY KEY," +
                     "password TEXT NOT NULL," +
                     "firstName TEXT," +
                     "lastName TEXT," +
                     "emailAddress TEXT" +
                     ");";
        
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    public static void main(String[] args) {
        createNewDatabase();
    }
}
