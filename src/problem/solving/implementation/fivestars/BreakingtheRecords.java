package problem.solving.implementation.fivestars;

import java.io.*;
import java.util.*;

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

    public static void main(String[] args) {


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
        System.out.println(Arrays.toString(result));


        scanner.close();
    }
}
