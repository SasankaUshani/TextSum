package Extractor;

import Preprocessor.JunkWordsRemover;
import Preprocessor.StopWordsRemover;

import java.io.IOException;
import java.util.ArrayList;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SasankaKudagoda on 3/20/18.
 */
public class SentenceExtractor {
    StringBuilder description;
    List<String[]> allWordsInDescription = new ArrayList<>(); //words in the document
    List<String> uniqueWords = new ArrayList<>(); // unique words in description
    List<String[]> allSentences = new ArrayList<>();
    List<String> allSentencesAsList = new ArrayList<>();
    List<String> stopWords = null;
    List<String> junkText = null;

    public void splitSentence(StringBuilder descriptions) throws IOException, InterruptedException {
        junkText = new JunkWordsRemover().getJunkWords();
        stopWords = new StopWordsRemover().getStopWords();

        int documentCount = 0;
//        for (int i = 0; i < descriptions.size(); i++) {
        description = (StringBuilder) descriptions;

        if (description != null) {
            Pattern re = Pattern.compile("[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)",
                    Pattern.MULTILINE | Pattern.COMMENTS);

            Matcher reMatcher = re.matcher(description);
            //  reMatcher = re2.matcher(description);

            while (reMatcher.find()) {

                String[] tokenizedTerms = reMatcher.group().
                        replaceAll("[\\W&&[^\\s]]", "").split("\\W+");//to get individual terms
                String sentence = reMatcher.group().toString();
              //  for (String junkSentence:junkText) {
//                    if (junkSentence.contains(sentence.toLowerCase())) {
                   //     System.out.print("*****************Conatin ");
                     //   System.out.println(junkSentence);
//                    }
             //   }
//                System.out.print("rematcher ");
//                System.out.println(reMatcher.group().toString());

                allSentences.add(tokenizedTerms);
                allSentencesAsList.add(sentence);

                for (String term : tokenizedTerms) {
//                        avoid duplicate entry & stop words
                    if (!uniqueWords.contains(term) && !stopWords.contains(term.toLowerCase())) {
                        uniqueWords.add(term);


                    }
                }
                allWordsInDescription.add(tokenizedTerms);


            }

        } else {
            System.out.println("Description is null");
        }
        documentCount++;
    }
//    }

    public List<String[]> getAllWordsInDescription() {
        return this.allWordsInDescription;
    }

    public List<String> getUniqueWords() {
        return this.uniqueWords;
    }

    public List<String[]> getAllSentences() {
        return this.allSentences;
    }

    public List<String> getAllSentencesAsList() {
        return this.allSentencesAsList;
    }
}
