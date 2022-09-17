package problem.solving.leetcode;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;

public class MedianFinder {
  PriorityQueue<Integer> minHeap;
  PriorityQueue<Integer> maxHeap;
  int minSize;
  int maxSize;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minSize = 0;
        maxSize = 0;
    }

    public void addNum(int num) {
        if(minSize==0){
            minHeap.offer(num);
            minSize++;
            return;
        }
        if (maxSize==0){
            if(num < minHeap.peek()){
                maxHeap.offer(num);
            }else{
                maxHeap.offer(minHeap.poll());
                minHeap.offer(num);
            }
            maxSize++;
            return;
        }
        if(num<minHeap.peek()){
            maxHeap.offer(num);
            maxSize++;
        }else{
            minHeap.offer(num);
            minSize++;
        }
        balance();
    }

    private void balance() {
        if (Math.abs(minSize - maxSize) <= 1) {
            return;
        }

        if (minSize > maxSize) {
            maxHeap.offer(minHeap.poll());
            minSize--;
            maxSize++;
        } else {
            minHeap.offer(maxHeap.poll());
            minSize++;
            maxSize--;
        }
    }

    public double findMedian() {
        if (minSize > maxSize) {
            return minHeap.peek();
        }
        if (maxSize > minSize) {
            return maxHeap.peek();
        }
        double a = minHeap.peek();
        double b = maxHeap.peek();
        return (a + b) / 2.0;
    }

    public static void main(String[] args) {

        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(3);  // arr = [1, 2]// arr[1, 2, 3]
        medianFinder.addNum(8);  // arr = [1, 2]// arr[1, 2, 3]
        medianFinder.addNum(2);  // arr = [1, 2]// arr[1, 2, 3]
        medianFinder.addNum(4);  // arr = [1, 2]// arr[1, 2, 3]
        medianFinder.addNum(5);  // arr = [1, 2]// arr[1, 2, 3]
        // arr = [1, 2]// arr[1, 2, 3]
          // arr = [1, 2]// arr[1, 2, 3]
        medianFinder.findMedian();

    }
}
