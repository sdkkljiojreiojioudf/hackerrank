package problem.solving.implementation;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Kangaroo {

    // Complete the kangaroo function below.
    static String kangaroo(int x1, int v1, int x2, int v2) {
        if (v1 == v2 && x2 != x1) {
            return "NO";
        }
        int nb_of_turn_to_cross = (x2 - x1) / (v1 - v2);
        int kang_1_test = v1 * nb_of_turn_to_cross + x1;
        int kang_2_test = v2 * nb_of_turn_to_cross + x2;
        boolean same_jumb_nb = kang_1_test == kang_2_test;
        boolean intersection = nb_of_turn_to_cross > 0;
        return same_jumb_nb && intersection ? "YES" : "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String[] x1V1X2V2 = scanner.nextLine().split(" ");

        int x1 = Integer.parseInt(x1V1X2V2[0]);

        int v1 = Integer.parseInt(x1V1X2V2[1]);

        int x2 = Integer.parseInt(x1V1X2V2[2]);

        int v2 = Integer.parseInt(x1V1X2V2[3]);

        String result = kangaroo(x1, v1, x2, v2);


        scanner.close();
    }
}
