package problem.solving.implementation.sixstars;

import java.io.*;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class TheGridSearch {
    /*
     * Complete the 'gridSearch' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING_ARRAY G
     *  2. STRING_ARRAY P
     */

    public static String gridSearch(List<String> G, List<String> P) {
        // Write your code here
        char[][] g = convertToCharArray(G);
        char[][] p = convertToCharArray(P);
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length; j++) {
                //check if we match the first cell of the pattern matrice
                if (g[i][j] == p[0][0]) {

                    if(p.length + i > g.length || p[0].length + j > g[i].length){
                        continue;
                    }

                    int i_tmp = i;
                    int j_tmp = j;
                    boolean matching = true;
                    //iterate on all cell of the pattern matrice
                    patternLoop:
                    for (int k = 0; k < p.length && i_tmp < g.length; k++, i_tmp++) {
                        j_tmp = j;
                        for (int l = 0; l < p[k].length && j_tmp < g[i].length; l++, j_tmp++) {
                            if (p[k][l] != g[i_tmp][j_tmp]) {
                                matching = false;
                                continue patternLoop;
                            }
                        }
                    }

                    if (matching) {
                        return "YES";
                    }


                }
            }
        }

        return "NO";
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

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int R = Integer.parseInt(firstMultipleInput[0]);

                int C = Integer.parseInt(firstMultipleInput[1]);

                List<String> G = IntStream.range(0, R).mapToObj(i -> {
                            try {
                                return bufferedReader.readLine();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        })
                        .collect(toList());

                String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int r = Integer.parseInt(secondMultipleInput[0]);

                int c = Integer.parseInt(secondMultipleInput[1]);

                List<String> P = IntStream.range(0, r).mapToObj(i -> {
                            try {
                                return bufferedReader.readLine();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        })
                        .collect(toList());

                String result = TheGridSearch.gridSearch(G, P);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
