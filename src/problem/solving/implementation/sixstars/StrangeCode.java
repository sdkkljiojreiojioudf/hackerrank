package problem.solving.implementation.sixstars;

import java.io.*;
import java.util.Map;

public class StrangeCode {
    /*
     * Complete the 'strangeCounter' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts LONG_INTEGER t as parameter.
     */

    public static long strangeCounter(long t) {
        // Write your code here
        long maxColumnTime = 3;
        long columnRange = 3;

        while(maxColumnTime<t){
            columnRange *= 2;
            maxColumnTime += columnRange;
        }



        return maxColumnTime - t +1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        long t = Long.parseLong(bufferedReader.readLine().trim());

        long result = StrangeCode.strangeCounter(t);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
