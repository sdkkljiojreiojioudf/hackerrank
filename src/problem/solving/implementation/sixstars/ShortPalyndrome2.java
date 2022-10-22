package problem.solving.implementation.sixstars;


import java.awt.*;
import java.io.*;
import java.util.*;


class ShortPalyndrome2 {

    /*
     * Complete the 'shortPalindrome' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static final long M = 1000000007;
    public static int[] stringAlphabetIndexStatic = new int[1000000];

    public static int shortPalindrome(String s)
    {
        for (int i = 0; i < s.length(); i++) {
            int charAsciiCode = s.charAt(i);
            int alphabetIndex = charAsciiCode - 122 + 25;
            stringAlphabetIndexStatic[i] = alphabetIndex;
        }

        int n = s.length();

        int[][] l = new int[26][1000000];

        l[getAlphabetIndex(s, 0)][0]++;

        // precompute the prefix 2D array
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                l[j][i] += l[j][i-1];
            }
            l[getAlphabetIndex(s, i)][i]++;
        }

        int[][] r = new int[26][1000000];
        r[getAlphabetIndex(s, n-1)][n-1]++;
        //precompute the suffix 2D array
        for (int i = n-2; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                r[j][i] += r[j][i+1];
            }
            r[getAlphabetIndex(s, i)][i]++;
        }



        return 2;
    }



    private static Point getPoint(int left, int consecutiv) {
        return new Point(
                left,
                consecutiv
        );
    }

    private static class Point {
        private final int position;
        private final int consecutiv;

        public Point(int position, int consecutiv) {
            this.position = position;
            this.consecutiv = consecutiv;
        }

        public int getPosition() {
            return position;
        }

        public int getConsecutiv() {
            return consecutiv;
        }
    }

    // Function return the total palindromic
    // subsequence
    static int countPS(String str) {
        int[] charCpt = new int[26];

        for (int i = 0; i < str.length(); i++) {
            int charAsciiCode = str.charAt(i);
            int alphabetIndex = charAsciiCode - 122 + 25;
            stringAlphabetIndexStatic[i] = alphabetIndex;
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
                totalCpt = (int) ((totalCpt + binomialCoeff3(charNb, 4)) % M);
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


//                    System.out.println("letter : " + i + " left : " + left +  " right: " + right + ", consecutiveLeft : " + consecutivLeft +" , consecutive Right " + consecutivRight);

                int pivotPairsCombinaisons = 0;
                int charNbToRight = 0;

                for (int j = 0; j < charCptTmp.length; j++) {
                    charNb = charCptTmp[j];
                    if (j == i) {
                        int test = charCpt[i] - charCptTmp[i] + 1;
                        charNbToRight = test;

                    } else if (charNb >= 2) {
                        pivotPairsCombinaisons = (int) ((pivotPairsCombinaisons + binomialCoeff3(charNb, 2)) % M);
                    }
                }
                pivotPairsCombinaisons = (int) ((pivotPairsCombinaisons * Math.max(consecutivLeft, 1)) % M);
                pivotPairsCombinaisons = (int) ((pivotPairsCombinaisons * Math.max(consecutivRight, 1)) % M);
                totalCpt += (pivotPairsCombinaisons % M);
                totalCpt = (int) (totalCpt % M);

                pivotPairsCombinaisons = 0;
                for (int j = 0; j < charCptTmp.length; j++) {
                    charNb = charCpt[j] - charCptTmp[i];
                    if (charNb >= 2 && i != j) {
                        pivotPairsCombinaisons = (int) ((pivotPairsCombinaisons + binomialCoeff3(charNb, 2)) % M);
                    }
                }
                int charNbToLeft = charCpt[i] - consecutivLeft;
                charNbToLeft = charNbToLeft - charNbToRight;
                pivotPairsCombinaisons = (int) ((pivotPairsCombinaisons * Math.max(charNbToRight, 1)) % M);
                pivotPairsCombinaisons = (int) ((pivotPairsCombinaisons * Math.max(charNbToLeft, 1)) % M);
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
//                    charCptTmp = new int[26];
//                    for (int j = 0; j < 26; j++) {
//                        charCptTmp[j] = charCpt[j];
//                    }
//                    for (int j = 0; j <= left; j++) {
//                        charCptTmp[getAlphabetIndex(str, j)]--;
//                    }
//                    right = str.length() - 1;
//
//                    left++;
//                    consecutivLeft = 0;
//                    leftCharIndex = getAlphabetIndex(str, left);
//                    int previousLeftCharIndex = leftCharIndex;
//                    while ((right - left) >= 2 && charCptTmp[i] > 0) {
//                        if (leftCharIndex == i) {
//                            consecutivLeft++;
//                        }
//                        if (leftCharIndex == i && getAlphabetIndex(str, left + 1) != i) {
//                            break;
//                        } else {
//                            charCptTmp[leftCharIndex]--;
//                            left++;
//                            leftCharIndex = getAlphabetIndex(str, left);
//                        }
//                    }

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
        result = getBinomialDownResult2(k, result);
        return (int) result;
    }

    private static int binomialCoeff3(int n, int k) {
        long res = 1;

        // Since C(n, k) = C(n, n-k)
        if (k > n - k)
            k = n - k;

        // Calculate value of
        // [n * (n-1) *---* (n-k+1)] / [k * (k-1) *----* 1]
        for (int i = 0; i < k; ++i) {
            res = getBinomialUpResult2(n, res, i);
            res = getBinomialDownResult2(i + 1, res);
        }

        return (int) res;
    }

    private static long getBinomialUpResult2(int n, long res, int i) {
        res = (res * (n - i)) % M;
        return res;
    }

    private static long getBinomialDownResult2(int k, long result) {
        int down = 1;
        switch (k) {
            case 1:
                down = 1;
                break;
            case 2:
                down = 500000004;
                break;
            case 3:
                down = 333333336;
                break;
            case 4:
                down = 250000002;
                break;
        }
        result = (result * down) % M;
        return result;
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
        return stringAlphabetIndexStatic[i];
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

        int result = ShortPalyndrome2.shortPalindrome(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
