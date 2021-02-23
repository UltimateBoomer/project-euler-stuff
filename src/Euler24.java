import java.util.*;

public class Euler24 {
    public static void run() {
        int perm = 1_000_000;
        int digits = 10;

        for (int p = 0; p < perm; p++) {
            calculate(p, digits);
        }
    }

    public static void calculate(int p, int digits) {
        List<Integer> number = new ArrayList<>();
        for (int i = 0; i < digits; i++) {
            number.add(i);
        }

        List<Integer> n = toFactorialBaseNum(p);
        Collections.reverse(n);

//        System.out.println(p + " " + n);


        for (int i = n.size() - 1; i >= 0; i--) {
            if (n.get(i) > 0) {
                for (int j = n.get(i) - 1; j >= 0; j--) {
                    int swap1 = digits - 1 - i - 1 + j;
                    int swap2 = digits - 1 - i + j;
                    int numAt = number.get(swap1);

                    number.remove(swap1);
                    number.add(swap2, numAt);
                }

//                System.out.println(swap1 + " " + swap2);
//                System.out.println(number);
            }

        }

        System.out.println(p + " " + n + " " + number);
    }

    public static List<Integer> toFactorialBaseNum(int n) {
        List<Integer> f = new ArrayList<>();
        int largestFactorial;
        for (int i = 1; ; i++) {
            int factorial = Common.factorial(i);
            if (factorial > n) {
                largestFactorial = i - 1;
                break;
            }
        }

        if (largestFactorial > 0) {
            int leftover = n;
            int currentFactorial = largestFactorial;
            while (currentFactorial > 0) {
                int base = Common.factorial(currentFactorial);
                int currentDigit = leftover / base;
                leftover -= currentDigit * base;
                currentFactorial--;
                f.add(currentDigit);
            }
        } else {
            f.add(0);
        }
        return f;
    }
}
