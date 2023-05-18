package CMPE223SS.HW2.Q2.Try2;

import java.util.Arrays;
import java.util.Random;

public class Tester {
    public static void main(String[] args) {
        int[] arr;
        Random rand = new Random();
        int[] sizes = {10, 100, 1000, 10000};
        String[] sortAlgorithms = {"Selection", "Insertion", "Merge", "Quick"};
        for (int size : sizes) {
            System.out.println("Array size: " + size);
            arr = generateAscendingArray(size);
            testAllSortAlgorithms(arr);
            arr = generateDescendingArray(size);
            testAllSortAlgorithms(arr);
            arr = generateRandomArray(size, rand);
            testAllSortAlgorithms(arr);
            System.out.println();
        }
    }

    public static void testAllSortAlgorithms(int[] arr) {
        smallestPairwiseDifference(arr, "Selection");
        smallestPairwiseDifference(arr, "Insertion");
        smallestPairwiseDifference(arr, "Merge");
        smallestPairwiseDifference(arr, "Quick");
        smallestPairwiseDifference(arr, "noSort");
    }

    public static int[] generateAscendingArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static int[] generateDescendingArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i - 1;
        }
        return arr;
    }

    public static int[] generateRandomArray(int size, Random rand) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt();
        }
        return arr;
    }

    // smallestPairwiseDifference method implementation goes here
    // selectionSort, insertionSort, mergeSort, and quickSort methods also go here
}