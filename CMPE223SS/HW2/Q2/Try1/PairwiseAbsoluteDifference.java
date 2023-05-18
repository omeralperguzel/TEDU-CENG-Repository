package CMPE223SS.HW2.Q2.Try1;

import java.util.Arrays;

public class PairwiseAbsoluteDifference {
    
    public static void printSmallestPairwiseAbsoluteDifference(int[] arr, String sortAlgorithm) {
        int n = arr.length;
        int minDiff = Integer.MAX_VALUE;
        int minSum = Integer.MAX_VALUE;
        int num1 = -1;
        int num2 = -1;
        
        switch(sortAlgorithm) {
            case "Selection":
                selectionSort(arr);
                break;
            case "Insertion":
                insertionSort(arr);
                break;
            case "Merge":
                mergeSort(arr, 0, n-1);
                break;
            case "Quick":
                quickSort(arr, 0, n-1);
                break;
            default:
                break;
        }
        
        for(int i = 0; i < n-1; i++) {
            int diff = Math.abs(arr[i] - arr[i+1]);
            if(diff < minDiff) {
                minDiff = diff;
                num1 = arr[i];
                num2 = arr[i+1];
                minSum = num1 + num2;
            } else if(diff == minDiff) {
                int sum = arr[i] + arr[i+1];
                if(sum < minSum) {
                    minSum = sum;
                    num1 = arr[i];
                    num2 = arr[i+1];
                }
            }
        }
        
        System.out.println("ABS:" + minDiff + ", Min=" + num1 + ", Max=" + num2);
    }
    
    private static void selectionSort(int[] arr) {
        for(int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            for(int j = i+1; j < arr.length; j++) {
                if(arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
    
    private static void insertionSort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while(j >= 0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }
    
    private static void mergeSort(int[] arr, int l, int r) {
        if(l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m+1, r);
            merge(arr, l, m, r);
        }
    }
    
    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for(int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for(int j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }
        int i = 0, j = 0, k = l;
        while(i < n1 && j < n2) {
            if(L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } 
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while(i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while(j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    
    private static void quickSort(int[] arr, int low, int high) {
        if(low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for(int j = low; j < high; j++) {
            if(arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i+1;
    }
    
    public static void main(String[] args) {
        int[] arr = {23, 1, 5, 102, 34, 99};
        printSmallestPairwiseAbsoluteDifference(arr, "Selection");
        printSmallestPairwiseAbsoluteDifference(arr, "Insertion");
        printSmallestPairwiseAbsoluteDifference(arr, "Merge");
        printSmallestPairwiseAbsoluteDifference(arr, "Quick");
    }
}




