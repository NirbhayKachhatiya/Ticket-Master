import java.util.Scanner;

import static services.trainService.*;
import static services.userService.*;

public class App {
    public static void appInterface(){
        System.out.println("1.Sign Up");
        System.out.println("2.Log In");
        System.out.println("3.Log Out");
        System.out.println("Admin functionalities :");
        System.out.println("4.Add new train");
        System.out.println("5.Delete train");
        System.out.println("Normal user functionalities :");
        System.out.println("6.Show stations");
        System.out.println("7.Search trains");
        System.out.println("8.Get train details");
        System.out.println("0.Book ticket");
        System.out.println("10.Delete ticket");
        System.out.println("11.Exit Application");
    }
    public static void main(String[] args) {
        appInterface();
        int id = validate();
        while(id!=11){
            id = switch (id) {
                case 1 -> {
                    signUp();
                    appInterface();
                    yield validate();
                }
                case 2 -> {
                    logIn();
                    appInterface();
                    yield validate();
                }
                case 3 -> {
                    logOut();
                    appInterface();
                    yield validate();
                }
                case 4 -> {
                    addTrain();
                    appInterface();
                    yield validate();
                }
                case 5 -> {
                    deleteTrain();
                    appInterface();
                    yield validate();
                }
                case 6 -> {
                    showStations();
                    appInterface();
                    yield validate();
                }
                case 7 -> {
                    showTrains();
                    appInterface();
                    yield validate();
                }
                case 8 -> {
                    showTrainDetail();
                    appInterface();
                    yield validate();
                }
                case 9 -> {
                    bookTicket();
                    appInterface();
                    yield validate();
                }
                case 10 -> {
                    deleteTicket();
                    appInterface();
                    yield validate();
                }
                default ->  id;
            };
        }
        System.out.println("Thank You for using our application .");
    }

    public static int validate() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Please enter an integer between 1 to 11: ");
            String input = sc.nextLine();
            try {
                int num = Integer.parseInt(input);
                if (num >= 1 && num <= 11) {
                    return num;
                } else {
                    System.out.println("Error: Number must be between 1 and 11.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a valid integer.");
            }
        }
    }

}
