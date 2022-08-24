package problem.solving.implementation.sixstars;

import java.io.*;

class TheTimeInWords {

    /*
     * Complete the 'timeInWords' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER h
     *  2. INTEGER m
     */
    public static final String[] numbers = {
            "zero",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven"
    };

    public static final String[] between10and20 = {
            "ten",
            "eleven",
            "twelve",
            "thirteen",
            "fourteen",
            "fiveteen",
            "sixteen",
            "seventeen",
            "eighteen",
            "nineteen"
    };

    public static final String[] decimals = {

            "twenty",
            "thirty",
            "fourty",
            "fifty",
            "sixty",
            "seventy",
            "eighty",
            "ninety"
    };


    public static String timeInWords(int h, int m) {
        // Write your code here

        String first = "";
        String intermediate = "";
        String second = "";

        if (m == 0) {
            second = " o' clock";
            return numbers[h] + second;
        }

        if (m <= 30) {
            intermediate = " past ";
        } else if (m > 30) {
            intermediate = " to ";
            m = 60 - m;
            h++;
        }

        if(m != 15 && m != 30) {
            String minuteWord = " minute";
            if (m != 1) {
                minuteWord += "s";
            }
            intermediate = minuteWord + intermediate;
        }



        if (m == 15) {
            first = "quarter";
        } else if (m == 30) {
            first = "half";
        } else if (m < 10) {
            first = numbers[m];
        } else if (m < 20) {
            first = between10and20[m - 10];
        } else {
            int secondMinuteNumber = m % 10;
            int firstMinuteNumber = (m / 10) % 10;

            first += decimals[firstMinuteNumber - 2];
            first += " ";
            first += numbers[secondMinuteNumber];
        }

        second = numbers[h];


        return first + intermediate + second;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int h = Integer.parseInt(bufferedReader.readLine().trim());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        String result = TheTimeInWords.timeInWords(h, m);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
