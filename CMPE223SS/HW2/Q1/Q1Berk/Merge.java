package CMPE223SS.HW2.Q1.Q1Berk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Merge {

    /* teacher firstly i wrote my own algorithm by using while loop but when i read the instructions i awared
    that method is false so instructions says copy and paste the algorithm from the textbook
    i did not erease the my own merge sort method its called MergeSort(int[] InputArray)

     */


   public static void main(String[]args){

        int[] array  = {2,0,33,-44,3,66,91,11};

     //  MergeSort_Descending(array);

            MergeSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void MergeSort(int[] InputArray) {
        int ia_length = InputArray.length;

        if (ia_length < 2) {
            return;
        }
        int mid = ia_length / 2;

        int[] left_half = new int[mid];
        int[] right_half = new int[ia_length - mid];

        for (int i = 0; i < mid; i++) {
            left_half[i] = InputArray[i];
        }
        for (int i = mid; i < ia_length; i++) {
            right_half[i - mid] = InputArray[i];
        }
        MergeSort(left_half);
        MergeSort(right_half);
        Mergee(InputArray,left_half,right_half);
    }

    public static void Mergee(int[] InputArray,int[] left_half,int[] right_half){
            int lefthalf_size = left_half.length;
            int righthalf_size = right_half.length;

            int i=0,j=0,k=0;

            while(i<lefthalf_size && j<righthalf_size){
                if (left_half[i]<=right_half[j]){
                    InputArray[k] = left_half[i++];
                }
                else{
                    InputArray[k] = right_half[j++];
                }
                k++;
            }

            while(i<lefthalf_size){
                InputArray[k++] = left_half[i++];
            }
            while(j<righthalf_size){
                InputArray[k++] = right_half[j++];
            }
    }

    public static void Mergee_Descending(int[] InputArray,int[] left_half,int[] right_half){
        int lefthalf_size = left_half.length;
        int righthalf_size = right_half.length;

        int i=0,j=0,k=0;

        while(i<lefthalf_size && j<righthalf_size){
            if (left_half[i]>=right_half[j]){
                InputArray[k] = left_half[i++];
            }
            else{
                InputArray[k] = right_half[j++];
            }
            k++;
        }

        while(i<lefthalf_size){
            InputArray[k++] = left_half[i++];
        }
        while(j<righthalf_size){
            InputArray[k++] = right_half[j++];
        }

   }

    public static void MergeSort_Descending(int[] InputArray) {
        int ia_length = InputArray.length;

        if (ia_length < 2) {
            return;
        }
        int mid = ia_length / 2;

        int[] left_half = new int[mid];
        int[] right_half = new int[ia_length - mid];

        for (int i = 0; i < mid; i++) {
            left_half[i] = InputArray[i];
        }
        for (int i = mid; i < ia_length; i++) {
            right_half[i - mid] = InputArray[i];
        }
        MergeSort(left_half);
        MergeSort(right_half);
        Mergee_Descending(InputArray,left_half,right_half);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }




    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {  // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)  // Copy a[lo..hi] to aux[lo..hi].
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++)  // Merge back to a[lo..hi].
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
    }

    private static Comparable[] aux;      // auxiliary array for merges

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];    // Allocate space just once.
        sort_descending(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {  // Sort a[lo..hi].
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);       // Sort left half.
        sort(a, mid + 1, hi);     // Sort right half.
        merge(a, lo, mid, hi);  // Merge results (code on page 271).
    }


    public static void sort_descending(Comparable[] a, int lo, int hi) {  // Sort a[lo..hi].
        if (hi >= lo) return; // small < operation change can change algorithm directly
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);       // Sort left half.
        sort(a, mid + 1, hi);     // Sort right half.
        merge(a, lo, mid, hi);  // Merge results (code on page 271).
    }
}






