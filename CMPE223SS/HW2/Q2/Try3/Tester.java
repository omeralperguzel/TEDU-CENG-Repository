//-----------------------------------------------------
//Title: Pairwise
//Author: Ömer Alper Güzel
//Section: 2
//Assignment: 2 Q2
//Description: This is a Java program that allows the user to test the sorting algorithms.
//-----------------------------------------------------

package CMPE223SS.HW2.Q2.Try3;

import java.util.Arrays;
import java.util.Random;

//This class is used to test the sorting algorithms
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

    // Test all sorting algorithms on the given array
    public static void testAllSortAlgorithms(int[] arr) {
        smallestPairwiseDifference(arr, "Selection");
        smallestPairwiseDifference(arr, "Insertion");
        smallestPairwiseDifference(arr, "Merge");
        smallestPairwiseDifference(arr, "Quick");
        smallestPairwiseDifference(arr, "noSort");
    }

    // Generate an array of size "size" with ascending values
    public static int[] generateAscendingArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }

    // Generate an array of size "size" with descending values
    public static int[] generateDescendingArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i - 1;
        }
        return arr;
    }

    // Generate an array of size "size" with random values
    public static int[] generateRandomArray(int size, Random rand) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt();
        }
        return arr;
    }

    // It sorts the array using the given sorting algorithm and finds the smallest pairwise difference
    public static void smallestPairwiseDifference(int[] arr, String sortAlgorithm) {
        long startTime = System.currentTimeMillis();
        switch (sortAlgorithm) {
            case "Selection":
                selectionSort(arr);
                break;
            case "Insertion":
                insertionSort(arr);
                break;
            case "Merge":
                mergeSort(arr);
                break;
            case "Quick":
                quickSort(arr);
                break;
            case "noSort":
                break;
            default:
                System.out.println("Invalid sorting algorithm.");
                return;
        }
        int minDiff = Integer.MAX_VALUE;
        int minSum = Integer.MAX_VALUE;
        int n = arr.length;
        int num1 = 0, num2 = 0;
        for (int i = 0; i < n - 1; i++) {
            int diff = Math.abs(arr[i] - arr[i + 1]);
            if (diff < minDiff) {
                minDiff = diff;
                num1 = arr[i];
                num2 = arr[i + 1];
                minSum = num1 + num2;
            } else if (diff == minDiff) {
                int sum = arr[i] + arr[i + 1];
                if (sum < minSum) {
                    num1 = arr[i];
                    num2 = arr[i + 1];
                    minSum = sum;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("ABS:" + minDiff + ", Min=" + num1 + ", Max=" + num2 + ", Time: " + (endTime - startTime) + " ms");
    }

    // Sort the array using selection sort algorithm
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // Sort the array using insertion sort algorithm
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Sort the array using merge sort algorithm
    public static void mergeSort(int[] arr) {
        int n = arr.length;
        if (n > 1) {
            int mid = n / 2;
            int[] left = new int[mid];
            int[] right = new int[n - mid];
            for (int i = 0; i < mid; i++) {
                left[i] = arr[i];
            }
            for (int i = mid; i < n; i++) {
                right[i - mid] = arr[i];
            }
            mergeSort(left);
            mergeSort(right);
            int i = 0, j = 0, k = 0;
            while (i < left.length && j < right.length) {
                if (left[i] < right[j]) {
                    arr[k] = left[i];
                    i++;
                } else {
                    arr[k] = right[j];
                    j++;
                }
                k++;
            }
            while (i < left.length) {
                arr[k] = left[i];
                i++;
                k++;
            }
            while (j < right.length) {
                arr[k] = right[j];
                j++;
                k++;
            }
        }
    }

    // Sort the array using quick sort algorithm
    public static void quickSort(int[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }

    // Helper method for quick sort
    public static void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSortHelper(arr, low, pivotIndex - 1);
            quickSortHelper(arr, pivotIndex + 1, high);
        }
    }

    // This method partitions the array and returns the pivot index
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}