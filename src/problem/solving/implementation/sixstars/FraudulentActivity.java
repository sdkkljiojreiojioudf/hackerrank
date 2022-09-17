 package problem.solving.implementation.sixstars;


import java.io.*;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FraudulentActivity {


    public static int activityNotifications(List<Integer> expenditure, int d) {
        // Write your code here

        PriorityBlockingQueue<Integer> smallNumbersQueue = new PriorityBlockingQueue<>(d/2, (o1, o2) -> o2 - o1);
        PriorityBlockingQueue<Integer> highNumbersQueue = new PriorityBlockingQueue<>();
        for (int i = 0; i < d; i++) {
            int value = expenditure.get(i);
            addValue(smallNumbersQueue, highNumbersQueue, value);
        }
        int cpt = 0;
        for (int i = d; i < expenditure.size(); i++) {
            int currentValue = expenditure.get(i);
            double median = -1;
            if (d % 2 == 0) {
                median = (smallNumbersQueue.peek() +highNumbersQueue.peek()) / 2.;
            } else {
                if (smallNumbersQueue.size() > highNumbersQueue.size()) {
                    median = smallNumbersQueue.peek();
                } else {
                    median = highNumbersQueue.peek();
                }
            }
            if (currentValue >= (median * 2)) {
                cpt++;
            }

            int valueToRemove =  expenditure.get(i-d);
            removeValue(smallNumbersQueue, highNumbersQueue, valueToRemove);
            addValue(smallNumbersQueue, highNumbersQueue, currentValue);

        }

        return cpt;
    }

    private static void removeValue(PriorityBlockingQueue<Integer> smallNumbersQueue, PriorityBlockingQueue<Integer> highNumbersQueue, int valueToRemove) {
        if(valueToRemove < highNumbersQueue.peek()){
            smallNumbersQueue.remove(valueToRemove);
        }else{
            highNumbersQueue.remove(valueToRemove);
        }
        balance(smallNumbersQueue, highNumbersQueue);
    }

    private static void addValue(PriorityBlockingQueue<Integer> smallNumbersQueue, PriorityBlockingQueue<Integer> highNumbersQueue, int value) {
        if (smallNumbersQueue.isEmpty()) {
            smallNumbersQueue.add(value);
            return;
        }
        if (smallNumbersQueue.peek() <= value) {
            highNumbersQueue.add(value);
        } else {
            smallNumbersQueue.add(value);
        }
        //balance
        balance(smallNumbersQueue, highNumbersQueue);
    }

    private static void balance(PriorityBlockingQueue<Integer> smallNumbersQueue, PriorityBlockingQueue<Integer> highNumbersQueue) {
        if (smallNumbersQueue.size() > highNumbersQueue.size()) {
            highNumbersQueue.add(smallNumbersQueue.poll());
        }else if(highNumbersQueue.size() -1 > smallNumbersQueue.size()){
            smallNumbersQueue.add(highNumbersQueue.poll());
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int d = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> expenditure = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = FraudulentActivity.activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
