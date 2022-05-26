package testModules;

import dataUSA.api.GetDataFromAPI;
import dataUSA.dataObjects.ConfigMan;
import dataUSA.util.DataUtils;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataUsaTests {
    private final ArrayList<String> years = new ArrayList<>(Arrays.asList("2013","2014","2015","2016","2017","2018","2019"));

    @Before
    public void setup() {
        JSONObject configJson = DataUtils.readJsonFile("src/main/resources/config.json");
        assert configJson != null;
        ConfigMan.setUrl(configJson.getString("url"));
        ConfigMan.setRequestMethod(configJson.getString("requestMethod"));
        ConfigMan.setWaitTimeOut(configJson.getInt("waitTimeOut"));
        System.out.println("Setup completed successfully.");
    }

    /**
     * Runs a general test for the population data available in each year returned by the API.
     */
    @Test
    public void getAllPopulationData() {
        //Set up the request URL for the API
        ConfigMan.setUrl(ConfigMan.getUrl() + "drilldowns=Nation&measures=Population");
        //Sending the API request and setting the response
        JSONObject response = GetDataFromAPI.getJSONObjectFromAPI();
        //Selecting a jsonObject from the response and doing test validation on it
        JSONArray dataArray = response.getJSONArray("data");
        for (int i = 0; i < dataArray.length(); i++) {
            validation(dataArray.getJSONObject(i));
        }
    }

    /**
     * Runs a general test for the population data available for a random year returned by the API.
     */
    @Test
    public void getRandomYearPopulationData() {
        //Selecting a random year from the list
        int index = (int) (Math.random() * years.size());
        //Set up the request URL for the API
        ConfigMan.setUrl(ConfigMan.getUrl() + String.format("drilldowns=Nation&measures=Population&year=%s", years.get(index)));
        //Sending the API request and setting the response
        JSONObject response = GetDataFromAPI.getJSONObjectFromAPI();
        //Test validations on the API response
        validation(response.getJSONArray("data").getJSONObject(0));
    }

    private void validation(JSONObject data) {
        int yearIdData = data.getInt("ID Year");
        String yearData = data.getString("Year");
        int populationData = data.getInt("Population");

        //region Test Parameters
        assertTrue(years.contains(String.valueOf(yearIdData)));
        assertTrue(years.contains(yearData));
        assertEquals("01000US", data.getString("ID Nation"));
        assertEquals("United States", data.getString("Nation"));
        assertEquals("united-states", data.getString("Slug Nation"));

        System.out.printf("The population in %s was %s.%n", yearData, populationData);

        //Validates JSON Schema
        JSONObject jsonSchema = DataUtils.readJsonFile("src/main/resources/schema.json");
        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(data);
        //endregion
    }
}
