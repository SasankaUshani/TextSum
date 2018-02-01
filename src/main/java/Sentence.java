import java.io.IOException;
import java.util.ArrayList;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sentence {
    String description;
    List<String[]> allWordsInDocuments = new ArrayList<>(); //words in the document
    List<String> uniqueWords = new ArrayList<>(); // unique words in description
    List<String> allSentences = new ArrayList<>();
    List<String> stopWords = null;

    public void splitSentence(ArrayList descriptions) throws IOException, InterruptedException {

        int documentCount = 0;
        for (int i = 0; i < descriptions.size(); i++) {
            description = (String) descriptions.get(i);
            System.out.println(descriptions.get(i));
            System.out.println("+++++++++++++++++++");


            if (description != null) {
                Pattern re = Pattern.compile("[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)",
                        Pattern.MULTILINE | Pattern.COMMENTS);
                Matcher reMatcher = re.matcher(description);
                stopWords = new StopWords().getStopWords();
                while (reMatcher.find()) {
                    allSentences.add(reMatcher.group().toString());
                    String[] tokenizedTerms = reMatcher.group().toString().
                            replaceAll("[\\W&&[^\\s]]", "").split("\\W+");//to get individual terms

                    for (String term : tokenizedTerms) {
                        //avoid duplicate entry & stop words
                        if (!uniqueWords.contains(term) && !stopWords.contains(term.toLowerCase())) {
                            uniqueWords.add(term);
                        }
                    }
                    allWordsInDocuments.add(tokenizedTerms);
                }

            }
            documentCount++;
            System.out.println("Senetence size : " + allSentences.size() + " and document count is : " + documentCount);
        }
    }

    public List<String[]> getAllWordsInDocuments() {
        return this.allWordsInDocuments;
    }
}
