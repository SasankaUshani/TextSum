package Other;

import Models.Sentence;

import java.util.Comparator;

public class SentenceComparator implements Comparator<Sentence> {
    public int compare(Sentence o1, Sentence o2) {
        if(o1.getScore() < o2.getScore() )
            return 1;
        else if(o1.getScore() > o2.getScore())
            return -1;
        else
            return 0;

    }
}