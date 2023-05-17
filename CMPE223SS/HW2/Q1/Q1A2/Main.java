package CMPE223SS.HW2.Q1.Q1A2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Step 1: Insertion sort for an array of integers
        int[] arr = readIntegersFromFile("integers.txt");
        System.out.println("Integers are reading from the integers.txt file, the array is:");
        System.out.println(Arrays.toString(arr));
        insertionSort(arr);
        System.out.println("The array has been sorted in increasing order by using the insertion sort algorithm:");
        System.out.println(Arrays.toString(arr));
        System.out.println("Step 1 has been completed.\n");

        // Step 2: Insertion sort for an array of integers in descending order
        int[] arr2 = readIntegersFromFile("integers.txt");
        System.out.println("The array of integers that has been sorted in decreasing order by using the insertion sort algorithm:");
        insertionSortDescending(arr2);
        System.out.println(Arrays.toString(arr2));
        System.out.println("Step 2 has been completed.\n");

        // Step 3: Insertion sort for an array of doubles
        double[] arr3 = readDoublesFromFile("doubles.txt");
        System.out.println("Doubles are reading from the doubles.txt file, the array is:");
        System.out.println(Arrays.toString(arr3));
        insertionSortDouble(arr3);
        System.out.println("The array of double values has been sorted in increasing order by using the insertion sort algorithm:");
        System.out.println(Arrays.toString(arr3));
        System.out.println("Step 3 has been completed.\n");

        // Step 4: Merge sort for an array of integers in descending order
        int[] arr4 = readIntegersFromFile("integers.txt");
        System.out.println("The original array is:");
        System.out.println(Arrays.toString(arr4));
        mergeSortDescending(arr4, 0, arr4.length - 1);
        System.out.println("The array of integer values has been sorted in descending order by using the merge sort algorithm is:");
        System.out.println(Arrays.toString(arr4));
        System.out.println("Step 4 has been completed.\n");

        // Step 5: Student class implementing Comparable interface
        Student[] students = readStudentsFromFile("students.txt");
        Arrays.sort(students);
        System.out.println("Step 5 has been completed.\n");
        for (Student student : students) {
            System.out.println(student);
        }

        // Step 6: Quick sort for an array of Student objects
        Student[] students2 = readStudentsFromFile("students.txt");
        quickSort(students2, 0, students2.length - 1);
        System.out.println("Step 6 has been completed.\n");
        for (Student student : students2) {
            System.out.println(student);
        }

        // Step 7: Quick sort partition method to sort elements in descending order
        Student[] students3 = readStudentsFromFile("students.txt");
        quickSortDescending(students3, 0, students3.length - 1);
        System.out.println("Step 7 has been completed.\n");
        for (Student student : students3) {
            System.out.println(student);
        }
    }

    // Step 1: Insertion sort for an array of integers in increasing order
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // Step 2: Insertion sort for an array of integers in descending order
    public static void insertionSortDescending(int[] arr) {
        int n = arr.length;
        for (int i = n - 2; i >= 0; i--) {
            int key = arr[i];
            int j = i + 1;
            while (j < n && arr[j] > key) {
                arr[j - 1] = arr[j];
                j++;
            }
            arr[j - 1] = key;
        }
    }

    // Step 3: Insertion sort for an array of doubles 
    public static void insertionSortDouble(double[] arr) {
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

    // Step 4: Merge sort for an array of integers in descending order
    public static void mergeSortDescending(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSortDescending(arr, l, m);
            mergeSortDescending(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] >= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Step 5: Student class implementing Comparable interface 
    public static class Student implements Comparable<Student> {
        private String name;
        private long id;
        private int semesterNo;

        public Student(String name, long id, int semesterNo) {
            this.name = name;
            this.id = id;
            this.semesterNo = semesterNo;
        }

        public String getName() {
            return name;
        }

        public long getId() {
            return id;
        }

        public int getSemesterNo() {
            return semesterNo;
        }

        @Override
        public int compareTo(Student other) {
            if (this.id != other.id) {
                return Long.compare(this.id, other.id);
            } else if (this.semesterNo != other.semesterNo) {
                return Integer.compare(this.semesterNo, other.semesterNo);
            } else {
                return this.name.compareTo(other.name);
            }
        }

        @Override
        public String toString() {
            return id + ": " + name + " " + semesterNo;
        }
    }

    // Step 6: Quick sort for an array of Student objects 
    public static void quickSort(Student[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(Student[] arr, int low, int high) {
        Student pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) < 0) {
                i++;
                Student temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Student temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Step 7: Quick sort partition method to sort elements in descending order
    public static void quickSortDescending(Student[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionDescending(arr, low, high);
            quickSortDescending(arr, low, pi - 1);
            quickSortDescending(arr, pi + 1, high);
        }
    }

    public static int partitionDescending(Student[] arr, int low, int high) {
        Student pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) > 0) {
                i++;
                Student temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Student temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Helper method to read integers from a file
    public static int[] readIntegersFromFile(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }
            scanner.close();
            return arr;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Helper method is created to read doubles from a file 
    public static double[] readDoublesFromFile(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            int n = scanner.nextInt();
            double[] arr = new double[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextDouble();
            }
            scanner.close();
            return arr;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Helper method is created to read students from a file
    public static Student[] readStudentsFromFile(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            Student[] students = new Student[10];
            for (int i = 0; i < 10; i++) {
                String[] tokens = scanner.nextLine().split(",");
                String name = tokens[0];
                long id = Long.parseLong(tokens[1]);
                int semesterNo = Integer.parseInt(tokens[2].trim());
                students[i] = new Student(name, id, semesterNo);
            }
            scanner.close();
            return students;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}