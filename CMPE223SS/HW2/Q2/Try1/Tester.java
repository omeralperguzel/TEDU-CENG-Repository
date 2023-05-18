package CMPE223SS.HW2.Q2.Try1;

import java.util.Random;

public class Tester {
    public static void main(String[] args) {
        int[] arr = new int[10];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(1000);
        }

        // Setting 1: Selection sort
        long startTime = System.currentTimeMillis();
        Pairwise.smallestPairwiseDifference(selectionSort(arr.clone()));
        long endTime = System.currentTimeMillis();
        System.out.println("Selection sort time: " + (endTime - startTime) + " ms");

        // Setting 2: Insertion sort
        startTime = System.currentTimeMillis();
        Pairwise.smallestPairwiseDifference(insertionSort(arr.clone()));
        endTime = System.currentTimeMillis();
        System.out.println("Insertion sort time: " + (endTime - startTime) + " ms");

        // Setting 3: Merge sort
        startTime = System.currentTimeMillis();
        Pairwise.smallestPairwiseDifference(mergeSort(arr.clone()));
        endTime = System.currentTimeMillis();
        System.out.println("Merge sort time: " + (endTime - startTime) + " ms");

        // Setting 4: Quick sort
        startTime = System.currentTimeMillis();
        Pairwise.smallestPairwiseDifference(quickSort(arr.clone()));
        endTime = System.currentTimeMillis();
        System.out.println("Quick sort time: " + (endTime - startTime) + " ms");

        // Setting 5: Without sorting
        startTime = System.currentTimeMillis();
        Pairwise.smallestPairwiseDifference(arr.clone());
        endTime = System.currentTimeMillis();
        System.out.println("Without sorting time: " + (endTime - startTime) + " ms");
    }

    public static int[] selectionSort(int[] arr) {
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
        return arr;
    }

    public static int[] insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    public static int[] mergeSort(int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return arr;
        }
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
        merge(left, right, arr);
        return arr;
    }

    public static void merge(int[] left, int[] right, int[] arr) {
        int nL = left.length;
        int nR = right.length;
        int i = 0, j = 0, k = 0;
        while (i < nL && j < nR) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < nL) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < nR) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    public static int[] quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
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