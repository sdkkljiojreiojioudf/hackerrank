package problem.solving.implementation;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

public class MigratoryBirds {


    // Complete the migratoryBirds function below.
    static int migratoryBirds(List<Integer> arr) {
        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();
        int maxVal = -1;
        for (int e : arr) {
            int val = -1;
            if (!integerIntegerHashMap.containsKey(e)) {
                integerIntegerHashMap.put(e, 1);
            } else {
                Integer oldVal = integerIntegerHashMap.get(e);
                val = oldVal + 1;
            }
            integerIntegerHashMap.put(e, val);
            if (val > maxVal) {
                maxVal = val;
            }
        }
        return Collections.max(integerIntegerHashMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));



        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = migratoryBirds(arr);

        System.out.println(result);

        bufferedReader.close();

    }
}
