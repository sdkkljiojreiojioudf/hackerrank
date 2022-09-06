package problem.solving.implementation.sixstars.bomberman;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

 class Result {
    /*
     * Complete the 'bomberMan' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING_ARRAY grid
     */

     public static List<String> bomberMan(int n, List<String> grid) {
         // Write your code here
         grid = grid.stream().filter(e->e != null).collect(toList());

         int rowNb = grid.size();
         int colNb = grid.get(0).length();

         int[][] bombMatrice = new int[grid.size()][grid.get(0).length()];
         for (int i = 0; i < rowNb; i++) {
             String states = grid.get(i);
             for (int j = 0; j < colNb; j++) {
                 char c = states.charAt(j);
                 if(c=='O'){
                     bombMatrice[i][j] = 1;
                 }else{
                     bombMatrice[i][j] = 0;
                 }
             }
         }



         int[] bombsCounter = new int[2];
         bombsCounter[0] = 1;
         bombsCounter[1] = -1;

         Map<Integer, int[][]> matriceCache = new HashMap<>();

         //
         int cpt = 1;

         while(cpt < 7 && cpt <= n) {
             for (int i = 0; i < bombsCounter.length; i++) {
                 int bombCounter = bombsCounter[i];

                 if (bombCounter == 0) {
                     //adding bomb of i index
                     for (int j = 0; j < rowNb; j++) {
                         for (int k = 0; k < colNb; k++) {
                             int cell = bombMatrice[j][k];
                             if (cell == 0) {
                                 bombMatrice[j][k] = i + 1;
                             }
                         }
                     }
                 } else if (bombCounter == 3) {
                     bombsCounter[i] = -1;
                     //BOOM
                     for (int j = 0; j < rowNb; j++) {
                         for (int k = 0; k < colNb; k++) {
                             int cell = bombMatrice[j][k];
                             if (cell == i + 1) {
                                 bombMatrice[j][k] = 0;
                                 if (j > 0 && bombMatrice[j - 1][k] != i+1) {
                                     bombMatrice[j - 1][k] = 0;
                                 }
                                 if (j < rowNb - 1 && bombMatrice[j + 1][k] != i+1) {
                                     bombMatrice[j + 1][k] = 0;
                                 }
                                 if (k > 0 && bombMatrice[j][k - 1] != i+1) {
                                     bombMatrice[j][k - 1] = 0;
                                 }
                                 if (k < colNb - 1 && bombMatrice[j][k + 1] != i+1) {
                                     bombMatrice[j][k + 1] = 0;
                                 }
                             }
                         }
                     }
                 }
                 bombsCounter[i]++;
             }
             if(cpt==3 || cpt==4 || cpt==5 || cpt==6){
                 int[][] matriceCached = new int[rowNb][colNb];
                 for (int i = 0; i < rowNb; i++) {
                     for (int j = 0; j < colNb; j++) {
                         matriceCached[i][j] = bombMatrice[i][j];
                     }
                 }
                 matriceCache.put(cpt-3, matriceCached);
             }
             cpt++;
         }



         if(n>=3) {
             int cacheIndex = (n-3) % 4;
             bombMatrice = matriceCache.get(cacheIndex);
         }

         List<String> result = new ArrayList<>();
         for (int i = 0; i < rowNb; i++) {
             String row ="";
             for (int j = 0; j < colNb; j++) {
                 int cell = bombMatrice[i][j];
                 if(cell>0){
                     row += 'O';
                 }else{
                     row += '.';
                 }
             }
             result.add(row);
         }
         return result;


     }


}
 public class BomberMan{
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r = Integer.parseInt(firstMultipleInput[0]);

        int c = Integer.parseInt(firstMultipleInput[1]);

        int n = Integer.parseInt(firstMultipleInput[2]);

        List<String> grid = IntStream.range(0, r).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        List<String> result = Result.bomberMan(n, grid);

        bufferedWriter.write(
                result.stream()
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
