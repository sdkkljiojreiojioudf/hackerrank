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


    private static class Event {
        private int start;
        private int end;

        public Event(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }

    public static int maxEvents(int[][] events) {
        int[][] dayEventAttended = new int[100001][2];


        PriorityQueue<Event> events1 = new PriorityQueue<>(Comparator.comparingInt(o -> (((Event) o).getEnd() - ((Event) o).getStart())).thenComparingInt(value -> ((Event) value).getStart()));

        LinkedList<Event> eventList = new LinkedList<>();
        List<Event> toSort = new ArrayList<>();
        for (int[] e : events) {
            Event event1 = new Event(e[0], e[1]);
            eventList.add(event1);
        }
        eventList = eventList.stream().sorted((o1, o2) -> o1.getStart() - o1.getEnd()).collect(Collectors.toCollection(LinkedList::new));
//        toSort.sort(Comparator.comparingInt(o -> (((Event) o).getEnd() - ((Event) o).getStart())).thenComparingInt(value -> ((Event) value).getStart()));
//        for (Event event1 : toSort) {
//            events1.add(event1);
//        }

        int[][] doubles = new int[100001][2];

        int cpt = 0;
        int doubleCpt = 0;
        while (eventList.isEmpty() == false) {
            Event event = eventList.poll();
            int start = event.getStart();
            int end = event.getEnd();
            while (start <= end) {
                int tmpCpt = 0;
                if (dayEventAttended[start][0] == 0 && dayEventAttended[start][1] == 0) {
                    dayEventAttended[start][0] = start;
                    dayEventAttended[start][1] = end;
                    tmpCpt++;
                }
                if (dayEventAttended[end][0] == 0 && dayEventAttended[end][1] == 0) {
                    dayEventAttended[end][0] = start;
                    dayEventAttended[end][1] = end;
                    tmpCpt++;
                }
                if (tmpCpt == 2) {
                    doubles[start][0] = start;
                    doubles[start][1] = end;
                    doubles[end][0] = start;
                    doubles[end][1] = end;
                    doubleCpt++;
                }
                if (tmpCpt > 0) {
                    cpt += tmpCpt;
                    break;
                } else if (doubles[start][0] > 0 || doubles[end][1] > 0) {
                    //if start and end are the same than in doubles
                    System.out.println("lol");
                    break;
                }
                start++;
                if (start < end) {
                    end--;
                }
            }
        }
        cpt -= doubleCpt;
        return cpt;
    }

    public static void main(String[] args) throws IOException {
//        {{1,5],[1,5],[1,5],[2,3],[2,3]]

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
        MaximumNumberOfEvents.maxEvents(new int[][]{{3, 6}, {3, 6}, {3,6}, {3, 3}, {6, 6}});
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
