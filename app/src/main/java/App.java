import com.fasterxml.jackson.databind.MappingIterator;

import java.util.Scanner;

import static services.userService.*;

public class App {
    public static void appInterface(){
        System.out.println("1.Sign Up");
        System.out.println("2.Log In");
        System.out.println("3.Log Out");
        System.out.println("4.Exit Application");
        System.out.println("Enter number");
    }
    public static void main(String[] args) {
        appInterface();
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        while(id!=4){
            if(id>4 || id<1){
                System.out.println("Please enter a number between 1 and 4");
                appInterface();
                id=sc.nextInt();
            }
            else{
                id = switch (id) {
                    case 1 -> {
                        signUp();
                        appInterface();
                        yield sc.nextInt();
                    }
                    case 2 -> {
                        logIn();
                        appInterface();
                        yield sc.nextInt();
                    }
                    case 3 -> {
                        logOut();
                        appInterface();
                        yield sc.nextInt();
                    }
                    default ->  id;
                };
            }
        }
        System.out.println("Thank You for using our application .");
    }
}
