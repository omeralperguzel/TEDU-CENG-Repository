package CMPE223SS.HW2.Q1.Q1Old2;

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

    public static <T extends Comparable<T>> void insertionSort(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            T key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).compareTo(key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
    

    public static <T extends Comparable<T>> void mergeSort(List<T> list) {
        if (list.size() > 1) {
            int mid = list.size() / 2;
            List<T> left = list.subList(0, mid);
            List<T> right = list.subList(mid, list.size());
    
            mergeSort(left);
            mergeSort(right);
    
            int i = 0, j = 0, k = 0;
            while (i < left.size() && j < right.size()) {
                if (left.get(i).compareTo(right.get(j)) > 0) {
                    list.set(k++, left.get(i++));
                } else {
                    list.set(k++, right.get(j++));
                }
            }
            while (i < left.size()) {
                list.set(k++, left.get(i++));
            }
            while (j < right.size()) {
                list.set(k++, right.get(j++));
            }
            Collections.reverse(list.subList(0, mid));
        }
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
    
}
