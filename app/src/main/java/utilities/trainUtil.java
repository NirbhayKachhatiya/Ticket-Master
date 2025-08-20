package utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import entities.Train;
import entities.User;
import services.userService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static services.trainService.FILE_PATH;
import static services.trainService.mapper;

public class trainUtil {

    public static List<Train> readTrainJson(){

        File file = new File(FILE_PATH);
        try {
            if (!file.exists() || file.length() == 0) {
                return new ArrayList<>();
            }
            return mapper.readValue(file, new TypeReference<List<Train>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // fallback if error
        }
    }
    public static void writeTrainJson(List<Train> trains) {
        File file = new File(FILE_PATH);
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, trains);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
