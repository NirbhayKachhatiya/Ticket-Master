package entities;

import java.util.ArrayList;
import java.util.List;

public class User {

    // Attributes of user
    private String username;
    private String password;
    private String userId;
    private String hashedPassword;
    private List<Ticket> tickets;

    // Default constructor (needed by Jackson)
    public User() {
        this.tickets = new ArrayList<>();
    }

    // Parameterized constructor
    public User(String username, String password, String userId, String hashedPassword) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.hashedPassword = hashedPassword;
        this.tickets = new ArrayList<>();
    }

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getHashedPassword() { return hashedPassword; }
    public void setHashedPassword(String hashedPassword) { this.hashedPassword = hashedPassword; }

    public List<Ticket> getTickets() { return tickets; }
    public void setTickets(List<Ticket> tickets) { this.tickets = tickets; }

    // Optional: nice toString for debugging
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userId=" + userId +
                ", tickets=" + tickets +
                '}';
    }
}
