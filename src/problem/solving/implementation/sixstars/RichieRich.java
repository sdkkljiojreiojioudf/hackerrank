package problem.solving.implementation.sixstars;

import java.io.*;
import java.util.HashMap;

public class RichieRich {
    public static String highestValuePalindrome(String s, int n, int k) {
        // Write your code here
        boolean isMiddleAloneIndex = n % 2 != 0;
        int middleIndex = n / 2;

        StringBuilder stringCopy = new StringBuilder(s);

        HashMap<Integer, Integer> updatedIndex = new HashMap<>();
        for (int i = 0; i < n / 2; i++) {

            int value = getNumericValue(stringCopy, i);
            int mirrorValue = getNumericValue(stringCopy, (s.length() - i - 1));


            if (value != mirrorValue) {
                if (k <= 0 && i < n / 2) {
                    return "-1";
                }
                int replacingValue;
                if (mirrorValue > value) {
                    replacingValue = mirrorValue;
                } else {
                    replacingValue = value;
                }
                stringCopy = replaceTwoNumberByNine(stringCopy, i, replacingValue);
                k -= 1;
                updatedIndex.put(i, null);
            }
        }
        if (k == 0) {
            return stringCopy.toString();
        }


        for (int i = 0; i < n / 2; i++) {
            if (k < 1) {
                break;
            }
            int value = getNumericValue(stringCopy,i);
            int mirrorValue = getNumericValue(stringCopy,s.length() - i - 1);
            if (value == mirrorValue && value < 9) {
                if (updatedIndex.containsKey(i) && k >= 1) {
                    stringCopy = replaceTwoNumberByNine(stringCopy, i, 9);
                    updatedIndex.remove(i);
                    k -= 1;
                } else if (k >= 2) {
                    stringCopy = replaceTwoNumberByNine(stringCopy, i, 9);
                    k -= 2;
                }
            }
        }

        if (isMiddleAloneIndex && k >= 1) {
            int value = Integer.parseInt(s.substring(middleIndex, middleIndex + 1));
            if (value < 9) {
                char targetChat = String.valueOf(9).charAt(0);
                stringCopy.setCharAt(middleIndex, targetChat);
            }
        }


        return stringCopy.toString();
    }

    private static int getNumericValue(StringBuilder stringCopy, int i) {
        return Character.getNumericValue(stringCopy.charAt(i));
    }

    private static StringBuilder replaceTwoNumberByNine(StringBuilder s, int i, int targetNumber) {
        char targetChat = String.valueOf(targetNumber).charAt(0);
        s.setCharAt(i, targetChat);
        s.setCharAt(s.length() - i - 1, targetChat);
        return s;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        String s = bufferedReader.readLine();

        String result = RichieRich.highestValuePalindrome(s, n, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
