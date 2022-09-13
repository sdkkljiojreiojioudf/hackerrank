package problem.solving.implementation.sixstars;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MatrixRotation {

    /*
     * Complete the 'matrixRotation' function below.
     *
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY matrix
     *  2. INTEGER r
     */

    public static void matrixRotation(List<List<Integer>> matrix, int r) {
        // Write your code here
        int rowNumber = matrix.size();
        int colNumber = matrix.get(0).size();
        int[][] array = new int[rowNumber][colNumber];
        for (int i = 0; i < rowNumber; i++) {
            List<Integer> row = matrix.get(i);
            array[i] = new int[row.size()];
            for (int j = 0; j < row.size(); j++) {
                array[i][j] = row.get(j);
            }
        }
        int[][] newArray =  new int[rowNumber][colNumber];
        for (int i = 0; i < rowNumber; i++) {
            List<Integer> row = matrix.get(i);
            newArray[i] = new int[row.size()];
            for (int j = 0; j < row.size(); j++) {
                newArray[i][j] = row.get(j);
            }
        }

        int outskirtsRectangleNumber = Math.min(rowNumber, colNumber) / 2;

        int totalCells = (rowNumber) * 2 + (colNumber - 2) * 2;


        Coordinate[][] rectangleOutskirtCellCoordinates = new Coordinate[outskirtsRectangleNumber][totalCells];

        int[][] outskirtCellValue = new int[rowNumber][colNumber];

        for (int i = 0; i < outskirtsRectangleNumber; i++) {

            int colMinIndex = i;
            int colMaxIndex = colNumber - i;

            int rowMinIndex = i;
            int rowMaxIndex = rowNumber - i;

            totalCells = (rowMaxIndex - rowMinIndex) * 2 + (colMaxIndex - colMinIndex - 2) * 2;


            int cellNumberBeforeBottomLeftCorner = (rowMaxIndex - rowMinIndex) - 1;
            int cellNumberBeforeBottomRightCorner = (totalCells / 2);
            int cellNumberBeforeTopRightCorner = cellNumberBeforeBottomRightCorner + cellNumberBeforeBottomLeftCorner;


            int rowIndex = rowMinIndex;
            int colIndex = colMinIndex;

            int cpt = 0;

            while (cpt < totalCells) {
                Coordinate newCoordinate = new Coordinate(rowIndex, colIndex);
                outskirtCellValue[rowIndex][colIndex] = cpt;

                if (cpt < cellNumberBeforeBottomLeftCorner) {
                    rowIndex++;
                } else if (cpt < cellNumberBeforeBottomRightCorner) {
                    colIndex++;
                } else if (cpt < cellNumberBeforeTopRightCorner) {
                    rowIndex--;
                } else {
                    colIndex--;
                }

                rectangleOutskirtCellCoordinates[i][cpt] = newCoordinate;

                cpt++;
            }
            cpt = 0;
             rowIndex = rowMinIndex;
             colIndex = colMinIndex;
            int trueRotationNumber = r % totalCells;
            while (cpt < totalCells) {

                int startingCellNumber = outskirtCellValue[rowIndex][colIndex];
                int newCellNumber = startingCellNumber + trueRotationNumber;
                if (newCellNumber >= totalCells) {
                    newCellNumber = newCellNumber - totalCells;
                }
                Coordinate coordinates = rectangleOutskirtCellCoordinates[i][newCellNumber];
                newArray[coordinates.x][coordinates.y] = array[rowIndex][colIndex];

                if (cpt < cellNumberBeforeBottomLeftCorner) {
                    rowIndex++;
                } else if (cpt < cellNumberBeforeBottomRightCorner) {
                    colIndex++;
                } else if (cpt < cellNumberBeforeTopRightCorner) {
                    rowIndex--;
                } else {
                    colIndex--;
                }
                cpt++;

            }

        }


        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < colNumber; j++) {
                System.out.print(newArray[i][j] +" ");
            }
            System.out.println();
        }
    }

    private static class Coordinate {
        private final int x;
        private final int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(firstMultipleInput[0]);

        int n = Integer.parseInt(firstMultipleInput[1]);

        int r = Integer.parseInt(firstMultipleInput[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        MatrixRotation.matrixRotation(matrix, r);

        bufferedReader.close();
    }
}
