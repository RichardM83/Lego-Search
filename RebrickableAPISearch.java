import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class RebrickableAPISearch {
    private static String apiKey;
    private static String partName;

    public static void main(String[] args) {
        setApiKey("be971570d8281bfad5f7dea36de1ccd3");

        printMinigures("0241187567-1");
    }

    public static void printMinigures(String set_num) {
        try {
            String apiUrl = "https://rebrickable.com/api/v3/lego/sets/" + set_num + "/minifigs/" + "?key=" + getApiKey();
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");


            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                if (connection.getResponseCode() == 200) {
                    String jsonResponse = response.toString();
                    System.out.println(jsonResponse);
                } else {
                    System.out.println("Error: " + connection.getResponseCode() + " - " + connection.getResponseMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public static void setApiKey(String key) {
        apiKey = key;
    }

    public static void setPartName(String name) {
        partName = name;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getPartName() {
        return partName;
    }
}
