package max_words_count_in_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class WordCounter {

    public static String getMaxCountOfWordsInFile(String filePath) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            Optional<String> maxLine = stream.max(Comparator.comparingInt(line -> line.split("\\s+").length));
            if (maxLine.isPresent()) {
                return maxLine.get();
            }
        }
        return "";
    }

    public static String getMaxCountOfWordsInText(String data) {
        if (data == null) {
            return "";
        }
        String[] lines = data.split("\\R");
        String result = "";
        int max = 0;

        for (String line : lines) {
            String trimmedLine = line.trim();
            int currentWordCount;
            if (trimmedLine.isEmpty()) {
                currentWordCount = 0;
            } else {
                currentWordCount = trimmedLine.split("\\s+").length;
            }

            if (currentWordCount > max) {
                max = currentWordCount;
                result = line;
            }
        }
        return result;
    }
}
