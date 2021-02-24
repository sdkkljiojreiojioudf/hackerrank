package problem.solving.implementation;


import java.io.*;
        import java.math.*;
        import java.security.*;
        import java.text.*;
        import java.util.*;
        import java.util.concurrent.*;
        import java.util.regex.*;
import java.util.stream.Collectors;


public class BeautifulDaysAtTheMovies {

    static int reverseNumber(int n){
        int reversed = 0;
        for (int i = 0; i < 100; i++) {
            if(n <1){
                break;
            }
            int rest = n % 10;
            reversed = reversed * 10 + rest;
            n /= 10;

        }
        return reversed;
    }

    // Complete the beautifulDays function below.
    static int beautifulDays(int i, int j, int k) {
        int cpt = 0;
        for(int x = i; x <= j; x++){
            boolean test = Math.abs(x - reverseNumber(x)) % k == 0;
            if(test){
                cpt ++;
            }
        }
        return cpt;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] ijk = scanner.nextLine().split(" ");

        int i = Integer.parseInt(ijk[0]);

        int j = Integer.parseInt(ijk[1]);

        int k = Integer.parseInt(ijk[2]);

        int result = beautifulDays(i, j, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

