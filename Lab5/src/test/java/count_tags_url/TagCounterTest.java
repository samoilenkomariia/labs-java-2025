package count_tags_url;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TagCounterTest {

    @Test
    void testFetchHTML() {
        String content = "";
        try {
            content = TagCounter.fetchHTML("https://example.com/");
        } catch  (IOException e) {
            System.err.println("Error reading fetching HTML" + e.getMessage());
            fail();
        }
        String expected = "<!doctype html><html lang=\"en\"><head><title>Example Domain</title><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><style>body{background:#eee;width:60vw;margin:15vh auto;font-family:system-ui,sans-serif}h1{font-size:1.5em}div{opacity:0.8}a:link,a:visited{color:#348}</style><body><div><h1>Example Domain</h1><p>This domain is for use in documentation examples without needing permission. Avoid use in operations.<p><a href=\"https://iana.org/domains/example\">Learn more</a></div></body></html>\n";

        assertEquals(expected, content, "invalid fetched HTML content, expected: %s, got: %s".formatted(expected, content));
    }

    @Test
    void testParseHTML() {
        Map<String,Integer> map = TagCounter.parseHTML("""
                <!DOCTYPE html>
                <html>
                <body>
                
                <h1>My First Heading</h1>
                
                <p>My first paragraph.</p>
                
                </body>
                </html>
                
                """);
        String[] testTags = {"html", "body", "h1", "p"};
        assertEquals(testTags.length, map.size(), "expected map size %d, got %d".formatted(testTags.length, map.size()));
        Arrays.asList(testTags).forEach(t -> {
            assertTrue(map.containsKey(t), "map does not contain key " + t);
            assertEquals(1, map.get(t), "expected map tp have 1 instance of " + t);
        });
    }
}
