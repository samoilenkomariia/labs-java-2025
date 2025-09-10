import java.util.Scanner;
// 7.
//Серед простих чисел, які не перевищують заданий n, знайти таке, в двійковій формі
//якого максимальна кількість нулів.
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
        if (num <= 1) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        int sqrt = (int) Math.sqrt(num);
        for (int i = 3; i <= sqrt; i+=2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
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
