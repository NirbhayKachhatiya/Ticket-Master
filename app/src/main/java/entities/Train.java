package entities;

import java.util.List;

public class Train {

    // Attributes
    private int trainNo;
    private String trainName;
    private List<String> stations;
    private String startDate;
    private String endDate;

    // Default constructor (needed by Jackson)
    public Train(){}

    // Constructor
    public Train(int trainNo, String trainName,List<String> stations, String startDate, String endDate) {
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.stations = stations;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public int getTrainNo() { return trainNo; }
    public void setTrainNo(int trainNo) { this.trainNo = trainNo; }

    public String getTrainName() { return trainName; }
    public void setTrainName(String trainName) { this.trainName = trainName; }

    public List<String> getStations() { return stations; }
    public void setStations(List<String> stations) { this.stations = stations; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

}
