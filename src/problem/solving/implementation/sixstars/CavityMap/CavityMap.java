package problem.solving.implementation.sixstars.CavityMap;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class CavityMap {
    /*
     * Complete the 'cavityMap' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING_ARRAY grid as parameter.
     */

    public static char[][] cavityMap(List<String> grid) {

        List<String> newGrid = grid.stream().collect(Collectors.toList());
        char[][] newString = convertToCharArray(newGrid);

        int[][] matrice = convertToIntArray(grid);

        for (int i = 1; i < matrice.length - 1; i++) {
            int[] row = matrice[i];
            for (int j = 1; j < row.length - 1; j++) {
                int cell = row[j];
                if (matrice[i - 1][j] < cell) {
                    if (matrice[i + 1][j] < cell) {
                        if (matrice[i][j - 1] < cell) {
                            if (matrice[i][j + 1] < cell) {
                                newString[i][j] = 'X';
                            }
                        }
                    }
                }
            }
        }
        return newString;
    }

    private static int[][] convertToIntArray(List<String> grid) {
        int[][] m = new int[grid.size()][grid.get(0).length()];
        for (int i = 0; i < grid.size(); i++) {
            String value = grid.get(i);
            for (int j = 0; j < value.length(); j++) {
                int digit = Integer.parseInt(value.substring(j, j + 1));
                m[i][j] = digit;
            }
        }
        return m;
    }

    private static char[][] convertToCharArray(List<String> newGrid) {
        char[][] newString = new char[newGrid.size()][newGrid.get(0).length()];
        for (int i = 0; i < newGrid.size(); i++) {
            for (int j = 0; j < newGrid.get(i).length(); j++) {
                newString[i][j] = newGrid.get(i).charAt(j);
            }
        }
        return newString;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> grid = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        char[][] newString = CavityMap.cavityMap(grid);

        for (char[] chars : newString) {
            for (int j = 0; j < chars.length; j++) {
                bufferedWriter.write(chars[j]);
            }
            bufferedWriter.write('\n');

        }


        bufferedReader.close();
        bufferedWriter.close();
    }
}
