package utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import entities.User;

import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import static services.userService.FILE_PATH;
import static services.userService.mapper;

public class userUtil {

    public static User curUser;

    public static List<User> readJson(){
        File file = new File(FILE_PATH);
        try {
            if (!file.exists() || file.length() == 0) {
                return new ArrayList<>();
            }
            return mapper.readValue(file, new TypeReference<List<User>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // fallback if error
        }
    }

    public static void writeJson(List<User> users) {
        File file = new File(FILE_PATH);
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());

            // Converting bytes to hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b); // converting unsigned byte (if any) to signed byte
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean isUserLoggedIn(){
        return curUser != null;
    }
}
