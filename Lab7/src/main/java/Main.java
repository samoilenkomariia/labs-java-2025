import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;

import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter 0 to exit\nEnter a number: ");
            if (!sc.hasNextInt()) break;
            int n = sc.nextInt();
            if (n == 0) {
                sc.close();
                break;
            }
            System.out.println(getNumWithMaxZeros(n));
        }
    }

    public static String getNumWithMaxZeros(int n) {
        Optional<Integer> result = IntStream.rangeClosed(2, n)
                .filter(i -> isPrime.test(i))
                .boxed()
                .max(Comparator.comparing(i -> countZeroes.applyAsInt(i)));
        return result.map(num -> String.format("%d has %d zero(s)", num, countZeroes.applyAsInt(num)))
                .orElse("No prime number found");
    }

    static IntPredicate isPrime = num -> {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        int sqrt = (int) Math.sqrt(num);
        for (int i = 3; i <= sqrt; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    };

    static IntUnaryOperator countZeroes = num -> {
        String binary = Integer.toBinaryString(num);
        int count = 0;
        for (char c : binary.toCharArray()) {
            if (c == '0') count++;
        }
        return count;
    };
}