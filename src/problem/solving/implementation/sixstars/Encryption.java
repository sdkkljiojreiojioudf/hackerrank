package problem.solving.implementation.sixstars;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Encryption {
    /*
     * Complete the 'encryption' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String encryption(String s) {
        // Write your code here
        s = s.trim();
        double length_sqrt = Math.sqrt(s.length());
        Double lengthCeil = Math.ceil(length_sqrt);
        Double lengthFloor = Math.floor(length_sqrt);


        String[][] strings = getStrings(s, lengthCeil, lengthFloor);

        return getEncryptedString(lengthCeil, lengthFloor, strings);
    }

    private static String[][] getStrings(String s, Double lengthCeil, Double lengthFloor) {

        String[][] strings = new String[lengthFloor.intValue() + 1][lengthCeil.intValue()];
        int cpt = 0;
        for (int i = 0; i < lengthCeil; i++) {
            for (int j = 0; j < lengthCeil && cpt < s.length(); j++) {
                strings[i][j] = s.substring(cpt, cpt + 1);
                cpt++;
            }
        }
        return strings;
    }

    private static String getEncryptedString(Double lengthCeil, Double lengthFloor, String[][] strings) {
        StringBuilder stringBuilder = new StringBuilder();

        //iterate each columns
        for (int i = 0; i < lengthCeil; i++) {
            //iterate over each rows
            for (int j = 0; j <= lengthFloor; j++) {
                String s1 = strings[j][i];
                if (s1 == null) {
                    break;
                }
                stringBuilder.append(s1);

            }
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Encryption.encryption(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
