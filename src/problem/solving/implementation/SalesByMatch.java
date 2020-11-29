package problem.solving.implementation;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;


public class SalesByMatch {
    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < ar.length; i++) {
            int elem = ar[i];
            if (hashMap.containsKey(elem)) {
                hashMap.put(elem, hashMap.get(elem) + 1);
            } else {
                hashMap.put(elem, 1);
            }
        }
        int test = hashMap
                .values()
                .stream().map(e -> (e % 2 == 0 ? e : e - 1)/2)
                .reduce(0, Integer::sum);
        return test;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {


        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant(n, ar);


        scanner.close();
    }
}
