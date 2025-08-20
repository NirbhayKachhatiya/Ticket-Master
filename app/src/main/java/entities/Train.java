package entities;

import java.util.List;

public class Train {

    // Attributes
    private int trainNo;
    private List<String> stations;
    private String startDate;
    private String endDate;

    // Constructor
    public Train(int trainNo, List<String> stations, String startDate, String endDate) {
        this.trainNo = trainNo;
        this.stations = stations;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public int getTrainNo() { return trainNo; }
    public void setTrainNo(int trainNo) { this.trainNo = trainNo; }

    public List<String> getStations() { return stations; }
    public void setStations(List<String> stations) { this.stations = stations; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

}
