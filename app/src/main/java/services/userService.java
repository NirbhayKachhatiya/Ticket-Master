package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import entities.User;
import static utilities.userUtil.readJson;
import static utilities.userUtil.writeJson;

public class userService {

    public static final String FILE_PATH = "app/src/main/java/localdb/users.json";
    public static final ObjectMapper mapper = new ObjectMapper();
    public static List<User> users = readJson();

    public static void signUp(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = sc.nextLine();
        System.out.println("Enter password:");
        String password = sc.nextLine();
        String userId = UUID.randomUUID().toString();
        String hashedPassword = "";
        User newUser = new User(username,password,userId,hashedPassword);
        users.add(newUser); // add new user
        writeJson(users);
    }


}
