package problem.solving.implementation;

import java.util.*;

public class DivisibleSumPairs {

    // Complete the divisibleSumPairs function below.
    static int divisibleSumPairs(int k, int[] ar) {
        int cpt = 0;
        for (int i = 0; i < ar.length; i++) {
            int elem = ar[i];
            long pairsNb = Arrays
                    .stream(Arrays.copyOfRange(ar, i + 1, ar.length))
                    .filter(e -> ((elem + e) % k) == 0)
                    .count();
            cpt += pairsNb;
        }
        return cpt;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = divisibleSumPairs(k, ar);
        System.out.println(result);

        scanner.close();
    }
}
