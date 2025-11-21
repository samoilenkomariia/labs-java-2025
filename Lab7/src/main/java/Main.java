import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter 0 to exit\nEnter a number: ");
            if (!sc.hasNextInt()) break;
            int n = sc.nextInt();
            if (n == 0) break;

            System.out.println(getNumWithMaxZeros(n));
        }
    }

    public static String getNumWithMaxZeros(int n) {
        return solver.solve(n, isPrime, countZeroes);
    }

    interface TaskSolver {
        String solve(int n, Predicate<Integer> predicate, UnaryOperator<Integer> func);
    }

    static Predicate<Integer> isPrime = num -> {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        int sqrt = (int) Math.sqrt(num);
        for (int i = 3; i <= sqrt; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    };

    static UnaryOperator<Integer> countZeroes = num -> {
        String binary = Integer.toBinaryString(num);
        int count = 0;
        for (char c : binary.toCharArray()) {
            if (c == '0') count++;
        }
        return count;
    };

    static TaskSolver solver = (n, checkPrime, countZeros) -> {
        int resultNum = -1;
        int maxZeros = -1;

        for (int i = 0; i <= n; i++) {
            if (checkPrime.test(i)) {
                int count = countZeros.apply(i);
                if (count > maxZeros) {
                    maxZeros = count;
                    resultNum = i;
                }
            }
        }
        return resultNum == -1 ? "No prime number found" : String.format("%d has %d zero(s)", resultNum, maxZeros);
    };
}