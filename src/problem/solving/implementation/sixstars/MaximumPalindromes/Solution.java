package problem.solving.implementation.sixstars.MaximumPalindromes;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.IntStream;


class Result {


    public static final long M = 1000000007;


    public Map<Range, Long> initialize(String s, Queue<Range> rangeList) {
        long[] result = new long[rangeList.size()];

        long[] nominatorByPairNumberIndexCache = new long[1+(s.length() / 2)];
        long[] denominatorByPairNumberIndexCache = new long[1+(s.length() / 2)];

        Map<Range, Long> rangeLongMap = new HashMap<>();

        int[][] leftCache = new int[s.length()][];
        int[][] rightCache = new int[s.length()][];

        int[] stringCharCode = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int charAsciiCode = s.charAt(i);
            int alphabetIndex = charAsciiCode - 122 + 25;
            stringCharCode[i] = alphabetIndex;
        }

        int[] charCpt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charCpt[stringCharCode[i]]++;
        }
        int[] charNumbers = getNewCharCpt(s, leftCache, rightCache, charCpt, stringCharCode, 0, s.length() - 1);

        long permutationNb = 0;
        long denominator = 1;
        int pairsNb = 0;
        long aloneLetterNb = 0;
        for (int charNumber : charNumbers) {
            if (charNumber >= 2) {
                long pairNb = charNumber / 2;
                pairsNb += pairNb;
                long pairDenominator = 1;
                for (int j = 1; j <= pairNb; j++) {
                    pairDenominator = (pairDenominator * modInverse(j, M))%M;
                }
                denominatorByPairNumberIndexCache[(int) pairNb] = pairDenominator;
                denominator = (denominator* pairDenominator)%M;
            }
            if (charNumber % 2 != 0) {
                aloneLetterNb++;
            }
        }


        long nominator = 1;
        for (int j = 1; j <= pairsNb; j++) {
            nominator = (nominator * j) % M;
        }
        aloneLetterNb = Math.max(aloneLetterNb, 1);
        permutationNb = (aloneLetterNb *nominator * denominator) % M;

        long test = (1*2*3*4*5*6) / ((1*2*3)*(1*2*3));


        //############## find main infos ##############
        int cpt = rangeList.size();
        for (int i = 0; i < cpt; i++) {

            Range range = rangeList.peek();
            if(rangeLongMap.containsKey(range)){
                rangeList.poll();
                continue;
            }
            int left = range.x;
            int right = range.y;

            int[] newCharNumbers = getNewCharCpt(s, leftCache, rightCache, charCpt, stringCharCode, left, right);
            long newPermutationNb = permutationNb;

            int newPairsNb = 0;
            long newNominator = nominator;
            long newDenominator = 1;
            long newAloneLetterNb = 0;
            for (int j = 0; j < newCharNumbers.length; j++) {
                int newCharNb = newCharNumbers[j];
                int oldCharNb = charNumbers[j];
                if (oldCharNb >= 2) {
                    int newPairNb = newCharNb / 2;
                    int oldPairNb = oldCharNb / 2;
                    newPairsNb += newPairNb;



                    long pairDenominator = 1;
                    if(denominatorByPairNumberIndexCache[newPairNb]==0){
                        //
                        int leftPosition = newPairNb;
                        int rightPosition = newPairNb;
                        while (leftPosition >= 0 || rightPosition <= s.length()/2) {
                            if (leftPosition >= 0) {
                                if (denominatorByPairNumberIndexCache[leftPosition] > 0) {
                                    pairDenominator = denominatorByPairNumberIndexCache[leftPosition];
                                    oldPairNb = leftPosition;
                                    break;
                                }
                                leftPosition--;
                            }
                            if (rightPosition <= s.length()/2) {
                                if (denominatorByPairNumberIndexCache[rightPosition] > 0) {
                                    pairDenominator = denominatorByPairNumberIndexCache[rightPosition];
                                    oldPairNb = rightPosition;
                                    break;
                                }
                                rightPosition++;
                            }
                        }
                        if(oldPairNb<newPairNb){
                            for (int k = oldPairNb+1; k <= newPairNb; k++) {
                                pairDenominator = (pairDenominator * (modInverse(k, M) % M)) % M;
                            }
                        }else{
                            for (int k = oldPairNb; k > newPairNb; k--) {
                                pairDenominator = (pairDenominator * k) % M;
                            }
                        }
                        denominatorByPairNumberIndexCache[newPairNb] = pairDenominator;
                    }else{
                        pairDenominator = denominatorByPairNumberIndexCache[newPairNb];
                    }
                    newDenominator = (newDenominator * pairDenominator) % M;


                }
                if (newCharNb % 2 != 0) {
                    newAloneLetterNb++;
                }
            }




            int tmpPairNb = pairsNb;
            if (nominatorByPairNumberIndexCache[newPairsNb] == 0) {
                int leftPosition = newPairsNb;
                int rightPosition = newPairsNb;
                while (leftPosition >= 0 || rightPosition < s.length()/2) {
                    if (leftPosition >= 0) {
                        if (nominatorByPairNumberIndexCache[leftPosition] > 0) {
                            newNominator = nominatorByPairNumberIndexCache[leftPosition];
                            tmpPairNb = leftPosition;
                            break;
                        }
                        leftPosition--;
                    }
                    if (rightPosition < s.length()/2) {
                        if (nominatorByPairNumberIndexCache[rightPosition] > 0) {
                            newNominator = nominatorByPairNumberIndexCache[rightPosition];
                            tmpPairNb = rightPosition;
                            break;
                        }
                        rightPosition++;
                    }
                }
                if(tmpPairNb<newPairsNb){
                    for (int j = tmpPairNb+1; j <= newPairsNb; j++) {
                        newNominator = (newNominator * j ) % M;
                    }
                }else{
                    for (int j = tmpPairNb; j > newPairsNb; j--) {
                        newNominator = (newNominator * (modInverse(j, M) % M)) % M;
                    }
                }

                nominatorByPairNumberIndexCache[newPairsNb] = newNominator;
            } else {
                newNominator = nominatorByPairNumberIndexCache[newPairsNb];
            }



//            for (int j = pairsNb; j > newPairsNb; j--) {
//                newNominator = (newNominator * (modInverse(j, M)%M))%M;
//            }
            newAloneLetterNb = Math.max(newAloneLetterNb, 1);

            newPermutationNb = (((newAloneLetterNb * newNominator)% M)*newDenominator)% M;


            rangeLongMap.put(rangeList.poll(), newPermutationNb);
        }
        return rangeLongMap;
    }


    static long __gcd(long a, long b) {

        if (b == 0) {
            return a;
        } else {
            return __gcd(b, a % b);
        }
    }

    // To compute x^y under modulo m
    static long power(long x, long y, long m) {
        if (y == 0) {
            return 1;
        }
        long p = power(x, y / 2, m) % m;
        p = (p * p) % m;

        return (y % 2 == 0) ? p : (x * p) % m;
    }

    // Function to find modular
    // inverse of a under modulo m
    // Assumption: m is prime
    static long modInverse(long a, long m) {
        if (__gcd(a, m) != 1) {
            System.out.print("Inverse doesn't exist");
        } else {
            return power(a, m - 2, m);
        }
        return -1;
    }


    private int[] getNewCharCpt(String s, int[][] leftCache, int[][] rightCache, int[] charCpt, int[] stringAlpabetIndex, int left, int right) {
        int[] charToSubstractLeft = new int[26];
        for (int i = left - 1; i >= 0; i--) {
            if (leftCache[i] != null) {
                for (int j = 0; j < leftCache[i].length; j++) {
                    charToSubstractLeft[j] += leftCache[i][j];
                }
                charToSubstractLeft[stringAlpabetIndex[i]]++;
                break;
            }
            charToSubstractLeft[stringAlpabetIndex[i]]++;
        }
        leftCache[left] = charToSubstractLeft;

        int[] charToSubstractRight = new int[26];
        for (int i = right + 1; i < s.length(); i++) {
            if (rightCache[i] != null) {
                for (int j = 0; j < rightCache[i].length; j++) {
                    charToSubstractRight[j] += rightCache[i][j];
                }
                charToSubstractRight[stringAlpabetIndex[i]]++;
                break;
            }
            charToSubstractRight[stringAlpabetIndex[i]]++;
        }
        rightCache[right] = charToSubstractRight;

        int[] newCharCpt = new int[26];
        for (int i = 0; i < 26; i++) {
            newCharCpt[i] = charCpt[i] - charToSubstractLeft[i] - charToSubstractRight[i];
        }
        return newCharCpt;
    }

    private int[] getCharCptLeftToSoustract(String s, int[][] charCptLeftToRight, int maxLeftHistoryIndex, int left) {
        int[] charCptLeft = new int[26];
        //first case, left is already cached
        if (charCptLeftToRight[left] != null) {
            charCptLeft = charCptLeftToRight[left];
        } else {
            if (maxLeftHistoryIndex == -1) {
                for (int j = 0; j < left; j++) {
                    int charAsciiCode = s.charAt(j);
                    int alphabetIndex = charAsciiCode - 122 + 25;
                    charCptLeft[alphabetIndex]++;
                }
            } else if (left < maxLeftHistoryIndex) {
                for (int j = 0; j < charCptLeftToRight[maxLeftHistoryIndex].length; j++) {
                    charCptLeft[j] = charCptLeftToRight[maxLeftHistoryIndex][j];
                }
                for (int j = maxLeftHistoryIndex; j >= left; j--) {
                    int charAsciiCode = s.charAt(j);
                    int alphabetIndex = charAsciiCode - 122 + 25;
                    charCptLeft[alphabetIndex]--;
                }
                charCptLeftToRight[left] = charCptLeft;
            } else if (left > maxLeftHistoryIndex) {
                for (int j = 0; j < charCptLeftToRight[left].length; j++) {
                    charCptLeft[j] = charCptLeftToRight[left][j];
                }
                for (int j = maxLeftHistoryIndex + 1; j < left; j++) {
                    int charAsciiCode = s.charAt(j);
                    int alphabetIndex = charAsciiCode - 122 + 25;
                    charCptLeft[alphabetIndex]++;
                }
                charCptLeftToRight[left] = charCptLeft;
            }
        }
        return charCptLeft;
    }

    private int[] getCharCptRightToSoustract(String s, int[][] charCptRightToLeft, int maxRightHistoryIndex, int right) {
        int[] charCptRight = new int[26];

        //first case, left is already cached
        if (charCptRightToLeft[right] != null) {
            charCptRight = charCptRightToLeft[right];
        } else {
            if (maxRightHistoryIndex == -1) {
                for (int j = s.length() - 1; j > right; j--) {
                    int charAsciiCode = s.charAt(j);
                    int alphabetIndex = charAsciiCode - 122 + 25;
                    charCptRight[alphabetIndex]++;
                }
            } else if (right > maxRightHistoryIndex) {
                for (int j = 0; j < charCptRightToLeft[maxRightHistoryIndex].length; j++) {
                    charCptRight[j] = charCptRightToLeft[maxRightHistoryIndex][j];
                }
                for (int j = maxRightHistoryIndex; j <= right; j++) {
                    int charAsciiCode = s.charAt(j);
                    int alphabetIndex = charAsciiCode - 122 + 25;
                    charCptRight[alphabetIndex]--;
                }
                charCptRightToLeft[right] = charCptRight;
            } else if (right < maxRightHistoryIndex) {
                for (int j = 0; j < charCptRightToLeft[right].length; j++) {
                    charCptRight[j] = charCptRightToLeft[right][j];
                }
                for (int j = maxRightHistoryIndex + 1; j > right; j--) {
                    int charAsciiCode = s.charAt(j);
                    int alphabetIndex = charAsciiCode - 122 + 25;
                    charCptRight[alphabetIndex]++;
                }
                charCptRightToLeft[right] = charCptRight;
            }
        }
        return charCptRight;
    }


    public Map<Range, Long> initialize2(String s, Queue<Range> ranges) {
        Map<Range, Long> result = new HashMap<>();
        int[] charCpt = new int[26];
        int[] previousCharCpt = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int charAsciiCode = s.charAt(i);
            int alphabetIndex = charAsciiCode - 122 + 25;
            charCpt[alphabetIndex] = 0;
            previousCharCpt[alphabetIndex] = 0;
        }
        long pairsNb = 0;
        long aloneLetterNb = 0;
