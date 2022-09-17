package problem.solving.implementation.sixstars;

import java.io.*;

public class StrongPassword {
    /*
     * Complete the 'minimumNumber' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING password
     */

    public static int minimumNumber(int n, String password) {
        // Return the minimum number of characters to make the password strong
        int characterToAdd = 0;

        boolean containUpperCase = false;
        boolean containLowerCase = false;
        boolean containNumbers = false;
        boolean containSpecialCharacters = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (!containNumbers && Character.isDigit(c)) {
                containNumbers = true;
            } else if (!containUpperCase && Character.isUpperCase(c)) {
                containUpperCase = true;
            } else if (!containLowerCase && Character.isLowerCase(c)) {
                containLowerCase = true;

            }
            if (!containSpecialCharacters && password.substring(i, i + 1).matches("[!@#$%^&*()\\-+]")) {
                containSpecialCharacters = true;
            }
        }

        if (!containLowerCase) {
            characterToAdd++;
        }
        if (!containUpperCase) {
            characterToAdd++;
        }
        if(!containNumbers){
            characterToAdd++;
        }
        if (!containSpecialCharacters) {
            characterToAdd++;
        }

        int total = password.length() + characterToAdd;
        if (total < 6) {
            characterToAdd += 6 - total;
        }
        return characterToAdd;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String password = bufferedReader.readLine();

        int answer = StrongPassword.minimumNumber(n, password);

        bufferedWriter.write(String.valueOf(answer));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
