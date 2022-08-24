package problem.solving.implementation.sixstars;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class BeautifulTriplets {



    /*
     * Complete the 'beautifulTriplets' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER d
     *  2. INTEGER_ARRAY arr
     */

    //10 3
    //1 6 7 7 8 10 12 13 14 19

    //13,10,7
    //10,7,
    public static int beautifulTriplets(int d, List<Integer> arr) {
        // Write your code here

        int[] ints = new int[20000];


        for (Integer elem : arr) {
            ints[elem]++;
        }

        int cpt = 0;

        //searching triplets in arr

        for (int i = arr.size() - 1; i > 0; i--) {
            Integer elem = arr.get(i);

            int cptTmp = 0;

            // FIRST STEP
            // is an elem from distance d from elem exist in this arr ?
            int previousIndex = elem - d;
            Integer previousValue = getPreviousValue(ints, previousIndex);
            if (previousValue == null) continue;

            if (previousValue > 1) {
                cptTmp++;
            }

            // SECOND STEP
            // is an elem from distance (d * 2) from previous elem of elem exist in this arr ?
            previousIndex = elem - (2 * d);
            previousValue = getPreviousValue(ints, previousIndex);
            if (previousValue == null) continue;
            if (previousValue > 1) {
                cptTmp++;
            }

            cpt++;
            cpt += cptTmp;
        }

        return cpt;
    }

    private static Integer getPreviousValue(int[] ints, int previousIndex) {
        if (previousIndex < 0) {
            return null;
        }


        int previousValue = ints[previousIndex];
        if (previousValue < 1) {
            return null;
        }
        return previousValue;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int d = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = BeautifulTriplets.beautifulTriplets(d, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
