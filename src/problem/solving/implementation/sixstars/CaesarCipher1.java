package problem.solving.implementation.sixstars;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CaesarCipher1 {
    /*
     * Complete the 'caesarCipher' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER k
     */

    public static String caesarCipher(String s, int k) {
        // Write your code here
        if(k>26){
            k = k % 26;
        }
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        Map<Character, Integer> reverseAlphabetIndex = new HashMap<Character, Integer>();
        for (int i = 0; i < alphabet.length; i++) {
            reverseAlphabetIndex.put(alphabet[i], i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean isUpperCase = Character.isUpperCase(c);
            if(isUpperCase){
                c = Character.toLowerCase(c);
            }
            if(reverseAlphabetIndex.containsKey(c)==false){
                sb.append(c);
                continue;
            }
            int index = reverseAlphabetIndex.get(c);
            index += k;
            if(index > 25){
                index = index - 26;
            }
            char newC = alphabet[index];
            if(isUpperCase){
                newC = Character.toUpperCase(newC);
            }
            sb.append(newC);

        }

        return sb.toString();

    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        String result = CaesarCipher1.caesarCipher(s, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
