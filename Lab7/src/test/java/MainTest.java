import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void testEdgeCases() {
        assertEquals("No prime number found", Main.getNumWithMaxZeros(0));
        assertEquals("No prime number found", Main.getNumWithMaxZeros(1));
    }

    @Test
    public void testSmallPrimes() {
        assertEquals("2 has 1 zero(s)", Main.getNumWithMaxZeros(2));
        assertEquals("2 has 1 zero(s)", Main.getNumWithMaxZeros(11));
    }

    @Test
    public void testLargePrimes() {
        assertEquals("17 has 3 zero(s)", Main.getNumWithMaxZeros(17));
        assertEquals("257 has 7 zero(s)", Main.getNumWithMaxZeros(1000));
    }
}