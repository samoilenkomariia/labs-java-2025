import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTranslator {
    private Map<String, String> dictionary;

    private static class Token {
        String prefix = "";
        String word = "";
        String suffix = "";

        public Token(String raw) {
            Pattern p = Pattern.compile("^([\\p{Punct}\\s]*)(.*?)([\\p{Punct}\\s]*)$");
            Matcher m = p.matcher(raw);
            if (m.find()) {
                this.prefix = m.group(1);
                this.word = m.group(2);
                this.suffix = m.group(3);
            } else {
                this.word = raw;
            }
        }
    }

    public MyTranslator() {
        this.dictionary = new HashMap<>();
    }

    public void addWord(String eng, String ukr) {
        dictionary.put(eng.trim().toLowerCase(), ukr.trim());
    }

    public String translate(String text) {
        if (text == null || text.isEmpty()) return "";

        String[] rawWords = text.trim().split("\\s+");
        List<Token> tokens = new ArrayList<>();

        for (String raw : rawWords) {
            tokens.add(new Token(raw));
        }

        int n = tokens.size();
        boolean[] isConsumed = new boolean[n];
        String[] translations = new String[n];

        for (int windowSize = n; windowSize >= 1; windowSize--) {
            for (int i = 0; i <= n - windowSize; i++) {
                if (isRangeFree(isConsumed, i, windowSize)) {

                    String phrase = buildCleanPhrase(tokens, i, windowSize);

                    if (dictionary.containsKey(phrase.toLowerCase())) {
                        translations[i] = dictionary.get(phrase.toLowerCase());

                        Token firstToken = tokens.get(i);
                        Token lastToken = tokens.get(i + windowSize - 1);
                        translations[i] = firstToken.prefix + translations[i] + lastToken.suffix;

                        markRangeConsumed(isConsumed, i, windowSize);
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (translations[i] != null) {
                result.append(translations[i]).append(" ");
            } else if (!isConsumed[i]) {
                Token t = tokens.get(i);
                result.append(t.prefix).append(t.word).append(t.suffix).append(" ");
            }
        }

        return result.toString().trim();
    }

    private String buildCleanPhrase(List<Token> tokens, int index, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = index; i < index + length; i++) {
            sb.append(tokens.get(i).word);
            if (i < index + length - 1) sb.append(" ");
        }
        return sb.toString();
    }

    private boolean isRangeFree(boolean[] isConsumed, int index, int windowSize) {
        for (int i = index; i < index + windowSize; i++) {
            if (isConsumed[i]) return false;
        }
        return true;
    }

    private void markRangeConsumed(boolean[] isConsumed, int index, int windowSize) {
        for (int i = index; i < index + windowSize; i++) {
            isConsumed[i] = true;
        }
    }
}