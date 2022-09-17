package problem.solving.implementation.sixstars;

import java.io.*;

public class ReducedString {
    /*
     * Complete the 'superReducedString' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String superReducedString(String s) {
        // Write your code here
        for (int i = 0; i < s.length()-1; i++) {
            if(s.charAt(i) == s.charAt(i+1)){
                s = s.substring(0, i) + s.substring(i+2);
                i = Math.max(-1, i-2);
            }
        }
        if(s.isEmpty()){
            return "Empty String";

        }
        return s;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = ReducedString.superReducedString(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
