import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        final  String NEWS_API_KEY = "a31a93a9948a4b93a326671db56b4785";

        Api_Client api_client = new Api_Client();
        String sources = "abc-news,bbc-sport,cbc-news";
        String keyword = "dengue";
        String type = "top-headlines"; // or --> everything
        String pageSize = "10";
        String category = "sports";
        String endpoint = "https://newsapi.org/v2/" + type + "?country=us&category=" +
                category + "&" + "apiKey=a31a93a9948a4b93a326671db56b4785";

        StringBuilder response = api_client.httpClient(endpoint, NEWS_API_KEY);
        ArrayList urls = api_client.getJsonContent(response);
        ArrayList descriptions = api_client.getHTMLContent(urls);


        Sentence sentence = new Sentence();
        sentence.splitSentence(descriptions);
    }
}
