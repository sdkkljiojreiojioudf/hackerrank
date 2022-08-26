package problem.solving.implementation.sixstars;

import java.io.*;
import java.util.stream.IntStream;

class ChocolateFeast {

    /*
     * Complete the 'chocolateFeast' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER c
     *  3. INTEGER m
     */

    public static int chocolateFeast(int n, int c, int m) {
        // Write your code here

        int wrapperNumber = 0;

        int cpt = 0;

        //1 : we calculate the number of bar we can buy
        int chocolateBarNumber = n / c;
        cpt += chocolateBarNumber;

        wrapperNumber += chocolateBarNumber;


        while (wrapperNumber >= m) {
            chocolateBarNumber = 0;

            //3 : we had the bars we can buy with
            chocolateBarNumber += (wrapperNumber / m);

            //3: we recalculte wrapper number
            wrapperNumber = wrapperNumber % m;
            wrapperNumber += chocolateBarNumber;

            cpt += chocolateBarNumber;

        }

        return cpt;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int c = Integer.parseInt(firstMultipleInput[1]);

                int m = Integer.parseInt(firstMultipleInput[2]);

                int result = ChocolateFeast.chocolateFeast(n, c, m);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
