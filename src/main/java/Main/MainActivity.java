package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Other.Api_Client;
import Other.SentenceExtractor;
import Other.SentenceScoreCalculator;
import Other.TFIDFCalculator;


public class MainActivity {
    public static void main(String[] args) throws IOException, InterruptedException {

        final String NEWS_API_KEY = "a31a93a9948a4b93a326671db56b4785";

        Api_Client api_client = new Api_Client();
        String sources = "abc-news,bbc-sport,cbc-news";
        String keyword = "dengue";
        String type = "top-headlines"; // or --> everything
        String pageSize = "1";
        String category = "sports";
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

        StringBuilder des2 = new StringBuilder(" Following the Cavaliers' 140-138 overtime victory against the Minnesota Timberwolves on Wednesday, Cleveland coach Tyronn Lue vowed to find a more consistent role for swingman Cedi Osman after the rookie made big contributions off the bench.\n" +
                "\"I've got to keep playing him,\" Lue said. \"I mean, he brings a lot. He can guard 1s, 2s and 3s. He knows the coverages, he's in the right spot defensively all the time, and offensively he can make plays. He can run the floor and can catch and go. For a rookie, he really understands how to play the game, and we have to find minutes for him.\"\n" +
                "Osman played 21 minutes against the Wolves and finished with nine points on 3-of-5 shooting, four rebounds and a steal. His plus-minus rating of plus-7 was tied for the team lead.\n" +
                "\"Coach has been talking all the time to me, and I'm always ready,\" Osman said. \"Seriously, today when I get in the game, I was so thirsty -- I was just going out and bringing energy to the team. I'm so happy I was a part of the win tonight.\"\n" +
                "Osman had played just 17 minutes total in the Cavs' previous 10 games coming into Wednesday.\n" +
                "LeBron James had a huge block and the winning buzzer-beater as the Cavs topped the Timberwolves for a much-needed victory.\n" +
                "The lineup combination that put Osman, LeBron James and Jeff Green on the floor at the same time was particularly effective.\n" +
                "\"Guys are going to defend, get out in transition,\" Green said when asked about the potential of that group. \"The versatility of the three of us, being able to guard every position, it was big for us, especially down the stretch.\"\n" +
                "Green said that the Cavs -- who came into the night losers of 13 of their previous 19 games since Christmas Day -- spoke before the game about focusing on the joy of the game of basketball once again to lift them through their lean times.\n" +
                "James said Osman embodies the spirit the team is searching for.\n" +
                "\"Energy. That's what we need,\" James said. \"We're a team that has to have a lot of energy on the floor. Myself and Jeff and Cedi, we do that. We bring a lot of energy to the game. [Osman] doesn't know. He's out there just playing for the love of the game. He's going to make mistakes, which is OK, but his purity of the game is infectious to our team.\n" +
                "\"He hasn't been in many situations, so he's not even going to trip about what's going on. He's going to play his game. And he's been in professional basketball since he was 14 years old, so he plays the game the right way. It's great to have him.\"\n" +
                "Osman, 22, who was born in Macedonia, has played professionally in Turkey and as a member of the junior and senior Turkish national basketball teams.\n" +
                "According to NBA.com/Stats, James, Osman and Green have a net rating of plus-10.2 in 76 minutes played together this season.\n" +
                "Of course, finding minutes for Osman means sitting other players. He is competing with established veterans such as Derrick Rose, Kyle Korver, Iman Shumpert and Dwyane Wade for bench minutes.\n" +
                "Wade, 36, was held out of the lineup for rest Wednesday, allowing him a day off on the second night of a back-to-back. Meanwhile, Shumpert was a late scratch with plantar fasciitis, Rose played 8 minutes and Korver played 19 minutes.\n" +
                "And late in the game, Lue subbed Osman on and off the court with Isaiah Thomas, using Thomas on offensive possessions and Osman on defensive ones.\n" +
                "\"I mean, he gives us something we don't have,\" Thomas said. \"We don't -- what do they say, we're the oldest team in the league? We don't play with that much energy. So to have something, somebody like that, that rubs off on everybody. Even with the times we do practice, he's the most energized guy, so he deserves it.\n" +
                "\"I'm glad he got his time today, and he helped us out. I know he can help in the future, so we'll see what coach does.\"\n" +
                "We use cookies to offer an improved online experience. By clicking \"OK\" without changing your settings you are giving your consent to receive cookies.");
        descriptions.add(0, des);
        descriptions.add(1, des2);
//        System.out.println(descriptions.get());



        SentenceExtractor sentenceExtractor = new SentenceExtractor();
        sentenceExtractor.splitSentence(descriptions);
        List<String[]> allSentences = sentenceExtractor.getAllSentences();


        TFIDFCalculator tfidfCalculator = new TFIDFCalculator();
        tfidfCalculator.calculateTF_IDF_valueOfAllDocuments(allSentences);
//        tfidfCalculator.getCosineSimilarity();

        SentenceScoreCalculator sentenceScoreCalculator = new SentenceScoreCalculator(descriptions);
        sentenceScoreCalculator.getScoredSenetences();

    }
}
