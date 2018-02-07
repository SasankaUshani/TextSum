import java.io.IOException;
import java.util.ArrayList;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sentence {
    StringBuilder description;
    List<String[]> allWordsInDescription = new ArrayList<>(); //words in the document
    List<String> uniqueWords = new ArrayList<>(); // unique words in description
    List<String> allSentences = new ArrayList<>();
    List<String> stopWords = null;

    public void splitSentence(ArrayList descriptions) throws IOException, InterruptedException {

        int documentCount = 0;
        for (int i = 0; i < descriptions.size(); i++) {
            description = (StringBuilder) descriptions.get(i);

            if (description != null) {
                Pattern re = Pattern.compile("[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)",
                        Pattern.MULTILINE | Pattern.COMMENTS);
              // --> use this other way
                Pattern re2 = Pattern.compile("Follow Grant on Twitter and Facebook.");
                Matcher reMatcher = re.matcher(description);
              //  reMatcher = re2.matcher(description);
                stopWords = new StopWords().getStopWords();
                while (reMatcher.find()) {
                    allSentences.add(reMatcher.group());
                    System.out.println("Remathcer : " + reMatcher.group().toString());
                    String[] tokenizedTerms = reMatcher.group().
                            replaceAll("[\\W&&[^\\s]]", "").split("\\W+");//to get individual terms


                    for (String term : tokenizedTerms) {
                        //avoid duplicate entry & stop words
                        if (!uniqueWords.contains(term) && !stopWords.contains(term.toLowerCase())) {
                            uniqueWords.add(term);

                        }
                    }
                    allWordsInDescription.add(tokenizedTerms);
                }

            }else{
                System.out.println("Description is null");
            }
            documentCount++;
            System.out.println("Senetence size : " + allSentences.size() + " and document count is : " + documentCount);
        }
    }

    public List<String[]> getAllWordsInDescription() {
        return this.allWordsInDescription;
    }
    public List<String> getUniqueWords() {
        return this.uniqueWords;
    }
    public List<String> getAllSentences() {
        return this.allSentences;
    }
}
