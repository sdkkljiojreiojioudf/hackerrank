package problem.solving.implementation.sixstars;

import java.io.*;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LarrysArray {
    // 1, 3, 2 -> NO
    // 2, 3, 1 -> YES
    // 1, 2, 3
    // 6,5,4,3 ->  swap(5,4,3) -> 3,5,4
    // 6,3,5,4 ->  swap(6,3,5)  -> 3,5,6
    // 3,5,6,4 ->  swap(5,6,4)  -> 4,5,6
    // 3,4,5,6 -> YES
    // possible to swap, when there is 2 upper number at left
    // or 2 lower number at right


    public static String larrysArray(List<Integer> A) {
        int[] array = new int[A.size()];
        for (int i = 0; i < A.size(); i++) {
            array[i] = A.get(i);
        }
        // Write your code here
        for (int i = 0; i < array.length; i++) {
            if(i<0){
                continue;
            }

            if (i >= 2 && array[i] < array[i - 1]) {
                swap(array, i, false);
                if(array[i-2]>array[i-1]){
                    break;
                }
                i-=3;
            } else if (i < array.length - 2 && array[i] > array[i + 1]) {
                swap(array, i, true);
                i-=3;
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (i >= 1 && array[i] < array[i - 1]) {
                return "NO";
            } else if (i < array.length - 1 && array[i] > array[i + 1]) {
                return "NO";
            }
        }
        return "YES";
    }

    private static void swap(int[] arr, int swappedIndex, boolean leftValueToRight) {
        if (leftValueToRight) {
            for (int i = swappedIndex; i < swappedIndex + 2; i++) {
                int tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
            }
        } else {
            for (int i = swappedIndex; i > swappedIndex - 2; i--) {
                int tmp = arr[i];
                arr[i] = arr[i - 1];
                arr[i - 1] = tmp;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> A = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                String result = LarrysArray.larrysArray(A);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
