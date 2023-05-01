package CMPE223SS.HW2.Q1.Q1Old;

//This code does step 5 perfectly, but it does not do step 6 and 7.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // Read the student records from the file
        ArrayList<Student> students = readStudentsFromFile("student.txt");

        // Sort the student records using Quick Sort
        quickSort(students, 0, students.size() - 1);

        // Print the sorted student records
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static ArrayList<Student> readStudentsFromFile(String fileName) throws FileNotFoundException {
        ArrayList<Student> students = new ArrayList<>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] tokens = scanner.nextLine().split(",");
            String name = tokens[0];
            long id = Long.parseLong(tokens[1]);
            int semesterNo = Integer.parseInt(tokens[2]);
            Student student = new Student(name, id, semesterNo);
            students.add(student);
        }
        scanner.close();
        return students;
    }

    private static void quickSort(ArrayList<Student> students, int lo, int hi) {
        if (lo < hi) {
            int p = partition(students, lo, hi);
            quickSort(students, lo, p - 1);
            quickSort(students, p + 1, hi);
        }
    }

    private static int partition(ArrayList<Student> students, int lo, int hi) {
        Student pivot = students.get(hi);
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (students.get(j).compareTo(pivot) < 0) {
                i++;
                Collections.swap(students, i, j);
            }
        }
        Collections.swap(students, i + 1, hi);
        return i + 1;
    }
}
