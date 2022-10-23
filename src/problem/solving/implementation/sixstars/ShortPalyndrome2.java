package problem.solving.implementation.sixstars;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ShortPalyndrome2 {
    /*
     * Complete the 'shortPalindrome' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */


    public static final int M = 1000000007;


    static int countPS5(String str) {
        int len = str.length();
        int[][][][] c4 = new int[26][26][26][26];
        int[][][] c3 = new int[26][26][26];
        int[][] c2 = new int[26][26];
        int[] c1 = new int[26];
        for (int i = 0; i < len; i++) {
            int c = str.charAt(i) - 122 + 25;
            for (int j = 0; j < 26; j++) {
                c4[c][j][j][c] = (c4[c][j][j][c] + c3[c][j][j]) % M;
                c3[j][c][c] = (c3[j][c][c] + c2[j][c]) % M;
                c2[j][c] = (c2[j][c] + c1[j]) % M;
            }
            c1[c] += 1;
        }
        int res = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                for (int k = 0; k < 26; k++) {
                    for (int l = 0; l < 26; l++) {
                        res = (res + c4[i][j][k][l]) % M;
                    }
                }
            }
        }
        return res;
    }

    static int countPS4(String str) {
        int len = str.length();
        List<Integer>[] charIndexLinkedList = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            charIndexLinkedList[i] = new ArrayList<>();
        }


        int[][] charCptRightToLeft = new int[1000000][26];
        int[] charCpt = new int[26];
        for (int i = 0; i < str.length(); i++) {
            int charAsciiCode = str.charAt(i);
            int alphabetIndex = charAsciiCode - 122 + 25;
            stringAlphabetIndex[i] = alphabetIndex;
            charCpt[alphabetIndex]++;
            charIndexLinkedList[alphabetIndex].add(i);
        }
        int[][] charCptLeftToRight = new int[1000000][26];


        for (int i = 0; i < 26; i++) {
            charCptLeftToRight[0][i] = charCpt[i];
            charCptRightToLeft[len - 1][i] = charCpt[i];
        }

        for (int i = 1; i < len; i++) {
            System.arraycopy(charCptLeftToRight[i - 1], 0, charCptLeftToRight[i], 0, 26);
            charCptLeftToRight[i][getAlphabetIndex(str, i)]--;
        }
        int alphabetIndex = getAlphabetIndex(str, 0);
        charCptRightToLeft[0][alphabetIndex]++;
        for (int i = 1; i < len; i++) {
            System.arraycopy(charCptRightToLeft[i - 1], 0, charCptRightToLeft[i], 0, 26);
            charCptRightToLeft[i][getAlphabetIndex(str, i)]++;
        }

        int[][] firstLastCharIndex = new int[26][2];
        for (int i = 0; i < 26; i++) {
            firstLastCharIndex[i][0] = -1;
        }
        for (int i = 0; i < len; i++) {
            alphabetIndex = getAlphabetIndex(str, i);
            if (firstLastCharIndex[alphabetIndex][0] == -1) {
                firstLastCharIndex[alphabetIndex][0] = i;
            } else {
                firstLastCharIndex[alphabetIndex][1] = i;
            }
        }

        int cpt = 0;
        for (int i = 0; i < 26; i++) {
            List<Integer> charHistory = new ArrayList<>();
            List<Integer>[] otherCharHistory = new ArrayList[26];
            for (int j = 0; j < otherCharHistory.length; j++) {
                otherCharHistory[j] = new ArrayList<>();
            }
            List<Integer> charLinkedList = charIndexLinkedList[i];
            int index = 0;
            boolean deleteFirstOtherCharHistory = false;
            while (index < charLinkedList.size()) {
                int consecutiv = 1;
                int elemIndex = charLinkedList.get(index);
                for (int j = index + 1; j < charLinkedList.size(); j++) {
                    int e = charLinkedList.get(j);
                    if (e == (elemIndex + 1)) {
                        elemIndex = e;
                        index++;
                        consecutiv++;
                    } else {
                        break;
                    }
                }
                charHistory.add(consecutiv);
                for (int j = 0; j < 26; j++) {
                    if (j == i) {
                        continue;
                    }

                    int valueCumulated = charCptRightToLeft[elemIndex][j];
                    if (valueCumulated == 0) {
                        continue;
                    }
                    int valueToSubstract = 0;
                    if (otherCharHistory[j].size() > 0) {
                        valueToSubstract = otherCharHistory[j].get(otherCharHistory[j].size() - 1);
                    }
                    int newValue = valueCumulated - valueToSubstract;
                    if (deleteFirstOtherCharHistory == true) {
//                        otherCharHistory[j].removeLast();
                        deleteFirstOtherCharHistory = false;
                    }
                    if (charHistory.size() == 1) {
                        deleteFirstOtherCharHistory = true;
                    }


                    otherCharHistory[j].add(24);
                }
                index++;
            }
            int show = 2;
            int s1 = charHistory.size();
            for (int j = 0; j < s1; j++) {
                for (int k = j + 1; k < s1; k++) {
                    cpt *= (j * k);
                }
            }
        }

        return cpt;
    }

    static int countPS3(String str) {
        List<Integer>[] charIndexLinkedList = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            charIndexLinkedList[i] = new ArrayList<>();
        }

        int len = str.length();
        int[] charCpt = new int[26];
        for (int i = 0; i < str.length(); i++) {
            int charAsciiCode = str.charAt(i);
            int alphabetIndex = charAsciiCode - 122 + 25;
            stringAlphabetIndex[i] = alphabetIndex;
            charCpt[alphabetIndex]++;
            charIndexLinkedList[alphabetIndex].add(i);
        }

        int totalCpt = 3;
        for (int i = 0; i < 26; i++) {
            List<Integer> integers = charIndexLinkedList[i];
            int intLen = integers.size();
            int leftIndex = 0;
            int cpt = 0;
            for (int j = 0; j < intLen; j++) {
                for (int k = j; k < intLen; k++) {
                    int elem = integers.get(k);
                }
            }
        }
        return totalCpt;
    }

    static int countPS2(String str) {
        List<Integer>[] charIndexLinkedList = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            charIndexLinkedList[i] = new ArrayList<>();
        }

        int len = str.length();
        int[] charCpt = new int[26];
        for (int i = 0; i < len; i++) {
            int charAsciiCode = str.charAt(i);
            int alphabetIndex = charAsciiCode - 122 + 25;
            stringAlphabetIndex[i] = alphabetIndex;
            charCpt[alphabetIndex]++;
            charIndexLinkedList[alphabetIndex].add(i);
        }

        int[][] charCptLeftToRight = new int[1000000][26];
        int[][] charCptRightToLeft = new int[1000000][26];

        for (int i = 0; i < 26; i++) {
            charCptLeftToRight[0][i] = charCpt[i];
            charCptRightToLeft[len - 1][i] = charCpt[i];
        }

        for (int i = 1; i < 26; i++) {
            System.arraycopy(charCptLeftToRight[i - 1], 0, charCptLeftToRight[i], 0, 26);
            charCptLeftToRight[i][getAlphabetIndex(str, i)]--;
        }
        for (int i = len - 2; i >= 0; i--) {
            System.arraycopy(charCptRightToLeft[i + 1], 0, charCptRightToLeft[i], 0, 26);
            charCptRightToLeft[i][getAlphabetIndex(str, i)]--;
        }

        int totalCpt = 0;

        int[][] firstLastCharIndex = new int[26][2];
        for (int i = 0; i < 26; i++) {
            firstLastCharIndex[i][0] = -1;
        }
        for (int i = 0; i < len; i++) {
            int alphabetIndex = getAlphabetIndex(str, i);
            if (firstLastCharIndex[alphabetIndex][0] == -1) {
                firstLastCharIndex[alphabetIndex][0] = i;
            } else {
                firstLastCharIndex[alphabetIndex][1] = i;
            }
        }


        for (int i = 0; i < len; i++) {
            int alphabetIndex = getAlphabetIndex(str, i);
            List<Integer> integers = charIndexLinkedList[alphabetIndex];
            integers.remove(0);
            int[] firstLast = firstLastCharIndex[alphabetIndex];
            int first = firstLast[0];
            int last = firstLast[1];
            if (i >= first && i <= last) {
                for (int j = 0, integersSize = integers.size(); j < integersSize; j++) {
                    totalCpt += 1;

                }


//                int pivotPairsCombinaisons = 0;
//                for (int j = 0; j < charCpt.length; j++) {
//                    int charNb = charCpt[j];
//                    if (charNb >= 2 && j != i) {
//                        pivotPairsCombinaisons = (int) ((pivotPairsCombinaisons + binomialCoeff2(charNb, 2)) % M);
//                    }
//                }
//                pivotPairsCombinaisons = (int) ((pivotPairsCombinaisons * Math.max(1, 1)) % M);
//                pivotPairsCombinaisons = (int) ((pivotPairsCombinaisons * Math.max(1, 1)) % M);
//                totalCpt += (pivotPairsCombinaisons % M);
//                totalCpt = (int) (totalCpt % M);
            }
        }


        return totalCpt;
    }


    // Function return the total palindromic
    // subsequence
    static int countPS(String str) {

        LinkedList<Integer>[] charIndexLinkedList = new LinkedList[26];
        for (int i = 0; i < 26; i++) {
            charIndexLinkedList[i] = new LinkedList<>();
        }

        int len = str.length();
        int[] charCpt = new int[26];
        for (int i = 0; i < str.length(); i++) {
            int charAsciiCode = str.charAt(i);
            int alphabetIndex = charAsciiCode - 122 + 25;
            stringAlphabetIndex[i] = alphabetIndex;
            charCpt[alphabetIndex]++;
            charIndexLinkedList[alphabetIndex].add(i);
        }

        int[][] charCptLeftToRight = new int[1000000][26];
        int[][] charCptRightToLeft = new int[1000000][26];

        for (int i = 0; i < charCpt.length; i++) {
            charCptLeftToRight[0][i] = charCpt[i];
            charCptRightToLeft[str.length() - 1][i] = charCpt[i];
        }

        for (int i = 1; i < str.length(); i++) {
            System.arraycopy(charCptLeftToRight[i - 1], 0, charCptLeftToRight[i], 0, 26);
            charCptLeftToRight[i][getAlphabetIndex(str, i)]--;
        }
        for (int i = str.length() - 2; i >= 0; i--) {
            System.arraycopy(charCptRightToLeft[i + 1], 0, charCptRightToLeft[i], 0, 26);
            charCptRightToLeft[i][getAlphabetIndex(str, i)]--;
        }


        int totalCpt = 0;

        int[][] firstLastCharIndex = new int[26][2];
        for (int i = 0; i < 26; i++) {
            firstLastCharIndex[i][0] = -1;
        }
        for (int i = 0; i < str.length(); i++) {
            int alphabetIndex = getAlphabetIndex(str, i);
            if (firstLastCharIndex[alphabetIndex][0] == -1) {
                firstLastCharIndex[alphabetIndex][0] = i;
            } else {
                firstLastCharIndex[alphabetIndex][1] = i;
            }
        }

        for (int i = 0; i < 26; i++) {
            int charNb = charCpt[i];
            if (charNb >= 4) {
                totalCpt = (int) ((totalCpt + binomialCoeff2(charNb, 4)) % M);
            }


            Set<String> indexAlreadyProcessed = new HashSet<>();


            //right to left
            int left = 0;
            int right = str.length() - 1;
            int leftMostChar = firstLastCharIndex[i][0];
            int rightMostChar = firstLastCharIndex[i][1];
            if (rightMostChar - leftMostChar < 3) {
                continue;
            }
            int[] charCptTmp = new int[26];
            for (int j = 0; j < 26; j++) {
                charCptTmp[j] = charCpt[j];
            }

            int leftCharIndex = getAlphabetIndex(str, left);
            int rightCharIndex = getAlphabetIndex(str, right);

            //SEARCH THE FIRST SERIE OF CONSECUTIV CHAR i FROM LEFT TO RIGHT
            int consecutivLeft = 0;
            while ((right - left) >= 2 && charCptTmp[i] > 0) {
                if (leftCharIndex == i) {
                    consecutivLeft++;
                }
                if (left >= leftMostChar && getAlphabetIndex(str, left + 1) != i) {
                    break;
                } else {

                    charCptTmp[leftCharIndex]--;
                    left++;
                    leftCharIndex = getAlphabetIndex(str, left);
                }
            }

            while ((right - left) >= 3 && charCptTmp[i] > 0) {
                int consecutivRight = 0;
                while ((right - left) >= 2 && charCptTmp[i] > 0) {
                    rightCharIndex = getAlphabetIndex(str, right);
                    if (i == rightCharIndex) {
                        consecutivRight++;
                        if (getAlphabetIndex(str, right - 1) != i) {
                            break;
                        }
                    }
                    right--;
                    charCptTmp[rightCharIndex]--;

                }


//                    System.out.println("letter : " + i + " left : " + left + ", consecutiveLeft : " + consecutivLeft + " right: " + right + " , consecutive Right " + consecutivRight);

                int pivotPairsCombinaisons = 0;
                for (int j = 0; j < charCptTmp.length; j++) {
                    charNb = charCptTmp[j];
                    if (charNb >= 2 && j != i) {
                        pivotPairsCombinaisons = (int) ((pivotPairsCombinaisons + binomialCoeff2(charNb, 2)) % M);
                    }
                }
                pivotPairsCombinaisons = (int) ((pivotPairsCombinaisons * Math.max(consecutivLeft, 1)) % M);
                pivotPairsCombinaisons = (int) ((pivotPairsCombinaisons * Math.max(consecutivRight, 1)) % M);
                totalCpt += (pivotPairsCombinaisons % M);
                totalCpt = (int) (totalCpt % M);


                while ((right - left) >= 2 && charCptTmp[i] > 0) {
                    rightCharIndex = getAlphabetIndex(str, right);
                    charCptTmp[rightCharIndex]--;
                    right--;
                    if (i != rightCharIndex) {
                        if (getAlphabetIndex(str, right) == i) {
                            break;
                        }
                    }


                }
                if ((right - left) < 3) {
//                    System.out.println("there is here where we should FIND THE SECOND SERIE OF CONSECUTIV CHAR i FROM LEFT TO RIGHT");
                    charCptTmp = new int[26];
                    for (int j = 0; j < 26; j++) {
                        charCptTmp[j] = charCpt[j];
                    }
                    for (int j = 0; j <= left; j++) {
                        charCptTmp[getAlphabetIndex(str, j)]--;
                    }
                    right = str.length() - 1;

                    left++;
                    consecutivLeft = 0;
                    leftCharIndex = getAlphabetIndex(str, left);
                    while ((right - left) >= 2 && charCptTmp[i] > 0) {
                        if (leftCharIndex == i) {
                            consecutivLeft++;
                        }
                        if (leftCharIndex == i && getAlphabetIndex(str, left + 1) != i) {
                            break;
                        } else {
                            charCptTmp[leftCharIndex]--;
                            left++;
                            leftCharIndex = getAlphabetIndex(str, left);
                        }
                    }

                }
            }
        }

        return totalCpt;
    }


    static int[] lookupTableModInverse = new int[5];

    static {
        for (int i = 0; i < 5; i++) {
            lookupTableModInverse[i] = 0;
        }
    }

    private static int binomialCoeff2(int n, int k) {
        if (k > n - k) {
            k = n - k;
        }

        long result = 1;
        result = getBinomialUpResult(n, k, result);
        result = getBinomialDownResult(k, result);
        return (int) result;
    }


    private static long getBinomialUpResult(int n, int k, long result) {
        for (int i = 1, m = n; i <= k; i++, m--) {
            result = ((result * m)) % M;
        }
        return result;
    }

    private static long getBinomialDownResult(int k, long result) {
        for (int i = 1; i <= k; i++) {
            int modInverseResult = 0;
            if (lookupTableModInverse[i] > 0) {
                modInverseResult = lookupTableModInverse[i];
            } else {
                modInverseResult = modInverse(i, M);
                lookupTableModInverse[i] = modInverseResult;
            }
            result = (result * modInverseResult) % M;
        }
        return result;
    }


    private static int getAlphabetIndex(String str, int i) {
        return stringAlphabetIndex[i];
    }


    static long __gcd(long a, long b) {

        if (b == 0) {
            return a;
        } else {
            return __gcd(b, a % b);
        }
    }

    static long power(long x, long y, long m) {
        if (y == 0) {
            return 1;
        }
        long p = power(x, y / 2, m) % m;
        p = (p * p) % m;

        return (y % 2 == 0) ? p : (x * p) % m;
    }


    static int modInverse(long a, long m) {
        if (__gcd(a, m) != 1) {
            System.out.print("Inverse doesn't exist");
        } else {
            return (int) (power(a, m - 2, m) % M);
        }
        return -1;
    }

    public static int factorial(int from, int to) {
        long cpt = 1;
        for (int i = from; i <= to; i++) {
            cpt = (cpt * i) % M;
        }
        return (int) cpt;
    }


}


class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        int result = ShortPalyndrome2.countPS5(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
