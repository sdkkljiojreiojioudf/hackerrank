package problem.solving.implementation.fivestars;

import java.util.*;


public class SalesByMatch {
    // Complete the sockMerchant function below.
    static int sockMerchant(int[] ar) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        Arrays.stream(ar).forEach(elem -> {
            if (hashMap.containsKey(elem)) {
                hashMap.put(elem, hashMap.get(elem) + 1);
            } else {
                hashMap.put(elem, 1);
            }
        });
        return hashMap
                .values()
                .stream().map(e -> (e % 2 == 0 ? e : e - 1)/2)
                .reduce(0, Integer::sum);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }



        scanner.close();
    }
}
