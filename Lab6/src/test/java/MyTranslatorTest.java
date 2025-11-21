import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyTranslatorTest {

    private static MyTranslator translator;

    @BeforeAll
    static void setUp() {
        translator = new MyTranslator();
        translator.addWord("hello", "привіт");
        translator.addWord("i", "я");
        translator.addWord("it", "це");
        translator.addWord("you", "ти");
        translator.addWord("we", "ми");
        translator.addWord("do", "робити");
        translator.addWord("do not", "не");
        translator.addWord("not", "не");
        translator.addWord("love", "люблю");
        translator.addWord("like", "подобається");
        translator.addWord("is", "є");
        translator.addWord("are", "є");
        translator.addWord("am", "є");
        translator.addWord("my", "моє");
        translator.addWord("hobby", "хобі");
        translator.addWord("cat", "кіт");
        translator.addWord("dog", "собака");
        translator.addWord("book", "книга");
        translator.addWord("red", "червоний");
        translator.addWord("blue", "синій");
        translator.addWord("green", "зелений");
        translator.addWord("colors", "кольори");
        translator.addWord("riding a bike", "їздити на велосипеді");
        translator.addWord("look at", "дивитися на");
        translator.addWord("turn on", "увімкнути");
        translator.addWord("give up", "здаватися");
        translator.addWord("a piece of cake", "дуже просто");
        translator.addWord("out of the blue", "раптово");
    }

    @Test
    void testSimpleTranslation() {
        assertEquals("привіт", translator.translate("hello"));
        assertEquals("я", translator.translate("I"));
        assertEquals("ти", translator.translate("You"));
        assertEquals("книга", translator.translate("book"));
        assertEquals("червоний", translator.translate("red"));
        assertEquals("синій", translator.translate("blue"));
        assertEquals("зелений", translator.translate("green"));
        assertEquals("моє кіт", translator.translate("my cat"));
    }

    @Test
    void testSentenceTranslation() {
        String input = "I love my hobby";
        String expected = "я люблю моє хобі";

        assertEquals(expected, translator.translate(input));
    }

    @Test
    void testCaseInsensitivity() {
        assertEquals("привіт", translator.translate("HeLLo"));
        assertEquals("я люблю", translator.translate("I LoVe"));
        assertEquals("увімкнути", translator.translate("Turn ON"));
    }

    @Test
    void testPhraseTranslation() {
        assertEquals("моє хобі є їздити на велосипеді", translator.translate("My hobby is riding a bike"));
        assertEquals("я люблю дивитися на червоний книга", translator.translate("I love look at red book"));
    }

    @Test
    void testIdiomsAndLongPhrases() {
        assertEquals("це є дуже просто", translator.translate("It is a piece of cake"));
        assertEquals("раптово", translator.translate("Out of the blue"));
        assertEquals("ми не здаватися", translator.translate("We do not give up"));
    }

    @Test
    void testPunctuationSimple() {
        assertEquals("привіт,", translator.translate("hello,"));
        assertEquals("я люблю моє кіт.", translator.translate("I love my cat."));
        assertEquals("зелений!", translator.translate("Green!"));
    }

    @Test
    void testPunctuationWithPhrases() {
        // The punctuation should stick to the last word of the translated phrase
        assertEquals("увімкнути!", translator.translate("Turn on!"));
        assertEquals("раптово?", translator.translate("Out of the blue?"));
        assertEquals("дуже просто.", translator.translate("A piece of cake."));
    }

    @Test
    void testPunctuationWrappers() {
        assertEquals("(привіт)", translator.translate("(hello)"));
        assertEquals("\"собака\"", translator.translate("\"dog\""));
        assertEquals("[їздити на велосипеді]", translator.translate("[riding a bike]"));
    }

    @Test
    void testUnknownWords() {
        assertEquals("я люблю Java", translator.translate("I love Java"));
        assertEquals("Xenomorph є scary", translator.translate("Xenomorph is scary"));
    }

    @Test
    void testMixedComplexScenario() {
        String input = "Hey, look at that blue dog!";
        String expected = "Hey, дивитися на that синій собака!";
        assertEquals(expected, translator.translate(input));
        assertEquals("червоний, зелений кольори!", translator.translate("red, green colors!"));
    }

    @Test
    void testEdgeCasesWhitespace() {
        assertEquals("привіт world", translator.translate("hello    world"));
    }

    @Test
    void testEdgeCasesNumbersAndSymbols() {
        assertEquals("я have 2 кіт", translator.translate("I have 2 cat"));
        assertEquals("user@mail.com", translator.translate("user@mail.com"));
    }

    @Test
    void testEdgeCasesNullAndEmpty() {
        assertEquals("", translator.translate(""));
        assertEquals("", translator.translate(null));
        assertEquals("", translator.translate("   "));
    }
}