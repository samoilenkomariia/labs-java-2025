package count_tags_url;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static Map<String, Integer> tags = new HashMap<>();
    private static final Scanner sc = new Scanner(System.in);
    private static final String DEFAULT_URL = "https://example.com/";

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String input = sc.nextLine();
            switch (input) {
                case "0":
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                case "1":
                    handleFetchUrl();
                    break;
                case "2":
                    handleViewTags();
                    break;
                case "3":
                    handleSortTagsAlphabetically();
                    break;
                case "4":
                    handleSortTagsByOccurrence();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
    private static void printMenu() {
        System.out.print("""
                
                0 - Exit
                1 - Enter url
                2 - View tag count
                3 - Sort tag alphabetically
                4 - Sort tag by number of occurrence
                Enter your choice:
                """);
    }

    private static void handleFetchUrl() {
        System.out.print("Enter url (default: " + DEFAULT_URL + "): ");
        String url = sc.nextLine();
        if (url.isEmpty()) {
            url = DEFAULT_URL;
        }

        try {
            System.out.println("Fetching url... " + url);
            tags = TagCounter.getTagsCount(url);
            System.out.println("Successfully fetched and counted tags:");
            System.out.println(printMapBeautifully(tags));
        } catch (IOException e) {
            System.err.printf("Error working with URL: %s%n", e.getMessage());
        }
    }

    private static void handleViewTags() {
        if (tags.isEmpty()) {
            System.out.println("No tags loaded. Use option 1 to fetch a URL first.");
            return;
        }
        System.out.println("Current Tag Count:");
        System.out.println(printMapBeautifully(tags));
    }

    private static void handleSortTagsAlphabetically() {
        if (tags.isEmpty()) {
            System.out.println("No tags loaded. Use option 1 to fetch a URL first.");
            return;
        }
        var sortedMap = tags.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        System.out.println("Tags Sorted Alphabetically:");
        System.out.println(printMapBeautifully(sortedMap));
    }

    private static void handleSortTagsByOccurrence() {
        if (tags.isEmpty()) {
            System.out.println("No tags loaded. Use option 1 to fetch a URL first.");
            return;
        }
        var sortedMap = tags.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        System.out.println("Tags Sorted by Occurrence:");
        System.out.println(printMapBeautifully(sortedMap));
    }

    private static String printMapBeautifully(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            sb.append("  ").append(entry.getKey()).append(" = ").append(entry.getValue()).append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }
}