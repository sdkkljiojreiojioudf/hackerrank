package problem.solving.implementation.sixstars;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class WeightedUniformString {

    public static List<String> weightedUniformStrings(String s, List<Integer> queries) {
//        List<String> uniformSubStrings = new ArrayList<>();
//        HashMap
        // Write your code here

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Map<Character, Integer> characterWeightMap = new HashMap<>();
        for (int i = 0; i < alphabet.length(); i++) {
            characterWeightMap.put(alphabet.charAt(i), i+1);
        }

        Map<Integer, Boolean> weightHistory = new HashMap<>();

        Character firstCharOfUniformSubString = null;
        int consecutiveSameChar = 0;
        for (int i = 0; i < s.length(); i++) {
            char currentValue = s.charAt(i);
            int charWeight = characterWeightMap.get(currentValue);
            if (firstCharOfUniformSubString == null || firstCharOfUniformSubString != currentValue) {
                firstCharOfUniformSubString = currentValue;
                consecutiveSameChar = 1;
            }else if(firstCharOfUniformSubString == currentValue){
                consecutiveSameChar++;
            }
            int currentWeight = charWeight * consecutiveSameChar;
            weightHistory.put(currentWeight, null);
        }

        int cpt = 0;
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < queries.size(); i++) {
            if(weightHistory.containsKey(queries.get(i))){
                answer.add("Yes");
            }else{
                answer.add("No");
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<String> result = WeightedUniformString.weightedUniformStrings(s, queries);

        bufferedWriter.write(
                result.stream()
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
