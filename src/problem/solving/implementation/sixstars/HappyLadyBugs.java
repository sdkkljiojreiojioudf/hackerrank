package problem.solving.implementation.sixstars;

import java.io.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class HappyLadyBugs {
    /*
     * Complete the 'happyLadybugs' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING b as parameter.
     */

    public static String happyLadybugs(String b) {

        int[] characterNumbers = new int[100];

        Arrays.fill(characterNumbers, 0);

        int emptyCharacterNumber = 0;
        for (int i = 0; i < b.length(); i++) {
            if (b.charAt(i) == '_') {
                emptyCharacterNumber++;
            } else {
                characterNumbers[b.charAt(i)]++;
            }
        }

        if(emptyCharacterNumber==0){
            //checking if a character have the same character in an adjacent cell
            for (int i = 1; i < b.length()-1; i++) {
                char elem = b.charAt(i);
                if(elem != b.charAt(i-1) && elem != b.charAt(i+1)){
                    return "NO";
                }
            }
        }


        for (int characterNumber : characterNumbers) {
            if (characterNumber == 1) {
                return "NO";
            }
        }

        return "YES";

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int g = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, g).forEach(gItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                String b = bufferedReader.readLine();

                String result = HappyLadyBugs.happyLadybugs(b);

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
