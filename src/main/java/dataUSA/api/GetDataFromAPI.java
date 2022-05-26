package dataUSA.api;

import okhttp3.Response;
import org.json.JSONObject;
import static org.junit.Assert.assertEquals;

public final class GetDataFromAPI {

    private static final String ERROR_MESSAGE = "An error occurred while executing %s due to:";

    private GetDataFromAPI() {
        throw new UnsupportedOperationException();
    }
    private static final ExecuteRestAPI executeRestAPI = new ExecuteRestAPI();
    /**
     * Sending the OkHttp request and validating that the response is OK.
     * Returns the API response as a jsonObject.
     */
    public static JSONObject getJSONObjectFromAPI() {
        JSONObject jsonObject = null;
        try {
            Response response = executeRestAPI.webServiceJavaOKHttp();
            assert response.body() != null;

            assertEquals("200", String.valueOf(response.code()));

            jsonObject = new JSONObject(response.body().string());

        } catch (Exception e) {
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String errorMsg = String.format(ERROR_MESSAGE, methodName) + e.getMessage();
            System.out.println(errorMsg);
        }
        return jsonObject;
    }

}