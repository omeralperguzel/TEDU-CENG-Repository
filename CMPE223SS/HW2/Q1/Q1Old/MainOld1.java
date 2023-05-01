package CMPE223SS.HW2.Q1.Q1Old;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MainOld1 {
    public static void main(String[] args) {
        // Load integers from file
        int[] arrFromFile = loadFromFile("input.txt");
        System.out.println("Array from file: " + Arrays.toString(arrFromFile));
        
        // Create random array
        int[] arrRandom = createRandomArray(10);
        System.out.println("Random array: " + Arrays.toString(arrRandom));
        
        // Sort arrays using insertion sort
        insertionSort(arrFromFile);
        System.out.println("Sorted array from file: " + Arrays.toString(arrFromFile));
        
        insertionSort(arrRandom);
        System.out.println("Sorted random array: " + Arrays.toString(arrRandom));
    }
    
    public static int[] loadFromFile(String filename) {
        int[] arr = null;
        try {
            Scanner scanner = new Scanner(new File(filename));
            int n = scanner.nextInt();
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return arr;
    }
    
    public static int[] createRandomArray(int n) {
        int[] arr = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(100);
        }
        return arr;
    }
    
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    public static void insertionSortDesc(int[] arr) {
        for (int i = arr.length - 2; i >= 0; i--) {
            int key = arr[i];
            int j = i + 1;
            while (j < arr.length && arr[j] < key) {
                arr[j-1] = arr[j];
                j++;
            }
            arr[j-1] = key;
        }
    }

    public static void insertionSort(double[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            double key = arr[i];
            int j = i - 1;
    
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public static void mergeSortDescending(int[] arr) {
        int n = arr.length;
        int[] aux = new int[n];
        mergeSortDescending(arr, aux, 0, n - 1);
    }
    
    private static void mergeSortDescending(int[] arr, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        mergeSortDescending(arr, aux, lo, mid);
        mergeSortDescending(arr, aux, mid + 1, hi);
        mergeDescending(arr, aux, lo, mid, hi);
    }
    
    private static void mergeDescending(int[] arr, int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }
        int i = lo;
        int j = hi;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                arr[k] = aux[j--];
            } else if (j < mid + 1) {
                arr[k] = aux[i++];
            } else if (aux[i] > aux[j]) {
                arr[k] = aux[i++];
            } else {
                arr[k] = aux[j--];
            }
        }
    }

}

