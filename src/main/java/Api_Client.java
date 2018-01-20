import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;

public class Api_Client {
    final static String DIFFBOT_API_KEY = "0af72f25c0487d4ed09ccf06544397ca";
    final static String NEWS_API_KEY = "a31a93a9948a4b93a326671db56b4785";
    final static String SYNONYM_API_KEY = "777f01ece8859262a3ffe3413206df51";

    public static void main(String[] args) throws Exception {
        String sources = "abc-news,bbc-sport,cbc-news";
        String keyword = "dengue";
        String type = "top-headlines"; // or --> everything
        String pageSize = "10";
        String category = "sports";
        String endpoint = "https://newsapi.org/v2/" + type + "?country=us&category=" + category + "&" + "apiKey=a31a93a9948a4b93a326671db56b4785";

        ArrayList urls = getJsonContent(httpClient(endpoint, NEWS_API_KEY));
        getHTMLContent(urls);
        // System.out.println("get html: " + getHTMLContent("http://www.bbc.co.uk/news/world-asia-42699600"));
        //synonym("swim");

    }


    public static String getHTMLContent(ArrayList newsUrl) throws IOException {

        JsonObject responseObj = null;
        String encodedUrl;
        StringBuilder builder;
        Iterator url = newsUrl.iterator();
        while (url.hasNext()) {
            // System.out.println("Url from array " + url.next());
            encodedUrl = URLEncoder.encode((String) url.next(), "UTF-8");
            builder = httpClient("https://api.diffbot.com/v3/analyze?token=" + DIFFBOT_API_KEY + "" +
                    "&url=" + encodedUrl, null);
            JsonParser jsonParser = new JsonParser();
            responseObj = (JsonObject) jsonParser.parse(builder.toString());

            String description = responseObj.get("objects").getAsJsonArray().get(0).getAsJsonObject().get("text").getAsString();
            System.out.println("description : " + description);
        }
        return responseObj.get("objects").
                getAsJsonArray().get(0).getAsJsonObject().get("text").getAsString();


    }

    private static ArrayList<String> getJsonContent(StringBuilder stringBuilder) throws IOException {
        JsonParser jsonParser = new JsonParser();
        JsonObject responseObj = (JsonObject) jsonParser.parse(stringBuilder.toString());
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("News"));
        ArrayList<String> urlList = new ArrayList<>();
        for (int i = 0; i < responseObj.get("articles").getAsJsonArray().size(); i++) {

            String newsURL = responseObj.get("articles").getAsJsonArray().get(i).getAsJsonObject().get("url").getAsString();
            System.out.println("Json : " + newsURL);
            fileWriter.write(newsURL);
            fileWriter.write(" - ");
            fileWriter.newLine();
            urlList.add(newsURL);
        }
        fileWriter.close();

        return urlList;
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
            System.out.println("Not found");
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

        } catch (NullPointerException e) {
        }
    }
}