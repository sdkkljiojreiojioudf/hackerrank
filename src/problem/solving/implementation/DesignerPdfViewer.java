package problem.solving.implementation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DesignerPdfViewer {

    // Complete the designerPdfViewer function below.
    static int designerPdfViewer(int[] h, String word) {
        String letters = "abcdefghijklmnopqrstuvwxyz";

        long t1, t2;
        t1 = System.currentTimeMillis();
        int maxHeight = word.chars()
                .parallel()
                .map(e->h[letters.indexOf(e)])
                .max().orElse(-1);
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);


        t1 = System.currentTimeMillis();
         maxHeight = word.chars()
                .map(e->h[letters.indexOf(e)])
                .max().orElse(-1);
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);


        return maxHeight * word.length();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[] h = new int[26];

        String[] hItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < 26; i++) {
            int hItem = Integer.parseInt(hItems[i]);
            h[i] = hItem;
        }

        String word = scanner.nextLine();

        int result = designerPdfViewer(h, word);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}