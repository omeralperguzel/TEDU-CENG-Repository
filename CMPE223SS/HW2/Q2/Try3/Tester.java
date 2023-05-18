package CMPE223SS.HW2.Q2.Try3;

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

    public static void quickSort(int[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }

    public static void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSortHelper(arr, low, pivotIndex - 1);
            quickSortHelper(arr, pivotIndex + 1, high);
        }
    }

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