//        for (int charNb : charCpt) {
//            if (charNb >= 2) {
//                pairsNb += charNb / 2;
//            }
//            if (charNb % 2 != 0) {
//                aloneLetterNb++;
//            }
//        }


        int cpt = 0;

        int previousX = 0;
        int previousY = 0;
        long permutationNb = 1;
        while (ranges.isEmpty() == false) {
            Range r = ranges.peek();
            if (result.containsKey(r)) {
                ranges.poll();
                continue;
            }

            for (int i = 0; i < charCpt.length; i++) {
                charCpt[i] = 0;
            }
            for (int i = r.x; i < r.y; i++) {
                int charAsciiCode = s.charAt(i);
                int alphabetIndex = charAsciiCode - 122 + 25;
                charCpt[alphabetIndex]++;
            }


            aloneLetterNb = 0;
            for (int i = 0; i < charCpt.length; i++) {

                long previousCharNumber = previousCharCpt[i];
                long currentCharNumber = charCpt[i];
                if (currentCharNumber >= 2 || previousCharNumber >= 2) {
                    long previousCharPairNb = previousCharNumber / 2;
                    long currentCharPairNb = currentCharNumber / 2;
                    if (currentCharPairNb > previousCharPairNb) {

                        for (long j = previousCharNumber + 1; j <= currentCharNumber; j++) {
                            permutationNb *= j;
                            if (permutationNb > M) {
                                permutationNb = permutationNb % M;
                            }
                        }
                        for (long j = previousCharPairNb + 1; j <= currentCharPairNb; j++) {
                            permutationNb /= j;
                        }

                    } else if (previousCharPairNb > currentCharPairNb) {
                        for (long j = previousCharNumber; j > currentCharNumber; j--) {
                            permutationNb /= j;
                        }
                        for (long j = previousCharPairNb; j > currentCharPairNb; j--) {
                            permutationNb *= j;
                            if (permutationNb > M) {
                                permutationNb = permutationNb % M;
                            }
                        }
                    }
                }

                if (currentCharNumber % 2 != 0) {
                    aloneLetterNb++;
                }
            }


            for (int i = 0; i < charCpt.length; i++) {
                previousCharCpt[i] = charCpt[i];
            }


//            System.out.println("x:" + r.x + " y:" + r.y + ", permutationNb: " + permutationNb * aloneLetterNb);
            result.put(r, permutationNb);
            ranges.poll();
        }
        System.out.println(s.length());
        return result;
    }

    /*
     * Complete the 'initialize' function below.
     *
     * The function accepts STRING s as parameter.
     */

    public Map<Range, Long> initialize1(String s, Queue<Range> rangeList) {
        // This function is called once before all queries.
        Map<Range, Long> result = new HashMap<>();
        int[] charCpt = new int[26];
        int midRange = s.length() / 2;
        boolean center = s.length() % 2 != 0;


        long permutationNb = 1;
        long previousPairNb = 0;

        int[] previousCharCpt = new int[26];


        int previousLeftCharIndex = -1;
        int previousRightCharIndex = -1;

        int previousLeft = -1;
        int previousRight = -1;

        int iLeft = midRange;
        int iRight = midRange + 1;
        char cLeft = s.charAt(iLeft);
        char cRight = s.charAt(iRight);

        int leftIndex = cLeft - 122 + 25;
        int rightIndex = cRight - 122 + 25;

        previousLeftCharIndex = leftIndex;
        previousRightCharIndex = rightIndex;

        int left = iLeft;
        int right = iRight;

        previousLeft = left;
        previousRight = right;


        charCpt[leftIndex]++;
        charCpt[rightIndex]++;
        long permutationDenominator = 0;
        int cpt = 0;
        while (rangeList.isEmpty() == false) {
            Range currentRange = rangeList.peek();
            cpt++;


            long targetLeft = currentRange.getX();
            long targetRight = currentRange.getY();


            left = iLeft;
            right = iRight;

//            System.out.println(" left :" + left+  "right :" + right);

            if (targetLeft < left) {
                iLeft--;
            } else if (targetLeft > left) {
                iLeft++;
            }

            if (targetRight > right) {
                iRight++;
            } else if (targetRight < right) {
                iRight--;
            }
            left = iLeft;
            right = iRight;

            cLeft = s.charAt(iLeft);
            cRight = s.charAt(iRight);

            leftIndex = cLeft - 122 + 25;
            rightIndex = cRight - 122 + 25;

            setCharCpt(charCpt, previousLeftCharIndex, previousRightCharIndex, previousLeft, previousRight, leftIndex, rightIndex, left, right, targetLeft, targetRight);


            long pairsNb = 0;
            long aloneLetterNb = 0;
            for (int charNb : charCpt) {
                if (charNb >= 2) {
                    pairsNb += charNb / 2;
                }
                if (charNb % 2 != 0) {
                    aloneLetterNb++;
                }
            }
            permutationNb = getPermutationNb(charCpt, permutationNb, previousPairNb, previousCharCpt, previousLeftCharIndex, previousRightCharIndex, leftIndex, rightIndex, pairsNb);


            previousPairNb = pairsNb;


            previousLeftCharIndex = leftIndex;
            previousRightCharIndex = rightIndex;

            previousLeft = left;
            previousRight = right;


            if (targetLeft == left && targetRight == right) {
                if (addResult(rangeList, result, permutationNb, currentRange, aloneLetterNb)) {
                    break;
                }
            }


        }
        return result;
    }


    private void setCharCpt(int[] charCpt, int previousLeftCharIndex, int previousRightCharIndex, int previousLeft, int previousRight, int leftIndex, int rightIndex, int left, int right, long targetLeft, long targetRight) {
        if (targetLeft < left || (targetLeft == left && (previousLeft > targetLeft || previousLeft == -1))) {
            charCpt[leftIndex]++;
        } else if (previousLeft != -1 && (targetLeft > left || (targetLeft == left && previousLeft < left))) {
            charCpt[previousLeftCharIndex]--;
            if (left == right && (targetRight > right || (targetRight == right && (previousRight < targetRight || previousRight == -1)))) {
                charCpt[rightIndex]++;
            }
        }

        if ((left != right) && (targetRight > right || (targetRight == right && (previousRight < targetRight || previousRight == -1)))) {
            charCpt[rightIndex]++;

        } else if (previousLeft != -1 && (left != right && (targetRight < right || (targetRight == right && previousRight > right)))) {
            charCpt[previousRightCharIndex]--;

        } else if (left == right && (targetRight < right || (targetRight == right && previousRight > right))) {
            charCpt[previousRightCharIndex]--;
        }
    }

    private boolean addResult(Queue<Range> rangeList, Map<Range, Long> result, long permutationNb, Range currentRange, long aloneLetterNb) {
        rangeList.poll();
//        System.out.println("---------------------");
        long finalResult = Math.max(aloneLetterNb, 1) * Math.max(permutationNb, 1);
        result.put(currentRange, finalResult);

        if (rangeList.isEmpty()) {
            return true;
        }

        return false;
    }

    private long getPermutationNb(int[] charCpt, long permutationNb, long previousPairNb, int[] previousCharCpt, int previousLeftCharIndex, int previousRightCharIndex, int leftIndex, int rightIndex, long pairsNb) {
        if (permutationNb == 0 && pairsNb > 0) {
            permutationNb = 1;
        }
        permutationNb = multiplicateOrDividePermutationNumber(charCpt, permutationNb, previousPairNb, previousCharCpt, previousLeftCharIndex, previousRightCharIndex, pairsNb);


        permutationNb = getPermutationNb(charCpt, permutationNb, previousCharCpt, leftIndex);


        permutationNb = getPermutationNb(charCpt, permutationNb, previousCharCpt, rightIndex);
        return permutationNb;
    }

    private long multiplicateOrDividePermutationNumber(int[] charCpt, long permutationNb, long previousPairNb, int[] previousCharCpt, int previousLeftCharIndex, int previousRightCharIndex, long pairsNb) {
        if (pairsNb > previousPairNb) {
            for (long j = previousPairNb + 1; j <= pairsNb; j++) {
                permutationNb *= j;
                if (permutationNb > M) {
                    permutationNb = permutationNb % M;
                }
            }
        }

        permutationNb = getPermutationNb(charCpt, permutationNb, previousCharCpt, previousLeftCharIndex);
        permutationNb = getPermutationNb(charCpt, permutationNb, previousCharCpt, previousRightCharIndex);


        if (previousPairNb > pairsNb) {
            for (long j = previousPairNb; j > pairsNb; j--) {
                permutationNb /= j;
            }
        }
        return permutationNb;
    }


    private static BigInteger getPermutationNb(int[] charCpt, BigInteger permutationNb, int[] previousCharCpt, int charIndex) {
        long previousCharNumber = previousCharCpt[charIndex];
        long currentCharNumber = charCpt[charIndex];
        if (currentCharNumber >= 2 || previousCharNumber >= 2) {
            long previousCharPairNb = previousCharNumber / 2;
            long currentCharPairNb = currentCharNumber / 2;
            if (currentCharPairNb > previousCharPairNb) {
                permutationNb = permutationNb.divide(BigInteger.valueOf(currentCharPairNb));
            } else if (previousCharPairNb > currentCharPairNb) {
                permutationNb = permutationNb.divide(BigInteger.valueOf(previousCharNumber));
            }
        }
        previousCharCpt[charIndex] = charCpt[charIndex];
        return permutationNb;
    }

    private static long getPermutationNb(int[] charCpt, long permutationNb, int[] previousCharCpt, int charIndex) {
        long previousCharNumber = previousCharCpt[charIndex];
        long currentCharNumber = charCpt[charIndex];
        if (currentCharNumber >= 2 || previousCharNumber >= 2) {
            long previousCharPairNb = previousCharNumber / 2;
            long currentCharPairNb = currentCharNumber / 2;
            if (currentCharPairNb > previousCharPairNb) {
                permutationNb /= currentCharPairNb;
            } else if (previousCharPairNb > currentCharPairNb) {
                permutationNb *= previousCharPairNb;
            }
        }
        previousCharCpt[charIndex] = charCpt[charIndex];
        return permutationNb;
    }

    /*
     * Complete the 'answerQuery' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER l
     *  2. INTEGER r
     */


}

