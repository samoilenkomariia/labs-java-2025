import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = sc.nextInt();
        System.out.println(getNumWithMaxZeros(n));
    }

    public static String getNumWithMaxZeros(int n) {
        int resultNum = -1;
        int maxZeroCount = -1;
        for (int i = 0; i <= n; i++) {
            if (isSimple(i)) {
                int zeroCount = countZerosInStr(Integer.toBinaryString(i));
                if (zeroCount > maxZeroCount) {
                    maxZeroCount = zeroCount;
                    resultNum = i;
                }
            }
        }
        return resultNum == -1 ? "No such number" : "%d has %d zero(s)".formatted(resultNum, maxZeroCount);
    }

    public static boolean isSimple(int num) {
        if (num <= 1) {
            return false;
        }
        return switch(num) {
            case 2,3,5,7 -> true;
            default -> {
                if (num % 2 == 0 || num % 3 ==0 || num % 5 == 0 || num % 7 == 0) {
                    yield false;
                }
                yield true;
            }
        };
    }

    public static int countZerosInStr(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                count++;
            }
        }
        return count;
    }
}
