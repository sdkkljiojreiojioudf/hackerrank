package problem.solving.leetcode;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MaximumNumberOfEvents {


    public static int maxEvents(int[][] events) {
        if(events.length==0){
            return 0;
        }
       Arrays.sort(events, Comparator.comparingInt(o -> o[0]));
       int maxStart = events[0][0];
       int cpt =0;
        for (int[] event : events) {
            int start = event[0];
            int end = event[1];
            if (start >= maxStart) {
                cpt++;
                maxStart = start;
            }
        }
        return cpt;
    }

    public static void main(String[] args) throws IOException {
//        {{1,5],[1,5],[1,5],[2,3],[2,3]]
        //[[1,2],[2,3],[3,4],[1,2]]

//
//        int[][] ints = new int[100000][2];
//        for (int i = 1; i < 100000; i++) {
//            ints[i][0] = 1;
////            ints[i][0] = (int) (Math.random() * ( - 0)) + 0;
//            ints[i][1] = ints[i][0] + ((int) (Math.random() * (100000 - 0)) + 0);
////            ints[i][1] = i;
//        }
//
//        MaximumNumberOfEvents.maxEvents(ints);

//{{1,2],[2,2],[3,3],[3,4],[3,4]]
//        {{1,2],[2,3],[3,4]]
//        MaximumNumberOfEvents.maxEvents(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 2}});

//            [[1,2],[2,2],[3,3],[3,4],[3,4]]
//        MaximumNumberOfEvents.maxEvents(new int[][]{{1, 2}, {2, 2}, {3, 3}, {3, 4}, {3,4}});
//        MaximumNumberOfEvents.maxEvents(new int[][]{{3, 3}, {0, 1}, {2, 2}, {1, 3}});
//        [[1,10],[2,2],[2,2],[2,2],[2,2]]
        MaximumNumberOfEvents.maxEvents(new int[][]{{1,2},{2,3}, {3,4}, {1,2}});
//        [[1,3],[1,3],[1,3],[3,4]]
//        MaximumNumberOfEvents.maxEvents(new int[][]{{1,3}, {1, 3}, {1, 3}, {3, 4}});
//        [[7,11],[7,11],[7,11],[9,10],[9,11]]
//        MaximumNumberOfEvents.maxEvents(new int[][]{{7,11}, {7, 11}, {7,11}, {9, 10}, {9,11}});

////
//        MaximumNumberOfEvents.maxEvents(new int[][]{{27, 27},
//                {8, 10},
//                {9, 11},
//                {20, 21},
//                {25, 29},
//                {17, 20},
//                {12, 12},
//                {12, 12},
//                {10, 14},
//                {7, 7},
//                {6, 10},
//                {7, 7},
//                {4, 8},
//                {30, 31},
//                {23, 25},
//                {4, 6},
//                {17, 17},
//                {13, 14},
//                {6, 9},
//                {13, 14}}
//        );
////
//
//
//
//
//[[1,1],[1,2],[2,2]]
//        MaximumNumberOfEvents.maxEvents(new int[][]{{1, 2}, {1, 2}, {2, 2}});
//        MaximumNumberOfEvents.maxEvents(new int[][]{{1, 2}, {2, 3}, {2, 2}});
//
////
//        MaximumNumberOfEvents.maxEvents(new int[][]
//                {{1, 2}, {1, 2}, {1, 3}});
//[[1,3],[1,3],[1,3],[3,4]]

//
//        MaximumNumberOfEvents.maxEvents(new int[][]{{1, 3}, {1, 3}, {1, 3}, {3, 4}});
////[[1,2],[2,2],[3,3],[3,4],[3,4]]
//        MaximumNumberOfEvents.maxEvents(new int[][]{{1, 2}, {2, 2}, {3, 3}, {3, 4}, {3, 4}});
//        {{1,4],[4,4],[2,2],[3,4],[1,1]]
//        MaximumNumberOfEvents.maxEvents(new int[][]{{1, 4}, {4, 4}, {2, 2}, {3, 4}, {1, 1}});
////////
////////        {{1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
//        MaximumNumberOfEvents.maxEvents(new int[][]{{1, 4}, {4, 4}, {2, 2}, {3, 4}, {1, 1}});
//        MaximumNumberOfEvents.maxEvents(new int[][]{{1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}});
//////        [[1,5],[1,5],[1,5],[2,3],[2,3]]
//        MaximumNumberOfEvents.maxEvents(new int[][]{{1, 5}, {1, 5}, {1, 5}, {2, 3}, {2, 3}});
////        MaximumNumberOfEvents.maxEvents(new int[][]{{1,2},{1,2},{3,3},{1,5},{1,5}});
//        MaximumNumberOfEvents.maxEvents(new int[][]{{1, 5}, {1, 5}, {1, 5}, {2, 3}, {2, 3}});

    }
}
