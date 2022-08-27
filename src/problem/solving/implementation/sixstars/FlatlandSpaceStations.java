package problem.solving.implementation.sixstars;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FlatlandSpaceStations {


    // Complete the flatlandSpaceStations function below.
    static int flatlandSpaceStations(int n, int[] c) {

        // there is a spatial station in every citiy
        if(c.length == n){
            return 0;
        }

        Arrays.sort(c);

        int maximumDistanceBetweenSpaceStations = c[0];
        for (int i = 0; i < c.length-1; i++) {
            int firstSpaceStationIndex = c[i];
            int secondSpaceStationIndex = c[i+1];
            int distance = secondSpaceStationIndex - firstSpaceStationIndex;
            if(secondSpaceStationIndex - firstSpaceStationIndex > 1){
                distance = distance / 2;
            }
            maximumDistanceBetweenSpaceStations = Math.max(maximumDistanceBetweenSpaceStations, distance);
        }
        int distanceBetweenLastStationAndLastCity = (n-1) - c[c.length-1];
        if(distanceBetweenLastStationAndLastCity > maximumDistanceBetweenSpaceStations){
            maximumDistanceBetweenSpaceStations = distanceBetweenLastStationAndLastCity ;
        }

        return maximumDistanceBetweenSpaceStations;

    }


    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[] c = new int[m];

        String[] cItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        for (int i = 0; i < m; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = flatlandSpaceStations(n, c);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
