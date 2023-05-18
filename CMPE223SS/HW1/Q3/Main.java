//-----------------------------------------------------
//Title: Main
//Author: Ömer Alper Güzel
//ID: 16057474442
//Section: 2
//Assignment: 1 Q3
//Description: This is a Java program that reads a matrix from a file and checks if it is a type 1, type 2, or type 3 matrix. 
//-----------------------------------------------------

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
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

    // Function to print the matrix
    static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Function to read the matrix from the file
    static int[][] readMatrix(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        String[] lineArray = line.split(" ");
        int[][] matrix = new int[Integer.parseInt(lineArray[0])][Integer.parseInt(lineArray[1])];
        int i = 0;
        while ((line = br.readLine()) != null) {
            lineArray = line.split(" ");
            for (int j = 0; j < lineArray.length; j++) {
                matrix[i][j] = Integer.parseInt(lineArray[j]);
            }
            i++;
        }
        br.close();
        return matrix;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();
        int[][] matrix = readMatrix(fileName);
        System.out.println("The matrix is:");
        printMatrix(matrix);
        Node head = createLinkedList(matrix);
        if (isType1(head)) {
            System.out.println("The matrix is a type 1 matrix.");
        } else if (isType2(head)) {
            System.out.println("The matrix is a type 2 matrix.");
        } else if (isType3(head)) {
            System.out.println("The matrix is a type 3 matrix.");
        } else {
            System.out.println("The matrix is not a type 1, type 2, or type 3 matrix.");
        }
        scanner.close();
    }
}