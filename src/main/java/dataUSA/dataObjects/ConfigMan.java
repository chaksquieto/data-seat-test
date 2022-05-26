package dataUSA.dataObjects;

public class ConfigMan {
    private static String url;
    private static String requestMethod;
    private static int waitTimeOut;
    private static int population;
    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        ConfigMan.url = url;
    }

    public static String getRequestMethod() {
        return requestMethod;
    }

    public static void setRequestMethod(String requestMethod) {
        ConfigMan.requestMethod = requestMethod;
    }

    public static int getWaitTimeOut() {
        return waitTimeOut;
    }

    public static void setWaitTimeOut(int waitTimeOut) {
        ConfigMan.waitTimeOut = waitTimeOut;
    }

    public static int getPopulation() {
        return population;
    }

    public static void setPopulation(int population) {
        ConfigMan.population = population;
    }
}
