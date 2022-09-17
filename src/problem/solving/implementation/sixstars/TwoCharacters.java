package problem.solving.implementation.sixstars;

import java.io.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

public class TwoCharacters {
    /*
     * Complete the 'alternate' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int alternate(String s) {
        // Write your code here
        Set<Character> processed = new HashSet<>();
        int maxValue = 0;
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            if (processed.contains(a)) {
                continue;
            }

            OptionalInt optionalInt = processed.stream().mapToInt(b -> {
                if (a != b) {
                    int cptA = 0;
                    int cptB = 0;
                    for (int j = 0; j < s.length(); j++) {
                        char c = s.charAt(j);
                        if (b == c) {
                            if (cptA == cptB) {
                                cptB++;
                            } else {
                                return -1;
                            }
                        } else if (a == c) {
                            if ((cptB - 1) == cptA) {
                                cptA++;
                            } else {
                                return -1;
                            }
                        }
                    }
                    return cptA + cptB;
                }
                return -1;

            }).max();
            if(optionalInt.isPresent()) {
                maxValue = Math.max(maxValue, optionalInt.getAsInt());
            }
            processed.add(a);
        }
        return maxValue;


    }



    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int l = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int result = TwoCharacters.alternate(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
