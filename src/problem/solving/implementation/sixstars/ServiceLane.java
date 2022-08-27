package problem.solving.implementation.sixstars;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


public class ServiceLane {
    /*
     * Complete the 'serviceLane' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY cases
     */

    public static List<Integer> serviceLane(List<Integer> width, List<List<Integer>> cases) {
        // Write your code here
        List<Integer> maximumWidthVehiculeThatCanPass = new ArrayList<>();
        for (List<Integer> caseElement : cases) {
            int leftIndex = caseElement.get(0);
            int rightIndex = caseElement.get(1);
            List<Integer> caseWidthList = width.subList(leftIndex, rightIndex+1);
            OptionalInt minimumWidth;
            minimumWidth = caseWidthList.stream().mapToInt(e -> e).min();
            if(minimumWidth.isPresent()) {
                maximumWidthVehiculeThatCanPass.add(minimumWidth.getAsInt());
            }
        }
        return maximumWidthVehiculeThatCanPass;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int t = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> width = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<List<Integer>> cases = new ArrayList<>();

        IntStream.range(0, t).forEach(i -> {
            try {
                cases.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = ServiceLane.serviceLane(width, cases);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }

}
