import org.junit.jupiter.api.Test;

public class Lab1Test {
    @Test
    public void testIsSimple() {
        assert !Lab1.isSimple(1);
        assert Lab1.isSimple(2);
        assert Lab1.isSimple(3);
        assert !Lab1.isSimple(4);
        assert Lab1.isSimple(5);
        assert !Lab1.isSimple(6);
        assert Lab1.isSimple(7);
        assert !Lab1.isSimple(8);
        assert !Lab1.isSimple(9);
        assert !Lab1.isSimple(10);
        assert Lab1.isSimple(11);
    }

    @Test
    public void testCountZerosInStr() {
        assert Lab1.countZerosInStr("11001") == 2;
        assert Lab1.countZerosInStr("1111") == 0;
    }

    @Test
    public void testGetNumWithMaxZeros() {
        assert Lab1.getNumWithMaxZeros(0).equals("No such number");
        assert Lab1.getNumWithMaxZeros(1).equals("No such number");
        assert Lab1.getNumWithMaxZeros(2).equals("2 has 1 zero(s)");
        assert Lab1.getNumWithMaxZeros(11).equals("2 has 1 zero(s)");
        assert Lab1.getNumWithMaxZeros(17).equals("17 has 3 zero(s)");
        assert Lab1.getNumWithMaxZeros(1000).equals("257 has 7 zero(s)");
    }
}
