package problem.solving.leetcode;

import java.util.*;

public class LetterCombinationsOfAPhoneNumber {
    public static Map<Integer, char[]> map = new HashMap<>();

    static {
        map.put(2, "abc".toCharArray());
        map.put(3, "def".toCharArray());
        map.put(4, "ghi".toCharArray());
        map.put(5, "jkl".toCharArray());
        map.put(6, "mno".toCharArray());
        map.put(7, "pqrs".toCharArray());
        map.put(8, "tuv".toCharArray());
        map.put(9, "wxyz".toCharArray());
    }

    public static  List<String> letterCombinations(List<String> combinaisons, List<Integer> integers) {
        for (Integer digitNb : integers) {
            List<String> combinaisonsTmp = new ArrayList<>();
            for (String combinaison : combinaisons) {
                char[] s = map.get(digitNb);
                for (Character c : s) {
                    StringBuilder stringBuilder = new StringBuilder().append(combinaison);
                    stringBuilder.append(c);
                    combinaisonsTmp.add(stringBuilder.toString());
                }
            }
            combinaisons = combinaisonsTmp;
        }
        return combinaisons;
    }

    public static List<String> letterCombinations(String digits) {
        if(digits.isEmpty()){
            return new ArrayList<>();
        }
        List<Integer> integers = new ArrayList<>();
        for (int i = 1; i < digits.length(); i++) {
            Character c = digits.charAt(i);
            Integer digitNb = Character.getNumericValue(c);
            integers.add(digitNb);
        };
        List<String> firstCharList = new ArrayList<>();
        char[] firstDigitLetters = map.get(Character.getNumericValue(digits.charAt(0)));
        for(Character character : firstDigitLetters){
            firstCharList.add(String.valueOf(character));
        }
        List<String> comb = letterCombinations(firstCharList, integers);
        return comb;
    }

    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber.letterCombinations("23");
    }
}
