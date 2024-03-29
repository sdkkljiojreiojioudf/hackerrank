package problem.solving.implementation.sixstars;

import javax.imageio.metadata.IIOMetadataFormatImpl;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


class ShortPalyndrome {

    /*
     * Complete the 'shortPalindrome' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static final long M = 1000000007;
    public static int[] stringAlphabetIndex =  new int[1000000];

    // Function return the total palindromic
    // subsequence
    static int countPS(String str) {
        int[] charCpt = new int[26];

        for (int i = 0; i < str.length(); i++) {
            int charAsciiCode = str.charAt(i);
            int alphabetIndex = charAsciiCode - 122 + 25;
            stringAlphabetIndex[i] = alphabetIndex;
        }

        for (int i = 0; i < str.length(); i++) {
            int alphabetIndex = getAlphabetIndex(str, i);
            charCpt[alphabetIndex]++;
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
                    int previousLeftCharIndex = leftCharIndex;
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


    public static String getKey(int left, int right) {
        return new StringBuilder()
                .append(left)
                .append("-")
                .append(right)
                .toString();
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
            if(lookupTableModInverse[i]>0){
                modInverseResult = lookupTableModInverse[i];
            }else{
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


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        int result = ShortPalyndrome.countPS(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
