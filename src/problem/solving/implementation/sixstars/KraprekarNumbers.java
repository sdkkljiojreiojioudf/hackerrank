package problem.solving.implementation.sixstars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class KaprekarNumbers {

    /*
     * Complete the 'kaprekarNumbers' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER p
     *  2. INTEGER q
     */

    public static void kaprekarNumbers(int p, int q) {


        int digitNumber = getDigitNumber(p);

        double limitForNextDigitNumber = getLimitForNextDigitNumber(digitNumber);

        List<String> kaprekarNumbers = new ArrayList<>();

        //1: iterate from p (lower limitForNextDigitNumber) to q (upper limitForNextDigitNumber)
        for (int i = p; i <= q; i++) {

            if(i==(limitForNextDigitNumber)){
                digitNumber++;
                limitForNextDigitNumber = getLimitForNextDigitNumber(digitNumber);
            }
            //1: square i
            double n = Math.pow(i, 2);

            //2 : i is is d digits
            //    n is  2 x d digits  or  (2 x d) -1 digits
            //   imagine the string representation s of n
            //   the right hand part,r must be d digits
            double divisor = Math.pow(10, digitNumber);
            double rightPart = n % divisor;
            double leftPart = (n - rightPart) / divisor;


            // 3 : calculate the sum of the right and the left parts
            double sum = rightPart + leftPart;
            if(sum==i){
                kaprekarNumbers.add(String.valueOf(i));
            }

        }

        if(kaprekarNumbers.isEmpty()){
            System.out.println("INVALID RANGE");
        }else{
            System.out.println(String.join(" ", kaprekarNumbers));
        }
    }

    private static double getLimitForNextDigitNumber(int digitNumber) {
        return Math.pow(10, digitNumber);
    }

    private static int getDigitNumber(int p) {
        int digitNumber = 1;
        int p2 = p;
        while(p2 >= 10){
            p2 = p2 / 10;
            digitNumber++;
        }
        return digitNumber;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int p = Integer.parseInt(bufferedReader.readLine().trim());

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        KaprekarNumbers.kaprekarNumbers(p, q);

        bufferedReader.close();
    }
}
