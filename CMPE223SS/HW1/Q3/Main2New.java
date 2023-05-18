//-----------------------------------------------------
//Title: Main
//Author: Ömer Alper Güzel
//ID: 16057474442
//Section: 2
//Assignment: 1 Q3
//Description: This is a Java program that reads a matrix from a file and checks if it is a type 1, type 2, or type 3 matrix. 
//-----------------------------------------------------

//Importing the necessary libraries
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main2New {

    // Define a Node class for the linked list
    static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    // Function to read the matrix from the file
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

    // Function to create a linked list from the matrix
    static Node createLinkedList(int[][] matrix) {
        Node head = new Node(matrix[0][0]);
        Node currentRow = head;

        //Initialize the linked list
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                Node newNode = new Node(matrix[i][j]);
                if (j == 0) {
                    currentRow = newNode;
                    head = newNode;
                } 
                else {
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
        boolean hasNegative = false; // flag to check if there are any negative numbers between the positive numbers
        while (head != null) {
            if (head.data > 0) {
                count++;
                if (count == 3 && !hasNegative) { // add a check to see if there are no negative numbers between the positive numbers
                    return true;
                }
            } else {
                count = 0;
                hasNegative = false; // reset the flag if a negative number is encountered
            }
            if (head.data < 0) {
                hasNegative = true; // set the flag if a negative number is encountered
            }
            head = head.next;
        }
        return false;
    }

    //A type 2 matrix contains at least one negative number followed by another negative number.

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

    // Function to print the matrix. Used for test purposes.
    /*static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }*/

    public static void main(String[] args) throws IOException {
        // Read the matrix from the file
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();
        int[][] matrix = readMatrix(fileName);

        // Print the matrix. Used for test purposes.
        /*System.out.println("The matrix is:");
        printMatrix(matrix);*/

        // Create a linked list from the matrix
        Node head = createLinkedList(matrix);

        // Check if the matrix is a type 1 matrix
        if (isType1(head)) {
            System.out.println("It is a type 1 matrix.");
        } 
        // Check if the matrix is a type 2 matrix
        else if (isType2(head)) {
            System.out.println("It is a type 2 matrix.");
        } 
        // Check if the matrix is a type 3 matrix
        else if (isType3(head)) {
            System.out.println("It is a type 3 matrix.");
        } 
        // Check if the matrix is none of the above
        else {
            System.out.println("It is not one of these types.");
        }
    }           

}