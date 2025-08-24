package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Ticket;
import entities.Train;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static services.userService.addTicketToUser;
import static services.userService.deleteTicketFromUser;
import static utilities.trainUtil.*;
import static utilities.userUtil.*;

public class trainService {

    public static final String FILE_PATH = "app/src/main/java/localdb/trains.json";
    public static final ObjectMapper mapper = new ObjectMapper();
    public static List<Train> trains = readTrainJson();
    public static Scanner sc = new Scanner(System.in);

    public static void addTrain(){
        if (!isAdmin()){
            System.out.println("Current user is not an admin . Please login using admin credentials to add new train");
            return;
        }
        System.out.println("Adding train...");
        System.out.println("Enter Train Name :");
        String trainName = sc.nextLine();
        System.out.println("Enter Train Number :");
        int trainNo = validateInt();
        for(Train train : trains){
            if(train.getTrainNo()==trainNo){
                System.out.println("Train already exists. Please try again");
                return;
            }
        }
        System.out.println("How many stations does the train visit");
        List<String> stationList = new ArrayList<>();
        int stations = validateInt();
        for (int i = 1; i <= stations; ++i) {
            System.out.printf("Enter station name for station %d :",i);
            String station = sc.nextLine();
            stationList.add(station);
        }
        System.out.println("Enter starting date : (DD/MM/YYYY) ");
        String trainStartDate = validDateFormat();
        System.out.println("Enter ending date: (DD/MM/YYYY) ");
        String trainEndDate = validDateFormat();
        Train newTrain = new Train(trainNo,trainName,stationList,trainStartDate,trainEndDate);
        trains.add(newTrain);
        writeTrainJson(trains);
        System.out.println("New Train Added");
    }

    public static void showStations(){
        System.out.println("Showing all stations...");
        Map<String, Integer> seen = new HashMap<>();
        int i=1;
        for (Train train : trains){
            for(String station : train.getStations()){
                if(seen.get(station)==null){
                    System.out.println(i+". "+station);
                    seen.put(station,1);
                    i++;
                }
            }
        }
    }
    public static void deleteTrain(){
        if (!isAdmin()){
            System.out.println("Current user is not an admin . Please login using admin credentials to delete a train");
            return;
        }
        System.out.println("Enter Train number to delete :");
        int id=validateInt();
        sc.nextLine();
        for(Train train:trains){
            if(train.getTrainNo()==id){
                trains.remove(train);
                writeTrainJson(trains);
                System.out.println("Train Deleted");
                return;
            }
        }
        System.out.println("Train number not found");
    }

    public static void showTrains(){
        int count = 0;
        System.out.println("Enter source station :");
        String src=sc.nextLine();
        System.out.println("Enter destination station :");
        String dest=sc.nextLine();
        for (Train train : trains){
            int srcInd=train.getStations().indexOf(src);
            int dstInd=train.getStations().indexOf(dest);
            if(srcInd!=-1 && srcInd<dstInd){
                if (count == 0){
                    System.out.println("Following train(s) found (Train Number , Train Name) :");
                }
                System.out.println(train.getTrainNo()+" , "+train.getTrainName());
                count++;
            }
        }
        if(count == 0){
            System.out.println("No trains found");
        }
    }

    public static void showTrainDetail(){
        System.out.println("Enter Train Number :");
        int id=validateInt();
        for(Train train : trains){
            if(train.getTrainNo()==id){
                System.out.println("Train Number : "+train.getTrainNo());
                System.out.println("Train Name : "+train.getTrainName());
                System.out.println("Stations : "+train.getStations());
                System.out.println("Start Date : "+train.getStartDate());
                System.out.println("End Date : "+train.getEndDate());
                return;
            }
        }
        System.out.println("No trains found");
    }

    public static void bookTicket(){
        if(!isUserLoggedIn()){
            System.out.println("Please log in before booking a train.\n");
            return;
        }
        System.out.println("Enter train number:");
        int trainNo=validateInt();
        Train currentTrain = new Train();
        for(Train train : trains){
            if(train.getTrainNo()==trainNo){
                currentTrain = train;
            }
        }
        if(currentTrain.getTrainName()==null){
            System.out.println("No such train exists. Please try again");
            return;
        }
        System.out.println(currentTrain.getTrainName());
        int ind=1;
        for(String station : currentTrain.getStations()){
            System.out.println(ind+"."+station);
            ind++;
        }
        ind--;
        System.out.println("Enter source station (number):");
        int srcInd=sc.nextInt();
        System.out.println("Enter destination station (number):");
        int destInd=sc.nextInt();
        if(srcInd>ind || destInd>ind){
            System.out.println("Invalid source or destination station number .");
            return;
        }
        if(srcInd>=destInd){
            System.out.println("Source station should be before the destination station.");
            return;
        }
        String ticketId=UUID.randomUUID().toString();
        Ticket ticket = new Ticket(ticketId,curUser.getUserId(),currentTrain.getTrainNo(),currentTrain.getStations().get(srcInd-1),currentTrain.getStations().get(destInd-1));
        addTicketToUser(ticket);
        System.out.println("Ticket Added successfully");
    }

    public static void deleteTicket(){
        if(!isUserLoggedIn()){
            System.out.println("Please log in to delete your ticket.\n");
            return;
        }
        int i=1;
        System.out.println("Current tickets:");
        for (Ticket ticket:curUser.getTickets()){
            System.out.println(i+". Ticket id: "+ticket.getTicketId()+" Train Number: "+ticket.getTrainNo()+" , Source: "+ticket.getSource()+" , Destination: "+ticket.getDestination());
            i++;
        }
        if(i==1){
            System.out.println("No tickets found");
            return;
        }
        System.out.println("Enter ticket number to delete:");
        int index=validateInt();
        if(index<1 || index>=i){
            System.out.println("Invalid ticket index.");
            return;
        }
        deleteTicketFromUser(curUser.getTickets().get(index-1));
        System.out.println("Ticket deleted successfully");
    }

    public static int validateInt() {
        while (true) {
            String input = sc.nextLine();
            try {
                int num=Integer.parseInt(input);
                return num;
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
            }
        }
    }

    public static String validDateFormat() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String dateString = sc.nextLine();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate.parse(dateString, formatter);
                return dateString;
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date. Please enter a valid date.");
            }
        }

    }
}
