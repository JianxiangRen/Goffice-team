package backend.model;

public class User {
    private String userID;
    private String password;
    private String firstName;
    private String lastName;
    private String emailAddress;

    // Constructor
    public User(String userID, String password, String firstName, String lastName, String emailAddress) {
        this.userID = userID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    // Getters and Setters
    public String getUserID() { return userID; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
}
