package Other;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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

        stopWords = new StopWords().getStopWords();
        for (String[] description : descriptions) {
            Double[] tdidfValuesForDocument = new Double[descriptions.size()];
            for (String word : description) {
                if (!uniqueWords.contains(word) && !stopWords.contains(word.toLowerCase())) {
                    uniqueWords.add(word);

                    tf = calculateTf(description, word);
                    idf = calculateIdf(descriptions, word);

                    tdidfValuesForDocument[count] = tf * idf;
                    System.out.println("Word : " + word + " - " + tf * idf);

                } else {
//                    System.out.println("Stop word - " + word);
                }
            }
                count++;
                System.out.println("Total count is : " + count);
                tdidfValuesForDocuments.add(tdidfValuesForDocument);
            }


        return tdidfValuesForDocuments;
    }

    /**
     * Method to calculate cosine similarity between all the documents.
     */
//    public void getCosineSimilarity() {
//        for (int i = 0; i < tdidfValuesForDocuments.size(); i++) {
//            for (int j = 0; j < tdidfValuesForDocuments.size(); j++) {
//                System.out.println("between " + i + " and " + j + "  =  " +
//                        new CosineSimilarity().cosineSimilarity(tdidfValuesForDocuments.get(i),tdidfValuesForDocuments.get(j)));
//            }
//        }
//    }

}
