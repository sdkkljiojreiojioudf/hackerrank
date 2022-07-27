package problem.solving.implementation.fivestars;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

public class SubArrayDivision {

    // Complete the birthday function below.
    static int birthday(List<Integer> s, int d, int m) {
        int cpt = 0;
        for (int i = 0; i <= s.size() - m; i++) {
            int dayNumber = s.subList(i, i + m).stream().mapToInt(Integer::intValue).sum();
            if (dayNumber == d) {
                cpt++;
            }
        }
        return cpt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        String[] dm = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int d = Integer.parseInt(dm[0]);

        int m = Integer.parseInt(dm[1]);



        bufferedReader.close();

    }
}
