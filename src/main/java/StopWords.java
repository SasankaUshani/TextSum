
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class StopWords {

    public ArrayList<String> getStopWords() throws IOException, InterruptedException {
        Path file = Paths.get("/Users/sasankakudagoda/Desktop/IIT/TextSum/Project/src/main/java", "Stopword.txt");
        return (ArrayList<String>) Files.readAllLines(file, StandardCharsets.UTF_8);
    }
}
