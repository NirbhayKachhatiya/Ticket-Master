import java.util.Scanner;

import static services.userService.signUp;

public class App {
    public static void main(String[] args) {
        System.out.println("1.Sign Up");
        System.out.println("2.Exit Application");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number");
        int id = sc.nextInt();
        switch(id){
            case 1 :
                signUp();
                main(args);
                break;

            case 2 :
                System.out.println("Thank you for using our application !!");

        }
    }
}
