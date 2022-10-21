package problem.solving.implementation.sixstars;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class SherlockAnagrams {


    public static String sortString(String inputString) {
        // Converting input string to character array
        char tempArray[] = inputString.toCharArray();

        // Sorting temp array using
        Arrays.sort(tempArray);

        // Returning new sorted string
        return new String(tempArray);
    }

    /*
     * Complete the 'sherlockAndAnagrams' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int sherlockAndAnagrams(String s) {


        long result = 0;

        // for each length of substring
        for (int i = 1; i < s.length(); i++) {
            Map<String, Integer> subStringCpt = new HashMap<>();

            //for each start of substring
            //capture anagrams in the map
            for (int j = 0; j <= s.length() - i; j++) {
                String substring = s.substring(j, (j + i));
                substring = sortString(substring);
                subStringCpt.put(substring, subStringCpt.getOrDefault(substring, 0) + 1);
            }

            //for each substring, find the number of pair of anagrams
            for (Map.Entry<String, Integer> entry : subStringCpt.entrySet()) {
                String subString = entry.getKey();
                Integer subStringNb = entry.getValue();
                if(subStringNb==1){
                    continue;
                }
                int annagramNb = 0;
                for (int j = 0; j < subStringNb; j++) {
                    annagramNb+=j;
                }
                result += annagramNb;
            }
        }
        return (int) result;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                int result = SherlockAnagrams.sherlockAndAnagrams(s);

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
