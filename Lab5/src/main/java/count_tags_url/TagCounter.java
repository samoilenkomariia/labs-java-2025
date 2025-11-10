package count_tags_url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TagCounter {

    public static Map<String, Integer> getTagsCount(String url) throws IOException {
        String content = fetchHTML(url);
        return parseHTML(content);
    }

    public static String fetchHTML(String stringUrl) throws IOException {
        URL url = new URL(stringUrl);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            StringBuilder content = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
            return content.toString();
        }
    }

    public static Map<String, Integer> parseHTML(String content) {
        Map<String, Integer> map = new HashMap<>();

        int index = 0;
        while (index < content.length()) {
            int tagStart = content.indexOf('<', index);
            if (tagStart == -1) break;

            if (content.startsWith("<!--", tagStart)) {
                int commentEnd = content.indexOf("-->", tagStart + 4);
                if (commentEnd == -1) break;
                index = commentEnd + 3;
                continue;
            }

            int tagEnd = content.indexOf('>', tagStart+1);
            if  (tagEnd == -1) break;
            String tagContent = content.substring(tagStart + 1, tagEnd).trim();

            if (tagContent.isEmpty() ||
                    tagContent.startsWith("/") ||
                    tagContent.startsWith("!") ||
                    tagContent.startsWith("?")) {
                index = tagEnd + 1;
                continue;
            }

            int spaceIndex = tagContent.indexOf(' ');
            int slashIndex = tagContent.indexOf('/');
            int cutIndex = tagContent.length();
            if (spaceIndex != -1 && spaceIndex < cutIndex) cutIndex = spaceIndex;
            if (slashIndex != -1 && slashIndex < cutIndex) cutIndex = slashIndex;

            String tagName = tagContent.substring(0, cutIndex).toLowerCase();
            tagName = tagName.replaceAll("[^a-zA-Z0-9:-]", "");

            if (tagName.isEmpty() || !Character.isLetter(tagName.charAt(0))) {
                index = tagEnd + 1;
                continue;
            }

            if (tagName.equals("script") || tagName.equals("style")) {
                String closingTag = "</" + tagName + ">";
                int closeIndex = content.indexOf(closingTag, tagEnd + 1);
                if (closeIndex == -1) {
                    break;
                }
                map.put(tagName, map.getOrDefault(tagName, 0) + 1);
                index = closeIndex + closingTag.length();
                continue;
            }

            map.put(tagName, map.getOrDefault(tagName, 0) + 1);
            index = tagEnd + 1;
        }
        return map;
    }
}
