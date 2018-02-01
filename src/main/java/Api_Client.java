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
    final static String SYNONYM_API_KEY = "777f01ece8859262a3ffe3413206df51";
    final static String DIFFBOT_API_KEY = "0af72f25c0487d4ed09ccf06544397ca";



    public static ArrayList<String> getHTMLContent(ArrayList newsUrl) throws IOException {
        ArrayList<String> descriptionList = new ArrayList<>();
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
            //add all descriptions relavant to that topic to an array list
            descriptionList.add(description);


        }
        return descriptionList;


    }

    public static ArrayList<String> getJsonContent(StringBuilder stringBuilder) throws IOException {
        JsonParser jsonParser = new JsonParser();
        JsonObject responseObj = (JsonObject) jsonParser.parse(stringBuilder.toString());
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("News"));
        ArrayList<String> urlList = new ArrayList<>();
        for (int i = 0; i < responseObj.get("articles").getAsJsonArray().size(); i++) {

            String newsURL = responseObj.get("articles").getAsJsonArray().get(i).getAsJsonObject().get("url").getAsString();
//            System.out.println("Json : " + newsURL);
            fileWriter.write(newsURL);
            fileWriter.write(" - ");
            fileWriter.newLine();
            urlList.add(newsURL);
        }
        fileWriter.close();

        return urlList;
    }

    public static StringBuilder httpClient(String endpoint, String authenticationKey) throws IOException {
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
                response.append(line);

            }
        } catch (FileNotFoundException e) {
            System.out.println("Not found");
        }

        return response;
    }


    public static void synonym(String word) throws IOException {
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