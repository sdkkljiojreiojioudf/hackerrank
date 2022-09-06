package problem.solving.implementation.sixstars.TwoPlus;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class TwoPlus {
    public static int twoPluses(List<String> grid) {
        // Write your code here

        int rowNb = grid.size();
        int colNb = grid.get(0).length();


        int[][] crossNumberUsingCell = new int[rowNb][colNb];
        int[][] cumulateAreaUsedByCrossUsingCell = new int[rowNb][colNb];
        boolean[][] matriceOfAllowedCellForCross = new boolean[rowNb][colNb];
        List<Cross>[][] crosses = new ArrayList[rowNb][colNb];
        List<Cross> crossList = new ArrayList<>();
        Map<Integer, List<Cross>> areaForCrossList = new HashMap<>();
        for (int i = 0; i < rowNb; i++) {
            for (int j = 0; j < colNb; j++) {
                crossNumberUsingCell[i][j] = grid.get(i).charAt(j) == 'G' ? 0 : -1;
                matriceOfAllowedCellForCross[i][j] = grid.get(i).charAt(j) == 'G';
                crosses[i][j] = new ArrayList<>();
            }
        }

        Map<Coordinate, Integer> lengthByCenterCoordinates = new HashMap<>();


        int maxLength = 0;
        for (int i = 0; i < rowNb; i++) {
            for (int j = 0; j < colNb; j++) {
                boolean allowedCellForCross = matriceOfAllowedCellForCross[i][j];
                if (allowedCellForCross) {
                    boolean crossCanExpand = true;

                    Coordinate coordinate = new Coordinate(i, j);
                    Cross cross = new Cross(coordinate, 0);
                    crosses[i][j].add(cross);
                    int crossLength = 1;
                    while (crossCanExpand && (i + crossLength) < rowNb && (i - crossLength) >= 0 && (j + crossLength) < colNb && (j - crossLength) >= 0) {
                        boolean leftIsOpen = matriceOfAllowedCellForCross[i][j - crossLength];
                        boolean rightIsOpen = matriceOfAllowedCellForCross[i][j + crossLength];
                        boolean topIsOpen = matriceOfAllowedCellForCross[i - crossLength][j];
                        boolean bottomIsOpen = matriceOfAllowedCellForCross[i + crossLength][j];
                        crossCanExpand = leftIsOpen && rightIsOpen && topIsOpen && bottomIsOpen;
                        if (crossCanExpand == false) {
                            break;
                        }
                        Cross newCross = new Cross(coordinate, crossLength);
                        crosses[i][j].add(newCross);
                        crosses[i][j + crossLength].add(newCross);
                        crosses[i][j - crossLength].add(newCross);
                        crosses[i + crossLength][j].add(newCross);
                        crosses[i - crossLength][j].add(newCross);

                        for (int k = crossLength - 1; k > 0; k--) {
                            crosses[i][j + k].add(newCross);
                            crosses[i][j - k].add(newCross);
                            crosses[i + k][j].add(newCross);
                            crosses[i - k][j].add(newCross);
                        }

                        crossLength++;
                    }


                    maxLength = Math.max(maxLength, crossLength);

                }
            }
        }

        Set<Cross> crossSortedByLengthHash = new HashSet<>();
        for (int i = 0; i < rowNb; i++) {
            for (int j = 0; j < colNb; j++) {
                for (int k = 0; k < crosses[i][j].size(); k++) {
                    crossSortedByLengthHash.add(crosses[i][j].get(k));
                }
            }
        }
        List<Cross> crossSortedByLength = crossSortedByLengthHash
                .stream()
                .sorted(Comparator.comparing(Cross::getLength).reversed())
                .collect(toList());


        int maxArea = -1;
        for (int i = 0; i < crossSortedByLength.size(); i++) {
            Cross crossA = crossSortedByLength.get(i);

            for (int j = i + 1; j < crossSortedByLength.size(); j++) {
                Cross crossB = crossSortedByLength.get(j);
                int x = crossB.center.x;
                int y = crossB.center.y;
                boolean areCrossCrossingEachOther = false;

                int k = 1;
                while (!areCrossCrossingEachOther && k <= crossB.getLength()) {
                    if (crosses[x - k][y].contains(crossA)) {
                        areCrossCrossingEachOther = true;
                    } else if (crosses[x + k][y].contains(crossA)) {
                        areCrossCrossingEachOther = true;
                    } else if (crosses[x][y - k].contains(crossA)) {
                        areCrossCrossingEachOther = true;
                    } else if (crosses[x][y + k].contains(crossA)) {
                        areCrossCrossingEachOther = true;
                    }
                    k++;
                }

                if (areCrossCrossingEachOther == false) {
                    int area = crossA.getArea() * crossB.getArea();
                    if(area>maxArea){
                        maxArea = area;
                    }
                }
            }
        }
        if(maxArea!=-1){
            return maxArea;
        }

        return crossSortedByLength.get(0).getArea();

    }

    public static class Cross {
        private final Coordinate center;
        private int length;


        public Cross(Coordinate center, int length) {
            this.center = center;
            this.length = length;
        }




        public int getLength() {
            return length;
        }

        public int getArea() {
            return (length) * 4 + 1;
        }

        public void setLength(int length) {
            this.length = length;
        }



        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj.getClass() != this.getClass()) {
                return false;
            }
            final Cross other = (Cross) obj;
            if (this.center.x == other.center.x && this.center.y == other.center.y && other.length == this.length) {
                return true;
            }
            return false;
        }
    }

    private static class Coordinate {
        private final int x;
        private final int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<String> grid = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        int result = TwoPlus.twoPluses(grid);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
