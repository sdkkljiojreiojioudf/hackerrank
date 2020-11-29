package problem.solving.implementation;

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

public class DayOfTheProgrammer {
    // Complete the dayOfProgrammer function below.
    static String dayOfProgrammer(int year) {
        //Januart -> 31
        //March -> 31
        //
        int month = 9;
        int day = 13;
         if (year < 1919) {
            if (year % 4 == 0) {
                day -= 1;
            }else if(year==1918){
                day += 13;
            }
        }
        else if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
            day -= 1;
        }
        return day + ".0" + month + "." + year;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        int year = Integer.parseInt(bufferedReader.readLine().trim());

        String result = dayOfProgrammer(year);


        bufferedReader.close();

    }
}
