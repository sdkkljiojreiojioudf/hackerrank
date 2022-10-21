package problem.solving.implementation.sixstars;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CommonChild {
    /*
     * Complete the 'commonChild' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING s1
     *  2. STRING s2
     */
    public static int commonChild(String s1, String s2) {
        // Write your code here
        int result1 = getChildMaxSize(s1, s2);
        int result2 = getChildMaxSize(s2, s1);

        return Math.max(result1, result2);

    }

    private static int getChildMaxSize(String s1, String s2) {
        int maxSerieCpt = 0;
        int[][] matchingTable = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            char s1Char = s1.charAt(i - 1);
            for (int j = 1; j <= s2.length(); j++) {
                char s2Char = s2.charAt(j - 1);

                if (s1Char == s2Char) {
                    matchingTable[i][j] = matchingTable[i - 1][j-1] + 1;
                    if(matchingTable[i][j]>maxSerieCpt){
                        maxSerieCpt = matchingTable[i][j];
                    }
                } else {
                    matchingTable[i][j] = Math.max(matchingTable[i - 1][j-1], matchingTable[i][j-1]);
                    matchingTable[i][j] = Math.max(matchingTable[i][j], matchingTable[i-1][j]);
                }
            }
        }

        return maxSerieCpt;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = bufferedReader.readLine();

        String s2 = bufferedReader.readLine();

        int result = CommonChild.commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
