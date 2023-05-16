package CMPE223SS.HW2.Q1.Q1Berk;

import com.sun.security.jgss.GSSUtil;
import java.io.BufferedReader;
import java.io.File;
import java.nio.DoubleBuffer;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //see no path
        int numbers[] = readFiles("integers.txt");
        //reading the txt file from here

        int n = numbers.length;

        // you need to use Integer[] not int[] in order to use Comparable[]
        Integer[] arr = new Integer[n];

        //filling the array with integers coming from the txt file
        for (int i = 0; i < n; i++) {
            arr[i] = numbers[i];
        }

        Integer [] Original = arr.clone();
        // we need to clone that array for use one more time at step 4

        System.out.println("Integers are reading from the integers.txt file, the array is :");
        printArray(arr);

        System.out.println("The array has been sorted in increasing order by using the insertion sort algorithm:");
        Insertion insertion = new Insertion();
        insertion.Insertion_sort(arr);
        // insertion sort algorithm calling from here (üstten)
        printArray(arr);
        System.out.println("Step 1 has been completed.");
        System.out.println();

        System.out.println("The array of integers that has been sorted in decreasing order by using the insertion sort algorithm:");
        // insertion sort algorithm calling from here (descending)
        insertion.Insertion_sort_DO(arr);
        printArray(arr);
        System.out.println("Step 2 has been completed.");
        System.out.println();

        System.out.println("Doubles are reading from the doubles.txt file, the array is:");

        double numbers_double[] = readfiles("doubles.txt");
        // double text file is reading from here

        int k = numbers_double.length;

        Double[] array = new Double[k];
        for (int s = 0; s < k; s++) {
            array[s] = numbers_double[s];
            // double array is filling by txt file values
        }
        printArray(array);
        System.out.println("The array of double values has been sorted in increasing order by using the insertion sort algorithm:");
        insertion.Insertion_sort_DV(array);

        printArray(array);
        System.out.println("Step 3 has been completed.");
        System.out.println();

        System.out.println("The original array is:");
        printArray(Original);
        System.out.println("The array of integer values has been sorted in descending order by using the merge sort algorithm is:");
       // calling merge sort descending algorithm
         Merge.sort(Original);
        System.out.println("Step 4 has been completed.");
        System.out.println();

        // creating new object by pdf file
        // and filling it from route class (destination, source)
        Route route = new Route("Ankara", "Antalya");
        Route route1 = new Route("Ankara", "Istanbul");
        Route route2 = new Route("Istanbul", "Antalya");
        Route route3 = new Route("Antalya", "Izmir");
        Route route4 = new Route("Izmir", "Antalya");
        Route route5 = new Route("Izmir", "Ankara");
        Route route6 = new Route("Antalya", "Ankara");
        Route route7 = new Route("Ankara", "Izmir");
        Route route8 = new Route("Istanbul", "Izmir");
        Route route9 = new Route("Istanbul", "Ankara");

        Route[] root = {route, route1, route2, route3, route4, route5, route6, route7, route8, route9};
        Quick.sort(root);
        //sorting root array which is including objects **(source and destination)
        printArray(root);
        System.out.println("Step 6 has been completed.");

        //  Quick.sort1(root);

    }

    public static void printArray(Route[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i].source + " " + arr[i].destination);
        System.out.println();
    }


    private static int[] readFiles(String file) {
        try {
            File f = new File(file);
            Scanner s = new Scanner(f);
            int n = 0;

            while (s.hasNextInt()) {
                n++;
                s.nextInt();
            }
            int S[] = new int[n];

            Scanner s1 = new Scanner(f);

            for (int i = 0; i < S.length; i++)
                S[i] = s1.nextInt();

            return S;

        } catch (Exception e) {
            return null;
        }
    }

    private static double[] readfiles(String file) {
        try {
            File f = new File(file);
            Scanner s = new Scanner(f);
            int n = 0;

            while (s.hasNextDouble()) {
                n++;
                s.nextDouble();
            }
            double S[] = new double[n];

            Scanner s1 = new Scanner(f);

            for (int i = 0; i < S.length; i++)
                S[i] = s1.nextDouble();
            return S;
        } catch (Exception e) {
            return null;
        }
    }

    public static void printArray(Integer[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void printArray(Double[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
