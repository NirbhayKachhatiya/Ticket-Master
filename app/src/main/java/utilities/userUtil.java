package utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import entities.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static services.userService.FILE_PATH;
import static services.userService.mapper;

public class userUtil {
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


}
