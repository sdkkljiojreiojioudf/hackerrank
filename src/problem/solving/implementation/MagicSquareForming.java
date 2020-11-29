package problem.solving.implementation;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MagicSquareForming {

    // Complete the formingMagicSquare function below.
    static Integer formingMagicSquare(int[][] s) {
        List<Integer[]> all = new ArrayList<>();
        all.add(new Integer[]{8, 1, 6, 3, 5, 7, 4, 9, 2});
        all.add(new Integer[]{6, 1, 8, 7, 5, 3, 2, 9, 4});
        all.add(new Integer[]{4, 9, 2, 3, 5, 7, 8, 1, 6});
        all.add(new Integer[]{2, 9, 4, 7, 5, 3, 6, 1, 8});
        all.add(new Integer[]{8, 3, 4, 1, 5, 9, 6, 7, 2});
        all.add(new Integer[]{4, 3, 8, 9, 5, 1, 2, 7, 6});
        all.add(new Integer[]{6, 7, 2, 1, 5, 9, 8, 3, 4});
        all.add(new Integer[]{2, 7, 6, 9, 5, 1, 4, 3, 8});

        List<Integer> integers = Arrays.stream(s)
                .flatMap(e -> Arrays.stream(e).boxed())
                .collect(Collectors.toList());

        OptionalInt optionalInteger = IntStream.range(0, 8)
                .map(i -> {
                    Integer[] integersToCompare = all.get(i);
                    return IntStream.range(0, 9).map(i2 -> Math.abs(integers.get(i2) - integersToCompare[i2])).sum();
                }).min();

        if (optionalInteger.isPresent()) {
            return optionalInteger.getAsInt();
        }
        return null;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] s = new int[3][3];

        for (int i = 0; i < 3; i++) {
            String[] sRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int sItem = Integer.parseInt(sRowItems[j]);
                s[i][j] = sItem;
            }
        }

        int result = formingMagicSquare(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }


}
