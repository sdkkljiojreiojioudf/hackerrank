package problem.solving.implementation.sixstars;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class AlmostSorted {
    /*
     * Complete the 'almostSorted' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void almostSorted(List<Integer> list) {
        // Write your code here
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }

        //test swap
        int firstIndexUnsorted = -1;
        int toSwapFirstIndex = -1;
        int toSwapSecondIndex = -1;

        for (int i = 0; i < arr.length; i++) {

            if (toSwapFirstIndex==-1  && i> 0 && arr[i] < arr[i - 1]) {
                firstIndexUnsorted = i-1;
                toSwapFirstIndex = i - 1;
            }
            if (toSwapFirstIndex != -1 && arr[i] < arr[i-1] ) {
                if((toSwapFirstIndex + 1 == i || arr[i] < arr[toSwapFirstIndex + 1]) && (i >= arr.length - 1 || arr[toSwapFirstIndex] < arr[i + 1])){
                    toSwapSecondIndex = i;
                }

            }
            if(toSwapFirstIndex!= -1 && toSwapSecondIndex != -1){
                int tmp = arr[toSwapFirstIndex];
                arr[toSwapFirstIndex] = arr[toSwapSecondIndex];
                arr[toSwapSecondIndex] = tmp;
                break;
            }
        }

        //
        boolean moreThanTwoSwapNecessary = false;
        for (int i = 1; i < arr.length; i++) {
            if( arr[i] < arr[i - 1]){
                moreThanTwoSwapNecessary = true;
                break;
            }
        }

        if(moreThanTwoSwapNecessary== false && toSwapFirstIndex!=-1 && toSwapSecondIndex !=-1) {
            System.out.println("yes");
            System.out.println("swap " + (toSwapFirstIndex + 1) + " " + (toSwapSecondIndex + 1));
            return;
        }


        // re initiliaze array
        arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }

        // test reverse
        int lastIndexUnsorted = -1;
        for (int i = firstIndexUnsorted; i < arr.length; i++) {
            if(arr[i+1]>arr[i]){
                if(i+2==arr.length || arr[i+1]<arr[i+2]){
                    lastIndexUnsorted = i;
                    break;
                }
                else if(i+2>=arr.length){
                    System.out.println("no");
                    return;
                }
            }
        }

        for (int i = firstIndexUnsorted; i < lastIndexUnsorted; i++) {
            if (arr[i] < arr[i + 1] || arr[i] > arr[lastIndexUnsorted + 1] || (i>0 && arr[firstIndexUnsorted-1]>arr[lastIndexUnsorted])) {
                System.out.println("no");
                return;
            }
        }
        System.out.println("yes");
        System.out.println("reverse " + (firstIndexUnsorted + 1) + " " + (lastIndexUnsorted + 1));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        AlmostSorted.almostSorted(arr);

        bufferedReader.close();
    }
}
