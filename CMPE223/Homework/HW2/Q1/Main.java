package CMPE223.Homework.HW2.Q1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static int[][] initializeArray(ArrayList<String> lines){
        int totalRow = 0;
        int totalColumn = lines.size();
        
        String row = lines.get(0);
        //Split our string row space by space to obtain integer values.
        String[] rowElements = row.split(" ");
        totalRow = rowElements.length;
        int[][] resultMatrix = new int[totalColumn][totalRow];
        //Set integer values to our new 2D array.
        for (int indexColumn = 0; indexColumn < totalColumn; indexColumn++){
            String[] rowElementStrings = lines.get(indexColumn).split(" ");
            for (int indexRow = 0; indexRow < totalRow; indexRow++){
                resultMatrix[indexColumn][indexRow] = Integer.parseInt(rowElementStrings[indexRow]);
            }
        }
        return resultMatrix;
    }

    public static void sort(Comparable[] a){ 
        // Sort a[] into increasing order.
        int N = a.length;
        for (int i = 1; i < N; i++){ // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
             for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
             exch(a, j, j-1);
            }
        }
        // See page 245 for less(), exch(), isSorted(), and main().
        private static boolean less(Comparable v, Comparable w){ 
            return v.compareTo(w) < 0; 
        }
        
        private static void exch(Comparable[] a, int i, int j){ 
            Comparable t = a[i]; a[i] = a[j]; a[j] = t; 
        }
            
        private static void show(Comparable[] a){ 
            // Print the array, on a single line.
            for (int i = 0; i < a.length; i++){
                System.out.print(a[i] + " ");
            }    
            System.out.println();
            }
        
        public static boolean isSorted(Comparable[] a){ 
            // Test whether the array entries are in order.
            for (int i = 1; i < a.length; i++){
                if (less(a[i], a[i-1])) return false;
            }
            return true;
 }
        public static void main(String[] args) throws FileNotFoundException{ 
            // Read strings from standard input, sort them, and print.
            System.out.println("Input filename:");
            Scanner console = new Scanner(System.in);
            File file = new File(console.nextLine());
            Scanner scan = new Scanner(file);
            List<String> lines = Files.readAllLines(file, charset);
            String[] arr = lines.toArray(new String[lines.size()]);
            sort(a);
            assert isSorted(a);
            show(a);
        } 
}
