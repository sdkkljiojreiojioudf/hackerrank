package problem.solving.implementation;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;


public class StrangeAdvertising {

    // Complete the viralAdvertising function below.
    static int viralAdvertising(int n) {
        int shared = 5;
        double liked = 2;
        int cumulative = 2;
        for (int i = 0; i < n-1; i++) {
            shared = (int)liked * 3;
            double operation = (double)shared/(double)2;
            liked = Math.floor(operation);
            cumulative += liked;

        }

        return cumulative;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int result = viralAdvertising(n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}