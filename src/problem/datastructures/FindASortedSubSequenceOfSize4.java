package problem.datastructures;


// Java program to find a sorted
// sub-sequence of size 4

public class FindASortedSubSequenceOfSize4 {

    static void findQuadruplet(int arr[], int n) {

        // If number of elements < 4
        // then no Quadruple are possible
        if (n < 4) {
            System.out.println("No such Quadruple found");
            return;
        }
        boolean flag = false;

        // to check true or false
        // Index of greatest element
        // from right side
        int max = n - 1;

        // Index of smallest element
        // from left side
        int min = 0;

        // Index of second smallest element
        // from left side
        int second_min = -1;

        // Create another array that will
        // store index of a minimum element
        // on left side. If there is no minimum
        // element on left side,
        // then smaller[i] = -1
        int[] smaller = new int[n];

        // Create another array that will
        // store index of a second minimum
        // element on left side. If there is no
        // second minimum element on left side,
        // then second_smaller[i] = -1
        int[] second_smaller = new int[n];

        // First entry of both smaller and
        // second_smaller is -1.
        smaller[0] = -1;
        second_smaller[0] = -1;
        for (int i = 1; i < n; i++) {
            if (arr[i] <= arr[min]) {
                min = i;
                smaller[0] = -1;
                second_smaller[0] = -1;
            } else {
                smaller[i] = min;
                if (second_min != -1) {
                    if (arr[second_min] < arr[i]) {
                        second_smaller[i] = second_min;
                    }
                } else {
                    second_smaller[i] = -1;
                }
                if (second_min == -1
                        || arr[i] < arr[second_min]) {
                    second_min = i;
                }
            }
        }

        // Create another array that will store
        // index of a greater element on right side.
        // If there is no greater element on
        // right side, then greater[i] = -1

        int[] greater = new int[n];

        // Last entry of greater is -1.
        greater[n - 1] = -1;
        for (int i = n - 2; i >= 0; i--) {

            if (arr[max] <= arr[i]) {
                max = i;
                greater[i] = -1;
            } else {
                greater[i] = max;
            }
        }

        // Now we need to find a number which
        // has a greater on its right side and
        // two small on it's left side.
        for (int i = 0; i < n; i++) {
            if (second_smaller[i] != -1
                    && smaller[second_smaller[i]] != -1
                    && greater[i] != -1) {
                System.out.println(
                        "Quadruplets: "
                                + arr[smaller[second_smaller[i]]] + " "
                                + arr[second_smaller[i]] + " " + arr[i]
                                + " " + arr[greater[i]]);
                flag = true;
                break;
            }
        }
        if (flag == false)
            System.out.println("No such Quadruplet found");

        return;
    }

    // Driver Code
    public static void main(String args[]) {
        int arr[] = {0, 2,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,3,4,2,4,3,5,6,7};
        int N = arr.length;
        findQuadruplet(arr, N);
    }
}

// This code is contributed by Lovely Jain
