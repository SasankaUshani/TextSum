import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Base64;

public class Api_Client {
    final static String DIFFBOT_API_KEY = "0af72f25c0487d4ed09ccf06544397ca";
    final static String NEWS_API_KEY = "a31a93a9948a4b93a326671db56b4785";
    final static String SYNONYM_API_KEY = "777f01ece8859262a3ffe3413206df51";

    public static void main(String[] args) throws Exception {
        String endpoint = "https://newsapi.org/v2/top-headlines?sources=bbc-news&" + "apiKey=a31a93a9948a4b93a326671db56b4785";

        httpClient(endpoint, NEWS_API_KEY);
        // System.out.println("get html: " + getHTMLContent("http://www.bbc.co.uk/news/world-asia-42699600"));
        synonym("swim");

    }

    public static StringBuilder getHTMLContent(String url) throws IOException {
        String encodedUrl = URLEncoder.encode(url, "UTF-8");
        StringBuilder builder = httpClient("https://api.diffbot.com/v3/analyze?token=" + DIFFBOT_API_KEY + "" +
                "&url=" + encodedUrl, null);
        JsonParser jsonParser = new JsonParser();
        JsonObject responseObj = (JsonObject) jsonParser.parse(builder.toString());
        return new StringBuilder(responseObj.get("objects").
                getAsJsonArray().get(0).getAsJsonObject().get("text").getAsString());
    }


    private static StringBuilder httpClient(String endpoint, String authenticationKey) throws IOException {
        URL url = new URL(endpoint);
        URLConnection connection = url.openConnection();
        if (authenticationKey != null) {
            String accountKeyEnc = Base64.getEncoder().encodeToString((authenticationKey + ":"
                    + authenticationKey).getBytes());
            connection.setRequestProperty("Authorization", "Basic " + accountKeyEnc);
        }
        String line;
        StringBuilder response = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                System.out.println("response : " + line);
                response.append(line);

            }
        } catch (FileNotFoundException e) {
            System.out.println("Synonym Not found");
        }
        return response;
    }


    private static void synonym(String word) throws IOException {
        StringBuilder builder =
                httpClient("http://words.bighugelabs.com/api/2/" + SYNONYM_API_KEY + "/" + word + "/json", null);
        JsonParser jsonParser = new JsonParser();
        try {
            JsonObject responseObj = (JsonObject) jsonParser.parse(builder.toString());


            JsonObject verbObj = responseObj.get("verb").getAsJsonObject();
            JsonArray synArray = verbObj.get("syn").getAsJsonArray();

            for (int i = 0; i < synArray.size(); i++) {
                System.out.println("synonym " + i + " :" + synArray.get(i));

            }
            {

            }
        } catch (NullPointerException e) {
        }
    }
}