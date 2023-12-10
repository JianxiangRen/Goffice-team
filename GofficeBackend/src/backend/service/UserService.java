package backend.service;

import java.sql.*;
import backend.model.User;

public class UserService {

    private static final String DATABASE_URL = "jdbc:sqlite:mydatabase.db";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    // CREATE
    public boolean addUser(User user) {
        String sql = "INSERT INTO users(userID, password, firstName, lastName, emailAddress) VALUES(?,?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserID());
            String hashedPassword = SecurityUtils.hashPassword(user.getPassword());
            pstmt.setString(2, hashedPassword);
            pstmt.setString(3, user.getFirstName());
            pstmt.setString(4, user.getLastName());
            pstmt.setString(5, user.getEmailAddress());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // READ
    public User getUser(String userID) {
        String sql = "SELECT * FROM users WHERE userID = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("userID"),
                        rs.getString("password"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("emailAddress")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // UPDATE
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET firstName = ?, lastName = ?, emailAddress = ? WHERE userID = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmailAddress());
            pstmt.setString(4, user.getUserID());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // DELETE
    public boolean deleteUser(String userID) {
        String sql = "DELETE FROM users WHERE userID = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userID);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // LOGIN
    public boolean loginUser(String userID, String plainPassword) {
        String sql = "SELECT password FROM users WHERE userID = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");
                return SecurityUtils.checkPassword(plainPassword, storedHashedPassword);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}


