package dataUSA.api;

import dataUSA.dataObjects.ConfigMan;
import okhttp3.*;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

public class ExecuteRestAPI {
    private static final String ERROR_MESSAGE = "An error occurred while executing %s due to:";
    /**
     * Builds the OkHttp client and request.
     * Returns the OkHttp response.
     */
    public Response webServiceJavaOKHttp() {
        try {
            //Building the OkHttp client
            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(ConfigMan.getWaitTimeOut(), TimeUnit.SECONDS)
                    .writeTimeout(ConfigMan.getWaitTimeOut(), TimeUnit.SECONDS)
                    .readTimeout(ConfigMan.getWaitTimeOut(), TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true).proxy(Proxy.NO_PROXY)
                    .build();
            //Building the OkHttp request
            Request request = new Request.Builder()
                    .url(ConfigMan.getUrl())
                    .method(ConfigMan.getRequestMethod(), null)
                    .build();

            System.out.println("Request url: " + request.url());
            return client.newCall(request).execute();
        } catch (Exception e) {
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String errorMsg = String.format(ERROR_MESSAGE, methodName) + e.getMessage();
            System.out.println(errorMsg);
            return null;
        }
    }
}
