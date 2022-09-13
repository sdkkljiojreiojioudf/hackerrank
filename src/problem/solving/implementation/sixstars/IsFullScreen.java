package problem.solving.implementation.sixstars;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class IsFullScreen {
    public static List<String> bigSorting(List<String> unsorted) {

        char[][] chars = new char[unsorted.size()][];
        for (int i = 0; i < unsorted.size(); i++) {
            String s = unsorted.get(i);
            char[] chars1 = new char[s.length()];
            for (int j = 0; j < s.length(); j++) {
                chars1[j] = s.charAt(j);
            }
            chars[i] = chars1;
        }

        Comparator<char[]> comparatorTest = (a, b) -> {
            if (a.length != b.length) {
                return a.length - b.length;
            }
            for (int i = 0; i < a.length; i++) {
                if(a[i] != b[i]){
                    return a[i] - b[i];
                }
            }
            return 0;
        };

        List<char[]> listOfChars;
        listOfChars = Arrays.asList(chars);
        listOfChars.sort(comparatorTest);

        List<String> result = new ArrayList<>();
        for (char[] charArr : listOfChars) {
            StringBuilder sb = new StringBuilder();
            for (char c : charArr) {
                sb.append(c);
            }
            result.add(sb.toString());
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> unsorted = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        List<String> result = IsFullScreen.bigSorting(unsorted);

        bufferedWriter.write(
                result.stream()
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
