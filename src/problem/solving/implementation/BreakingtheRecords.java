package problem.solving.implementation;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BreakingtheRecords {

    // Complete the breakingRecords function below.
    static int[] breakingRecords(int[] scores) {
        int cpt_min = 0;
        int score_min = scores[0];
        int cpt_max = 0;
        int score_max = scores[0];
        for (int i = 1; i < scores.length; i++) {
            int score = scores[i];
            if (score < score_min) {
                cpt_min += 1;
                score_min = score;
            } else if (score > score_max) {
                cpt_max++;
                score_max = score;
            }
        }
        return new int[]{cpt_max, cpt_min};
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {


        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] scores = new int[n];

        String[] scoresItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int scoresItem = Integer.parseInt(scoresItems[i]);
            scores[i] = scoresItem;
        }

        int[] result = breakingRecords(scores);
        System.out.println(result);


        scanner.close();
    }
}
