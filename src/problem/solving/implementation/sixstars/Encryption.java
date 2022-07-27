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

        s = s.trim();
        double length_sqrt = Math.sqrt(s.length());
        Double lengthCeil = Math.ceil(length_sqrt);
        Double lengthFloor = Math.floor(length_sqrt);

        String[][] stringArray2d = getStringArray2d(s, lengthCeil);

        StringBuilder stringBuilder = getEncryptedString(lengthCeil, lengthFloor, stringArray2d);


        return stringBuilder.toString();

    }

    private static StringBuilder getEncryptedString(Double lengthCeil, Double lengthFloor, String[][] stringArray2d) {
        StringBuilder stringBuilder = new StringBuilder("");

        //for each columns
        for (int i = 0; i < lengthCeil; i++) {

            //for each row, add the "i" column index to the encrypted string
            for (int j = 0; j <= lengthFloor; j++) {

                String s1 = stringArray2d[j][i];
                if (s1 == null) {
                    break;
                }
                stringBuilder.append(s1);

            }
            stringBuilder.append(" ");
        }
        return stringBuilder;
    }

    private static String[][] getStringArray2d(String s, Double lengthCeil) {
        String[][] stringArray2d = new String[lengthCeil.intValue()][lengthCeil.intValue()];
        int cpt = 0;
        for (int i = 0; i < lengthCeil; i++) {
            for (int j = 0; j < lengthCeil && cpt < s.length(); j++) {
                stringArray2d[i][j] = s.substring(cpt, cpt + 1);
                cpt++;
            }
        }
        return stringArray2d;
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
