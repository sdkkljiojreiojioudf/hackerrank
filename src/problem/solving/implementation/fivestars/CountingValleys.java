package problem.solving.implementation.fivestars;

import java.io.*;


public class CountingValleys {
    /*
     * Complete the 'countingValleys' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER steps
     *  2. STRING path
     */

    public static int countingValleys(String path) {
        // Write your code here
        int valleysNumber = 0;
        int seaLevel = 0;
        int level = 0;
        for (int i = 0; i < path.length(); i++) {
            char stepCod = path.charAt(i);
            if (stepCod == 'D') {
                level -= 1;

            } else {
                level += 1;
                if (level == seaLevel) {
                    valleysNumber += 1;
                }
            }


        }
        return valleysNumber;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int steps = Integer.parseInt(bufferedReader.readLine().trim());

        String path = bufferedReader.readLine();

        int result = CountingValleys.countingValleys(path);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
