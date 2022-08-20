package problem.solving.implementation.sixstars;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;


class Result {


    // ehdegnmorgafrjxvksc
    // ehdegnmorgafrjxvsck
    // ehdegnmorgcafjkrsvx

    //dkhc
    //hcdk
    //kcdh

    // hefg
    // hfeg
    // hegf
    public static String biggerIsGreater(String w) {


        //first step, sort all letters, except the first

        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        TreeMap<Integer, Integer> stringIndexByAlpheticalPosition = new TreeMap<>();

        Integer biggestAlphabetPositionIndex = -1;

        Integer leftSwapIndex = -1;
        Integer rightSwapIndex = -1;

        // at each iteration, we want to check if at the right of index i
        // there is a minimal letter upper to w[i]
        for (int i = w.length()-1; i >= 0; i--) {
            char letter = w.charAt(i);
            Integer alphabeticalposition = alphabet.indexOf(letter);
            stringIndexByAlpheticalPosition.putIfAbsent(alphabeticalposition, i);
            if(stringIndexByAlpheticalPosition.size()>1) {
                Integer test = stringIndexByAlpheticalPosition.higherKey(alphabeticalposition);
                if(test==null){
                    continue;
                }
                rightSwapIndex =stringIndexByAlpheticalPosition.get(test);
                leftSwapIndex = i;
                break;
            }
        }

        if(leftSwapIndex==-1){
            return "no answer";
        }
        //swapping the 2 letters
        String stringAfterSwap = swap(w, leftSwapIndex, rightSwapIndex);

        // after left swap position, sort alls
        String finalString = sort(stringAfterSwap, leftSwapIndex+1, stringAfterSwap.length());
        return finalString;
    }

    static String sort(String str, int start, int end){
        char[] chars = str.toCharArray();
        Arrays.sort(chars, start, end);
        return new String(chars);
    }

    static String swap(String str, int i, int j){
        char[] chars = str.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }

}



public class BiggerIsGreater {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, T).forEach(TItr -> {
            try {
                String w = bufferedReader.readLine();

                String result = Result.biggerIsGreater(w);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