class Range {
    public final int x;
    public final int y;

    public Range(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        Range other = (Range) obj;
        if (other.x == x && other.y == y) {
            return true;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return (31 * (x + y));
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
         BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();
        Result result = new Result();


        int q = Integer.parseInt(bufferedReader.readLine().trim());

        long midRange = s.length() / 2;
        PriorityQueue<Range> rangeList = new PriorityQueue<>((p1, p2) -> {
            return (p2.x + s.length() - p2.y) - (p1.x + s.length() - p1.y);
        });
        Queue<Range> rangeList2 = new LinkedList<>();
        List<Range> originalList = new ArrayList<>();
        IntStream.range(0, q).forEach(qItr -> {

            try {
                String[] firstMultipleInput;
                firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");


                int l = Integer.parseInt(firstMultipleInput[0]);

                int r = Integer.parseInt(firstMultipleInput[1]);
                Range range = new Range(l - 1, r - 1);
                rangeList.add(range);
                originalList.add(range);
                rangeList2.add(range);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        for (int i = 0; i < rangeList.size(); i++) {
            Range range = rangeList.peek();

        }

        Map<Range, Long> rangeLongMap = result.initialize(s, rangeList2);

        originalList.forEach(r -> {

            try {
                bufferedWriter.write(String.valueOf(rangeLongMap.get(r)));
                bufferedWriter.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        });


        bufferedReader.close();
        bufferedWriter.close();
    }
}
