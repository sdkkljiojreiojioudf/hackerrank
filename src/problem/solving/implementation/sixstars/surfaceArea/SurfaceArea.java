package problem.solving.implementation.sixstars.surfaceArea;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SurfaceArea {

    /*
     * Complete the 'surfaceArea' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY A as parameter.
     */

    public static int surfaceArea(List<List<Integer>> A) {
        int area = 0;

        int[][] cub = new int[A.size()][A.get(0).size()];
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < A.get(i).size(); j++) {
                cub[i][j] = A.get(i).get(j);
            }
        }


        int rowLength = cub[0].length;
        int columnLength = cub.length;


        //area of cub face oriented from east and west of matrice
        for (int[] row : cub) {
            area += row[0];
            area += row[rowLength-1];
            for (int j = 1; j < row.length; j++) {
                if (row[j] > row[j - 1]) {
                    area += row[j] - row[j-1];
                }
                if (row[j-1] > row[j]) {
                    area += row[j-1] -row[j] ;
                }
            }
        }

        // area of cub faces oriented from north and south of matrice
        for (int i = 0; i < rowLength; i++) {
            area += cub[0][i];
            area += cub[columnLength-1][i];
            for (int j = 1; j < columnLength; j++) {
                if(cub[j][i] > cub[j-1][i]){
                    area += cub[j][i] - cub[j-1][i];
                }
                if(cub[j-1][i] > cub[j][i]){
                    area += cub[j-1][i] - cub[j][i];
                }
            }
        }


        //area represented by object base
        int objectBaseArea = cub.length * cub[0].length;
        area += objectBaseArea * 2;


        return area;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int H = Integer.parseInt(firstMultipleInput[0]);

        int W = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> A = new ArrayList<>();

        IntStream.range(0, H).forEach(i -> {
            try {
                A.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = SurfaceArea.surfaceArea(A);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
