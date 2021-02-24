package problem.solving.implementation;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

class PickingNumbers {

    /*
     * Complete the 'pickingNumbers' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    public static int pickingNumbers(List<Integer> a) {
        // Write your code here
        Map<Integer, Integer> integerIntegerHashMap = new HashMap<>();
        for (Integer elem : a) {
            if (integerIntegerHashMap.containsKey(elem)) {
                integerIntegerHashMap.put(elem, integerIntegerHashMap.get(elem) + 1);
            } else {
                integerIntegerHashMap.put(elem, 1);
            }
        }
        Optional<Integer> optionalInteger = integerIntegerHashMap.entrySet()
                .stream()
                .map(e -> getIntegerIntegerSimpleEntry(integerIntegerHashMap, e))
                .sorted(Map.Entry.comparingByValue())
                .map(AbstractMap.SimpleEntry::getValue)
                .max(Integer::compare);
        return optionalInteger.orElse(-1);

    }

    private static AbstractMap.SimpleEntry<Integer, Integer> getIntegerIntegerSimpleEntry(Map<Integer, Integer> integerIntegerHashMap, Map.Entry<Integer, Integer> e) {
        Integer key = e.getKey();
        int bonus = 0;
        if (integerIntegerHashMap.containsKey(key - 1)) {
            bonus = integerIntegerHashMap.get(key - 1);
        }
        if (integerIntegerHashMap.containsKey(key + 1)) {
            int newBonus = integerIntegerHashMap.get(key + 1);
            if (newBonus > bonus) {
                bonus = newBonus;
            }
        }
        return new AbstractMap.SimpleEntry<>(key, e.getValue() + bonus);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));


        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = PickingNumbers.pickingNumbers(a);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}