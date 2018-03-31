package Main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Algorithm.Api_Client;

import Algorithm.SentenceScoreCalculator;
import Database.PostgreSQLJDBC;
import Models.Sentence;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import sun.jvm.hotspot.runtime.posix.POSIXSignals;

/**
 * Created by SasankaKudagoda on 01/19/18.
 */
public class MainActivity {
    public static void main(String[] args) throws IOException, InterruptedException {

        final String NEWS_API_KEY = "a31a93a9948a4b93a326671db56b4785";

        Api_Client api_client = new Api_Client();
        String sources = "abc-news,bbc-sport,cbc-news";
        String keyword = "dengue";
        String type = "top-headlines"; // or --> everything
        String pageSize = "1";
        String category = "business";
        String endpoint = "https://newsapi.org/v2/" + type + "?country=us&category=" + category + "&" + "apiKey=a31a93a9948a4b93a326671db56b4785";
//        uncomment this to fetch news
//        StringBuilder response = api_client.httpClient(endpoint, NEWS_API_KEY);
//        ArrayList urls = api_client.getJsonContent(response);
//        ArrayList<StringBuilder> descriptions = api_client.getHTMLContent(urls);

        ArrayList<StringBuilder> descriptions = new ArrayList<>();
        StringBuilder des = new StringBuilder("Investigators have searched the Seattle home of a former U.S. Olympic Team swimming coach amid allegations that he sexually abused and took explicit photos of an Olympic swimmer when she was underage.\n" +
                "Homeland Security taskforce investigators along with police in Washington state served a search warrant at 46-year-old Sean Hutchison's Seattle apartment Tuesday, recovering electronic devices they say may contain evidence, the SeattlePI reported . Hutchison is alleged to have taken nude photos of Ariana Kukors when she was 17.\n" +
                "Homeland Security launched an investigation on Jan. 30 following a report from Kukors, according to the court documents.\n" +
                "Kukors, now 28, said in a statement Wednesday that she went to police to report that Hutchison sexually assaulted her on trips and while training at Seattle area pools. She told investigators that Hutchison used his position as her longtime coach to \"groom her\" for sexual abuse.\n" +
                "Kukors said the grooming started at age 13 when he became her coach at King Aquatics, a Seattle-area swim club. She claims Hutchison started sexually abusing her when she was 16.\n" +
                "Kukors, the 2009 world champion in the 200-meter individual medley who placed fifth in that event in the 2012 Olympics, said she came forward to empower other victims.\n" +
                "\"I never thought I would share my story because, in so many ways, just surviving was enough,\" Kukors said. \"But in time, I've realized that stories like my own are too important to go unwritten. Not for the sake of you knowing my story, but for the little girls and boys whose lives and future hangs in the grasp of a horribly powerful and manipulative person. That they may not have to go through the same pain, trauma, horror, and abuse. That their parents, mentors, and guardians are better able to spot the signs of grooming and realize its tragic consequences before it's too late.\"\n" +
                "Hutchison, who was an assistant coach on the 2008 U.S. Olympic team, didn't immediately respond to an email seeking comment Wednesday.\n" +
                "In a search warrant affidavit, a Homeland Security Investigations special agent said investigators responded to claims that Hutchison took sexually explicit photos of Kukors more than a decade ago. Hutchison was a U.S. Olympic swimming coach in California at the time, a position from which he resigned in 2010 amid speculation that he was sexually involved with a swimmer.\n" +
                "Hutchison denied it at the time, saying \"there is no truth to that,\" and insisting his departure was a long-planned move to form his own pro team.\n" +
                "Hutchison is currently listed as the CEO of King Aquatics.");
//        StringBuilder des2 = new StringBuilder("Following months of investigations by the U.S. Trade Representative Robert Lighthizer, the Trump administration announced today at a White House briefing that the administration intends to place about $60 billion of tariffs on Chinese goods, with the bulk of them likely to be focused on the high-tech industry. The White House will announce a final list of goods subject to the tariffs in the next few weeks.\n" +
//                "\n" +
//                "“We’ve lost over a fairly short period of time, 60,000 factories in our country. Closed, shuttered, gone. Six million jobs at least, gone. And now they are starting to come back,” President Trump said during the briefing. “The word that I want to use is reciprocal – when they charge 25 percent for a car to go in, and we charge 2 percent for their car to come into the United States, that’s not good. That’s how China rebuilt itself.”\n" +
//                "\nVice President Mike Pence was even more blunt, saying that “the era of economic surrender is over.”\n" +
//                "\n" +
//                "The final size of the tariffs was higher than numbers circulated this morning by The New York Times and Bloomberg, which had indicated about $50 billion in tariffs. Previously circulated numbers ranged from a low of $30 billion to a high of $100 billion, so the number that the White House seems to have settled on is in the middle of the hypothetical range.\n" +
//                "\n" +
//                "The United States is a major importer of goods from China, hitting an all-time high trade deficit of $375.2 billion in 2017. Tariffs on electronics and other high-tech goods portends both potentially higher prices for consumers as well as assemblers, and would also likely encourage at least some Silicon Valley tech companies to move their manufacturing and assembly work out of China to other countries and possibly even on-shored back to the U.S.Tech industry associations have been widely opposed to tariffs, seeing them as a blunt instrument. That said, those same associations have also encouraged the administration to continue to look into unfair trade practices.\n" +
//                "\n" +
//                "Information Technology Industry Council president and CEO Dean Garfield said in a statement that “We appreciate that the Trump Administration has listened to industry’s requests for a comment period. While we look forward to providing our feedback on the options the administration has outlined, we remain concerned with the administration’s focus on tariffs. These measures could violate international obligations and – more importantly – would punish U.S. consumers, businesses, and workers for China’s action.”\n" +
//                "\n" +
//                "In addition to the tariffs, the White House announced that the Treasury Department will put in place restrictions on Chinese investment in tech companies based in the United States. There are not comprehensive details at time of publishing on exactly what those restrictions are, but we will report them when we have more information.\n" +
//                "\n" +
//                "Visa restrictions, which had been floated as another tactic to fight China, were not included in the announcements so far, but more action is possible by the White House.");


//        descriptions.add(0, des);
        descriptions.add(0, des);
        for (int i = 0; i < descriptions.size(); i++) {
            SentenceScoreCalculator sentenceScoreCalculator = new SentenceScoreCalculator(descriptions.get(i));
            List<Sentence> scoredSenetences = sentenceScoreCalculator.getScoredSenetences();
            int incrementer = 0;
            StringBuilder selectedSenetences = new StringBuilder();

            for (Sentence sentence : scoredSenetences) {
                if (incrementer == 20) {
                    break;
                } else {
                    selectedSenetences.append(sentence.getSentenceValue() + "\n");
                    System.out.println(incrementer + " : " + sentence.getSentenceValue());
//                    System.out.println(sentence.getSentenceValue());

                    incrementer++;
                }

            }
            System.out.println("********************************");
            System.out.print(selectedSenetences);
//            PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
//            postgreSQLJDBC.createConnection();
//            postgreSQLJDBC.createTable();
//            postgreSQLJDBC.saveNews(selectedSenetences);
//            postgreSQLJDBC.retreiveNews(1);
//            System.out.println("----------------------------------------");

            URL url = new URL("http://www.google.com/search?q=mkyong");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            System.out.println("HHTP ");
            System.out.println(con);
        }
    }
}
