package problem.solving.implementation.sixstars;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class RedKnightShortestPath {
    public static void printShortestPath(int n, int i_start, int j_start, int i_end, int j_end) {
        // Print the distance along with the sequence of moves.

        Map<String, Integer> priorityDirectionMap = new HashMap<>();
        priorityDirectionMap.put("UL", 0);
        priorityDirectionMap.put("UR", 1);
        priorityDirectionMap.put("R", 2);
        priorityDirectionMap.put("LR", 3);
        priorityDirectionMap.put("LL", 4);
        priorityDirectionMap.put("L", 5);

        List<Direction> redKnightAllowedDirections = new ArrayList<>();
        redKnightAllowedDirections.add(
                new Direction("UL", -2, -1, (iDiff, jDiff) -> iDiff < 0 && jDiff <= 0)
        );
        redKnightAllowedDirections.add(
                new Direction("UR", -2, 1, (iDiff, jDiff) -> iDiff < 0 && jDiff > 0)
        );
        redKnightAllowedDirections.add(
                new Direction("R", 0, 2, (iDiff, jDiff) -> iDiff == 0 && jDiff > 0)
        );
        redKnightAllowedDirections.add(
                new Direction("LR", 2, 1, (iDiff, jDiff) -> iDiff > 0 && jDiff >= 0)
        );
        redKnightAllowedDirections.add(
                new Direction("LL", 2, -1, (iDiff, jDiff) -> iDiff > 0 && jDiff < 0)
        );
        redKnightAllowedDirections.add(
                new Direction("L", 0, -2, (iDiff, jDiff) -> iDiff == 0 && jDiff < 0)
        );

        int knightI = i_start;
        int knightJ = j_start;


        List<Direction> directionList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int iDiff = i_end - knightI;
            int jDiff = j_end - knightJ;

            int iDiffAbs = Math.abs(iDiff);
            int jDiffAbs = Math.abs(jDiff);

            if ((iDiffAbs==0 && jDiffAbs ==1)
                    || (iDiffAbs == 0 && jDiffAbs==1)
                    || (iDiffAbs == 1 && jDiffAbs == 1)
            ) {
                System.out.println("Impossible");
                break;
            }

            Optional<Direction> direction = redKnightAllowedDirections.stream()
                    .filter(e -> e.getIsGoodDirection().apply(iDiff, jDiff))
                    .findFirst();
            if (direction.isPresent()) {
                knightI += direction.get().getI();
                knightJ += direction.get().getJ();
                directionList.add(direction.get());
            }

            if (iDiff == 0 && jDiff == 0) {
                System.out.println(directionList.size());
                System.out.println(
                        directionList.stream()
                                .map(Direction::getDirectionCode)
                                .sorted(Comparator.comparingInt(priorityDirectionMap::get))
                                .collect(Collectors.joining(" "))
                );
                break;
            }
        }
    }

    private static class Direction {
        private String directionCode;
        private final int i;
        private final int j;

        BiFunction<Integer, Integer, Boolean> isGoodDirection;


        public Direction(String directionCode, int i, int j, BiFunction<Integer, Integer, Boolean> isGoodDirection) {
            this.directionCode = directionCode;
            this.i = i;
            this.j = j;
            this.isGoodDirection = isGoodDirection;
        }

        public String getDirectionCode() {
            return directionCode;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public BiFunction<Integer, Integer, Boolean> getIsGoodDirection() {
            return isGoodDirection;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int i_start = Integer.parseInt(firstMultipleInput[0]);

        int j_start = Integer.parseInt(firstMultipleInput[1]);

        int i_end = Integer.parseInt(firstMultipleInput[2]);

        int j_end = Integer.parseInt(firstMultipleInput[3]);

        RedKnightShortestPath.printShortestPath(n, i_start, j_start, i_end, j_end);

        bufferedReader.close();
    }
}
