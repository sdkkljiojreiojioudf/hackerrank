package problem.solving.implementation.sixstars;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CountingSort4 {
    public static void countSort(List<List<String>> arr) {

        for (int i = 0; i < arr.size() / 2; i++) {
            List<String> strings = arr.get(i);
            strings.set(1, "-");
        }
        String toPrint = arr.stream()
                .sorted(Comparator.comparingInt(o -> Integer.parseInt(o.get(0))))
                .map(e -> e.get(1))
                .collect(Collectors.joining(" "));

        System.out.println(toPrint);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> arr = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                arr.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        CountingSort4.countSort(arr);

        bufferedReader.close();
    }

}
