package problem.solving.implementation.sixstars.FairRations;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FairRations {
    /*
     * Complete the 'fairRations' function below.
     * Times are hard and your castle's food stocks are dwindling, so you must distribute as few loaves as possible according to the following rules:

            1. Every time you give a loaf of bread to some person , you must also give a loaf of bread to the person immediately in front of or behind them in the line (i.e., persons  or ).
            2. After all the bread is distributed, each person must have an even number of loaves.
Given the number of loaves already held by each citizen, find and print the minimum number of loaves you must distribute to satisfy the two rules above. If this is not possible, print NO.
     * The function is expected to return a STRING.
     * The function accepts INTEGER_ARRAY B as parameter.
     */

    public static String fairRations(List<Integer> B) {
        //1 : to respect the 2 rules we check if there is an even number of odd number!
        double cpt = B.stream()
                .filter(e -> e % 2 != 0)
                .count();
        if (cpt % 2 != 0) {
            return "NO";
        }
        Integer[] array = new Integer[B.size()];
        B.toArray(array); // fill the array

        int loaveCPT = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 1) {
                array[i+1]++;
                loaveCPT += 2;
            }
        }
        return String.valueOf(loaveCPT);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int N = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> B = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String result = FairRations.fairRations(B);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
