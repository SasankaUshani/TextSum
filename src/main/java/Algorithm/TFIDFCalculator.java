package Algorithm;

import Preprocessor.StopWordsRemover;

import java.io.IOException;
import java.util.*;


public class TFIDFCalculator {

    ArrayList<Double[]> tdidfValuesForDocuments = new ArrayList<Double[]>();
    List<String> stopWords = null;
    List<String> uniqueWords = new ArrayList<>(); // unique words in description

    /**
     * @param doc  list of strings
     * @param term String represents a term
     * @return term frequency of term in document
     */
    public double calculateTf(String[] doc, String term) {
        double result = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word))
                result++;
        }
        return result / doc.length;
    }

    /**
     * @param docs list of list of strings represents the dataset
     * @param term String represents a term
     * @return the inverse term frequency of term in documents
     */
    public double calculateIdf(List<String[]> docs, String term) {
        double n = 0;
        for (String[] doc : docs) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        return Math.log(docs.size() / n);
    }

    /**
     * @param doc  a text document
     * @param docs all documents
     * @param term term
     * @return the TF-IDF of term
     */
    public double tfIdf(String[] doc, List<String[]> docs, String term) {
        return calculateTf(doc, term) * calculateIdf(docs, term);

    }


    public ArrayList<Double[]> calculateTF_IDF_valueOfAllDocuments(List<String[]> descriptions) throws IOException, InterruptedException {
        double tf;
        double idf;
        int count = 0;
        double tfidfOfSentence = 0;
        double tfidfOfAllSentences = 0;
        HashMap<String[], Double> hm = new HashMap<String[], Double>();
        stopWords = new StopWordsRemover().getStopWords();

        for (String[] description : descriptions) {
            Double[] tdidfValuesForDocument = new Double[descriptions.size()];
            for (String word : description) {
                if (!uniqueWords.contains(word) && !stopWords.contains(word.toLowerCase())) {
                    uniqueWords.add(word);

                    tf = calculateTf(description, word);
                    idf = calculateIdf(descriptions, word);

                    tdidfValuesForDocument[count] = tf * idf;

                    tfidfOfSentence += tf * idf;
                } else {
//                    System.out.println("Stop word - " + word);
                }
            }
            tfidfOfAllSentences += tfidfOfSentence;
            hm.put(description, tfidfOfSentence);
//            calculateSentenceImportance(String.valueOf(description), tfidfOfSentence, tfidfOfAllSentences);

            tfidfOfSentence = 0;
            count++;

            tdidfValuesForDocuments.add(tdidfValuesForDocument);
        }

        return tdidfValuesForDocuments;
    }
}



