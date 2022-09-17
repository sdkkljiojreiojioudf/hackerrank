package problem.solving.implementation.sixstars;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LilyHomework {


    public static int lilysHomework(List<Integer> list) {
        List<Integer> listSorted = list
                .stream()
                .sorted()
                .collect(toList());

        List<Integer> listReversed = list.stream()
                .sorted((o1, o2) -> o2 - o1)
                .collect(toList());


        int sortedListSwapNumber = getSwapNumber(listSorted, list);
        int reverseListSwapNumber = getSwapNumber(listReversed, list);

        if(sortedListSwapNumber<reverseListSwapNumber){
            return sortedListSwapNumber;
        }else{
            return reverseListSwapNumber;
        }



    }

    private static int getSwapNumber(List<Integer> sortedList, List<Integer> originalList) {
        int swapNumber = 0;
        int[] arr = new int[originalList.size()];
        for (int i = 0; i < originalList.size(); i++) {
            arr[i] = originalList.get(i);
        }

        Map<Integer, Integer> reverseIndexArray = new HashMap<>();
        for (int i = 0; i < sortedList.size(); i++) {
            reverseIndexArray.put(sortedList.get(i), i);
        }

        for (int i = 0; i < arr.length; i++) {
            int sortedIndex = reverseIndexArray.get(arr[i]);
            if (sortedIndex != i) {
                int temp = arr[i];
                arr[i] = arr[sortedIndex];
                arr[sortedIndex] = temp;
                swapNumber++;
                i--;
            }
        }
        return swapNumber;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = LilyHomework.lilysHomework(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
