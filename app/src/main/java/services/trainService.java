package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Train;

import java.util.*;

import static utilities.trainUtil.*;
import static utilities.userUtil.*;

public class trainService {

    public static final String FILE_PATH = "app/src/main/java/localdb/trains.json";
    public static final ObjectMapper mapper = new ObjectMapper();
    public static List<Train> trains = readTrainJson();

    public static void addTrain(){
        if (!isAdmin()){
            System.out.println("Current user is not an admin . Please login using admin credentials to add new train");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Adding train...");
        System.out.println("Enter Train Name :");
        String trainName = sc.nextLine();
        System.out.println("Enter Train Number :");
        int trainNo = sc.nextInt();
        sc.nextLine();
        for(Train train : trains){
            if(train.getTrainNo()==trainNo){
                System.out.println("Train already exists. Please try again");
                return;
            }
        }
        System.out.println("How many stations does the train visit");
        List<String> stationList = new ArrayList<>();
        int stations = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= stations; ++i) {
            System.out.printf("Enter station name for station %d :",i);
            String station = sc.nextLine();
            stationList.add(station);
        }
        System.out.println("Enter starting date : (DD/MM/YYYY) ");
        String trainStartDate = sc.nextLine();
        System.out.println("Enter ending date: (DD/MM/YYYY) ");
        String trainEndDate = sc.nextLine();
        Train newTrain = new Train(trainNo,trainName,stationList,trainStartDate,trainEndDate);
        trains.add(newTrain);
        writeTrainJson(trains);
        System.out.println("New Train Added");
    }

    public static void deleteTrain(){
        if (!isAdmin()){
            System.out.println("Current user is not an admin . Please login using admin credentials to delete a train");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Train number to delete :");
        int id=sc.nextInt();
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Station Name :");
        String stationName=sc.nextLine();
        for (Train train : trains){
            if(train.getStations().contains(stationName)){
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Train Number :");
        int id=sc.nextInt();
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
}
