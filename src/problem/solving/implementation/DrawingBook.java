package problem.solving.implementation;

import java.io.*;
import java.util.*;


public class DrawingBook {
    /*
     * Complete the pageCount function below.
     */
    static int pageCount(int n, int p) {
        /*
         * Write your code here.
         */
        int diffFromEnd = n - p;
        int pageNb;
        if (diffFromEnd < p) {
            pageNb = (n - p);
            if (pageNb == 0) {
                return 0;
            } else {
                pageNb /= 2;
            }
            if (n % 2 == 0 && p%2!=0) {
                pageNb += 1;
            }
        } else {
            pageNb = (p - 1);
            if (pageNb == 0) {
                return 0;
            } else {
                pageNb /= 2;
            }
            if (p % 2 == 0) {
                pageNb += 1;
            }
        }

        return pageNb;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int p = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");




        scanner.close();
    }
}
