package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Algorithm.Api_Client;

import Algorithm.SentenceScoreCalculator;
import Models.Sentence;

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


        descriptions.add(0, des);
        SentenceScoreCalculator sentenceScoreCalculator = new SentenceScoreCalculator(descriptions);
        List<Sentence> scoredSenetences = sentenceScoreCalculator.getScoredSenetences();
        int incrementer = 0;
        StringBuilder selectedSenetences = new StringBuilder();

        for (Sentence sentence : scoredSenetences) {

            if (incrementer == 20) {
                break;
            } else {
                selectedSenetences.append(sentence.getSentenceValue()+"\n");
                System.out.println(incrementer + " : " + sentence.getSentenceValue());
                incrementer++;
            }

        }

    }
}
