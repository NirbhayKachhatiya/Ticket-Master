package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Train;

import java.util.*;

import static utilities.trainUtil.readTrainJson;
import static utilities.trainUtil.writeTrainJson;
import static utilities.userUtil.*;

public class trainService {

    public static final String FILE_PATH = "app/src/main/java/localdb/trains.json";
    public static final ObjectMapper mapper = new ObjectMapper();
    public static List<Train> trains = readTrainJson();

    public static void addTrain(){

        if (!isUserLoggedIn() || !curUser.getUsername().equals("admin")){
            System.out.println("Current user is not an admin . Please login using admin credentials to add new train \n");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Adding train...");
        System.out.println("Enter Train Name :");
        String trainName = sc.nextLine();
        int trainNo = trains.size()+1;
        System.out.println("How many stations does the train visit");
        List<String> stationList = new ArrayList<>();
        int stations = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= stations; ++i) {
            System.out.printf("Enter station name for station %d :\n%n",i);
            String station = sc.nextLine();
            stationList.add(station);
        }
        System.out.println("Enter starting date : (DD/MM/YYYY) ");
        String trainStartDate = sc.nextLine();
        System.out.println("Enter ending date: (DD/MM/YYYY) ");
        String trainEndDate = sc.nextLine();
        Train newTrain = new Train(trainNo,stationList,trainStartDate,trainEndDate);
        trains.add(newTrain);
        writeTrainJson(trains);
    }
}
