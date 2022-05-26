package dataUSA.util;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class DataUtils {

    private DataUtils() {
        throw new UnsupportedOperationException();
    }
    /**
     * Reads a local json file and returns a jsonObject
     */
    public static JSONObject readJsonFile(String jsonFile) {
        File f1 = new File(jsonFile);
        try (FileReader fr = new FileReader(f1.getAbsolutePath())) {
            JSONTokener tokener = new JSONTokener(fr);
            return new JSONObject(tokener);

        } catch (IOException ex) {
            System.out.println("There was an error trying to read the json file - " + ex.getMessage());
            return null;
        }
    }
}
