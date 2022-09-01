package problem.solving.implementation.sixstars.surfaceArea;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SurfaceArea2 {

    /*
     * Complete the 'surfaceArea' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY A as parameter.
     */

    public static int surfaceArea(List<List<Integer>> A, int H, int W) {
        int count=0;

        int[][] arr = new int[A.size()][A.get(0).size()];
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < A.get(i).size(); j++) {
                arr[i][j] = A.get(i).get(j);
            }
        }

        int columnLength = arr.length;
        int rowLength = arr[0].length;

        for(int i=0;i<H;i++)
        {
            for(int j=0;j<W;j++)
            {
                if(i==0)
                    count=count+arr[i][j];
                else if(i> 0 && arr[i][j]>arr[i-1][j])
                    count+=(arr[i][j]-arr[i-1][j]);

                if(j==0)
                    count=count+arr[i][j];
                else if(j > 0 && arr[i][j]>arr[i][j-1])
                    count+=(arr[i][j]-arr[i][j-1]);

                if(i==(H-1))
                    count=count+arr[i][j];
                else if(i < rowLength -1 && arr[i][j]>arr[i+1][j])
                    count+=(arr[i][j]-arr[i+1][j]);

                if(j==(W+1))
                    count=count+arr[i][j];
                else if(j < columnLength - 1 && arr[i][j]>arr[i][j+1])
                    count+=(arr[i][j]-arr[i][j+1]);

                count++;
                count++;
            }
        }
        return count;
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

        int result = SurfaceArea2.surfaceArea(A, H, W);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
