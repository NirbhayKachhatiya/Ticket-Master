package entities;

import java.util.Date;

public class Ticket {

    // Attributes
    private String ticketId;
    private String userId;
    private int trainNo;
    private String source;
    private String destination;

    public Ticket() {}

    public Ticket(String ticketId, String userId,int trainNo, String source, String destination) {
        this.ticketId = ticketId;
        this.trainNo=trainNo;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
    }

    // Getters and Setters
    public String getTicketId() { return ticketId; }
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public int getTrainNo() { return trainNo;}
    public void setTrainNo(int trainNo) { this.trainNo = trainNo; }
}
