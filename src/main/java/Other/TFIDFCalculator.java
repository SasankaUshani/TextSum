package Other;

import java.util.ArrayList;
import java.util.List;


public class TFIDFCalculator {

    ArrayList<double[]> tdidfValuesForDocuments = new ArrayList<double[]>();
    double tfidf; //term requency inverse document frequency
    private List<String> allTerms = new ArrayList<String>(); //to hold all terms
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
        for (String [] doc : docs) {
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


    public ArrayList<double[]> calculateTF_IDF_valueOfAllDocuments(List<String[]> documents){
        double tf;
        double idf;
        int count = 0 ;

        for(String[] document:documents){
            double[] tdidfValuesForDocument = new double[allTerms.size()];
           // Double[] tdidfValuesForDocument = new Double[document.length];
            for(String word : allTerms){
                tf = calculateTf(document, word);
                idf = calculateIdf(documents, word);
                tfidf = tf * idf;
                tdidfValuesForDocument[count] = tfidf;

                count++;
            }
            tdidfValuesForDocuments.add(tdidfValuesForDocument);

        }
        return tdidfValuesForDocuments;
    }

    /**
     * Method to calculate cosine similarity between all the documents.
     */
    public void getCosineSimilarity() {
        for (int i = 0; i < tdidfValuesForDocuments.size(); i++) {
            for (int j = 0; j < tdidfValuesForDocuments.size(); j++) {
                System.out.println("between " + i + " and " + j + "  =  " +
                        new CosineSimilarity().cosineSimilarity(tdidfValuesForDocuments.get(i),tdidfValuesForDocuments.get(j)));
            }
        }
    }

}
