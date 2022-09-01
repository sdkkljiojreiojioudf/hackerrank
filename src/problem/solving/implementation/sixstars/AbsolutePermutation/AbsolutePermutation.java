package problem.solving.implementation.sixstars.AbsolutePermutation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

class AbsolutePermutation {

    /*
     * Complete the 'absolutePermutation' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     */

    public static List<Integer> absolutePermutation(int n, int k) {
        // Write your code here
        int[] indexes = new int[n + 1];
        Stack<Integer> stack = new Stack<>();
        List<Integer> resultList;

        for (int i = 1; i <= n; i++) {

            int substraction = i - k;

            if (substraction > 0 && indexes[substraction] == 0) {
                indexes[substraction]++;
                stack.push(substraction);
            } else {
                int addition = i + k;
                if (addition > 0 && addition < n + 1 && indexes[addition] == 0) {
                    indexes[addition]++;
                    stack.push(addition);
                } else {
                    resultList = new ArrayList<>();
                    resultList.add(-1);
                    return resultList;
                }
            }
        }

        return new ArrayList<>(stack);
    }



    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int k = Integer.parseInt(firstMultipleInput[1]);

                List<Integer> result = AbsolutePermutation.absolutePermutation(n, k);

                bufferedWriter.write(
                        result.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                                + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
