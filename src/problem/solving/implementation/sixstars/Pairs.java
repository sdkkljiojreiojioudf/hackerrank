package problem.solving.implementation.sixstars;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Pairs {

    /*
     * Complete the 'pairs' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY arr
     */

    public static int pairs(long k, List<Integer> arr) {
        // Write your code here


        int pairNb = 0;

        Map<Long, Integer> numberCpt = new HashMap<>();

        for (Integer elem : arr) {
            numberCpt.put(Long.valueOf(elem) ,numberCpt.getOrDefault(elem, 0) + 1);
        }
        for (Integer elem : arr) {
            Long elemAddK = (long)elem + k;
            if (elemAddK >= 0) {
                int elemPairNb = numberCpt.getOrDefault(elemAddK, 0);
                if (elemPairNb > 0) {
                    pairNb += elemPairNb;
                }
            }
        }

        return pairNb;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Pairs.pairs(k, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
