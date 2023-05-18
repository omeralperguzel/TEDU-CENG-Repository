//-----------------------------------------------------
//Title: Main
//Author: Ömer Alper Güzel
//Section: 2
//Assignment: 1 Q3
//Description: This is a Java program that reads a matrix from a file and checks if it is a type 1, type 2, or type 3 matrix. 
//-----------------------------------------------------
//YAFES VERSION


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main3 {

    // Define a Node class for the linked list
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    // Function to create a linked list from the matrix
    static Node createLinkedList(int[][] matrix) {
        Node head = new Node(matrix[0][0]);
        Node currentRow = head;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                Node newNode = new Node(matrix[i][j]);
                if (j == 0) {
                    currentRow = newNode;
                    head = newNode;
                } else {
                    currentRow.next = newNode;
                    currentRow = newNode;
                }
            }
        }
        return head;
    }

    // Function to check if the matrix is a type 1 matrix
    static boolean isType1(Node head) {
        int count = 0;
        while (head != null) {
            if (head.data > 0) {
                count++;
                if (count == 3) {
                    return true;
                }
            } else {
                count = 0;
            }
            head = head.next;
        }
        return false;
    }

    // Function to check if the matrix is a type 2 matrix
    static boolean isType2(Node head) {
        while (head != null) {
            if (head.data < 0 && head.next != null && head.next.data < 0) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    // Function to check if the matrix is a type 3 matrix
    static boolean isType3(Node head) {
        return isType1(head) && isType2(head);
    }

    static int[][] readMatrix(String fileName) throws IOException {
        int[][] matrix;
        int rowCount = 0;
        int colCount = 0;

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            rowCount++;
            String[] lineArray = line.split(" ");
            if (rowCount == 1) {
                colCount = lineArray.length;
            } else if (lineArray.length != colCount) {
                throw new IllegalArgumentException("All rows must have the same number of columns");
            }
        }
        br.close();
    
        // Allocate the 2D integer array
        matrix = new int[rowCount][colCount];
    
        // Read the file again and populate the matrix
        br = new BufferedReader(new FileReader(fileName));
        int rowIndex = 0;
        while ((line = br.readLine()) != null) {
            String[] lineArray = line.split(" ");
            for (int colIndex = 0; colIndex < colCount; colIndex++) {
                matrix[rowIndex][colIndex] = Integer.parseInt(lineArray[colIndex]);
            }
            rowIndex++;
        }
        br.close();
    
        return matrix;
    }

    // Function to print the matrix
    static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        // Read the matrix from the file
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();
        int[][] matrix = readMatrix(fileName);
    
        // Print the matrix
        System.out.println("The matrix is:");
        printMatrix(matrix);
    
        // Create a linked list from the matrix
        Node head = createLinkedList(matrix);
    
        // Check if the matrix is a type 1 matrix
        if (isType1(head)) {
            System.out.println("It is a type 1 matrix.");
        } else if (isType2(head)) {
            System.out.println("It is a type 2 matrix.");
        } else if (isType3(head)) {
            System.out.println("It is a type 3 matrix.");
        } else {
            System.out.println("It is not one of these types.");
        }
    }
}