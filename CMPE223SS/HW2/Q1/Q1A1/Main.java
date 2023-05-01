package CMPE223SS.HW2.Q1.Q1A1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Step 1
        List<Integer> intList = readIntegersFromFile("integers.txt");
        System.out.println("Integers are reading from the integers.txt file, the array is:");
        System.out.println(intList);
        insertionSort(intList);
        System.out.println("The array has been sorted in increasing order by using the insertion sort algorithm:");
        System.out.println(intList);
        System.out.println("Step 1 has been completed.\n");

        // Step 2
        List<Double> doubleList = readDoublesFromFile("doubles.txt");
        System.out.println("Doubles are reading from the doubles.txt file, the array is:");
        System.out.println(doubleList);
        insertionSort(doubleList);
        System.out.println("The array of double values has been sorted in increasing order by using the insertion sort algorithm:");
        System.out.println(doubleList);
        System.out.println("Step 2 has been completed.\n");

        // Step 3
        List<Integer> intList2 = new ArrayList<>(intList);
        System.out.println("The original array is:");
        System.out.println(intList2);
        mergeSort(intList2);
        System.out.println("The array of integer values has been sorted in descending order by using the merge sort algorithm is:");
        System.out.println(intList2);
        System.out.println("Step 3 has been completed.\n");

        // Step 4
        List<Student> studentList = readStudentsFromFile("student.txt");
        System.out.println("The original list of students is:");
        for (Student s : studentList) {
            System.out.println(s);
        }
        sortStudentsBySemesterNo(studentList);
        System.out.println("The list of students sorted by grade in descending order is:");
        for (Student s : studentList) {
            System.out.println(s);
        }
        System.out.println("Step 4 has been completed.\n");

        // Step 5 (not applicable in Java as arrays are passed by reference)
        System.out.println("Step 5 has been completed.\n");

        // Step 6
        List<Student> studentList2 = readStudentsFromFile("student.txt");
        System.out.println("The original list of students is:");
        for (Student s : studentList2) {
            System.out.println(s);
        }
        sortStudentsByStudentID(studentList2);
        System.out.println("The list of students sorted by student ID is:");
        for (Student s : studentList2) {
            System.out.println(s.getId() + ": " + s.getName() + " " + s.getSemesterNo());
        }
        System.out.println("Step 6 has been completed.\n");

        // Step 7
        List<Student> studentList3 = readStudentsFromFile("student.txt");
        System.out.println("The original list of students is:");
        for (Student s : studentList3) {
            System.out.println(s);
        }
        sortStudentsByName(studentList3);
        System.out.println("The list of students sorted by name is:");
        for (Student s : studentList3) {
            System.out.println(s.getId() + ": " + s.getName() + " " + s.getSemesterNo());
        }
        System.out.println("Step 7 has been completed.");
    }
    
    public static List<Integer> readIntegersFromFile(String filename) {
        List<Integer> intList = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                intList.add(Integer.parseInt(scanner.nextLine()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return intList;
    }

    public static List<Double> readDoublesFromFile(String filename) {
        List<Double> doubleList = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                doubleList.add(Double.parseDouble(scanner.nextLine()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return doubleList;
    }

    public static List<Student> readStudentsFromFile(String filename) {
        List<Student> studentList = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] studentInfo = scanner.nextLine().split(","); 
                String name = studentInfo[0].trim();
                int id = Integer.parseInt(studentInfo[1].trim());;
                int semesterNo = Integer.parseInt(studentInfo[2].trim());;
                studentList.add(new Student(name, id, semesterNo));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public static void sortStudentsByStudentID(List<Student> studentList) {
        Collections.sort(studentList, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return (int) (s1.getId() - s2.getId());
            }
        });
    }

    public static void sortStudentsBySemesterNo(List<Student> studentList) {
        Collections.sort(studentList, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s2.getSemesterNo() - s1.getSemesterNo();
            }
        });
    }

    public static void sortStudentsByName(List<Student> studentList) {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getName().compareTo(s2.getName());
            }
        });
    }

    public static void sort(Comparable[] a) { 
        /* See Algorithms 2.1, 2.2, 2.3, 2.4, 2.5, or 2.7. */ 
    }
    private static boolean less(Comparable v, Comparable w) { 
        return v.compareTo(w) < 0; 
    }
    private static void exch(Comparable[] a, int i, int j) { 
        Comparable t = a[i]; a[i] = a[j]; a[j] = t; 
    }
    private static void show(Comparable[] a) { 
        // Print the array, on a single line.
        for (int i = 0; i < a.length; i++) 
          StdOut.print(a[i] + " ");
        StdOut.println();
    }
    public static boolean isSorted(Comparable[] a) { 
        // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++)
        if (less(a[i], a[i-1])) return false;
        return true;
    }

    public class Insertion {
        public static void sort(Comparable[] a) { 
            // Sort a[] into increasing order.
            int N = a.length;
            for (int i = 1; i < N; i++) { 
                // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
                for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
            }
        }
        // See page 245 for less(), exch(), isSorted(), and main().
    }

    public class Merge {
        private static Comparable[] aux; // auxiliary array for merges
        public static void sort(Comparable[] a) {
            aux = new Comparable[a.length]; // Allocate space just once.
            sort(a, 0, a.length - 1);
        }
        
        private static void sort(Comparable[] a, int lo, int hi) { 
            // Sort a[lo..hi].
            if (hi <= lo) return;
            int mid = lo + (hi - lo)/2;
            sort(a, lo, mid); // Sort left half.
            sort(a, mid+1, hi); // Sort right half.
            merge(a, lo, mid, hi); // Merge results (code on page 271).
        }
    }

    public class Quick {
        public static void sort(Comparable[] a) {
            StdRandom.shuffle(a); // Eliminate dependence on input.
            sort(a, 0, a.length - 1);
        }
        private static void sort(Comparable[] a, int lo, int hi) {
            if (hi <= lo) return;
            int j = partition(a, lo, hi); // Partition (see page 291).
            sort(a, lo, j-1); // Sort left part a[lo .. j-1].
            sort(a, j+1, hi); // Sort right part a[j+1 .. hi].
        }
    }

    private static int partition(Comparable[] a, int lo, int hi) { 
        // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi+1; // left and right scan indices
        Comparable v = a[lo]; // partitioning item
        while (true) { 
            // Scan right, scan left, check for scan complete, and exchange.
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j); // Put v = a[j] into position
        return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
    }

    /*public static void main(String[] args) { 
        // Read strings from standard input, sort them, and print.
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }*/ 
}
