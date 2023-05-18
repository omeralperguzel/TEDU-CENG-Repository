import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main2Alt2 {

    static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    static int[][] readMatrix(String fileName) throws IOException {
        int numRows = 0;
        int numCols = 0;
        
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            numRows++;
            String[] lineArray = line.split(" ");
            if (numRows == 1) {
                numCols = lineArray.length;
            } else if (lineArray.length != numCols) {
                throw new IllegalArgumentException("All rows must have the same number of columns");
            }
        }
        br.close();
        
        int[][] matrix = new int[numRows][numCols];
    
        br = new BufferedReader(new FileReader(fileName));
        int rowIndex = 0;
        while ((line = br.readLine()) != null) {
            String[] lineArray = line.split(" ");
            for (int colIndex = 0; colIndex < numCols; colIndex++) {
                matrix[rowIndex][colIndex] = Integer.parseInt(lineArray[colIndex]);
            }
            rowIndex++;
        }
        br.close();
    
        return matrix;
    }

    static Node[] createLinkedList(int[][] matrix) {
        Node head[] = new Node[matrix.length];
        Node current = null;
        for (int i = 0; i < matrix.length; i++){
            head[i] = new Node(matrix[0][0]);
        }
        for (int headindex = 0; headindex < matrix.length; headindex++){
            current = head[headindex];
            for (int y = 1; y < (headindex+1); y++){
                current.next = new Node(matrix[y][0]);
                current = current.next;
                }
            for (int x = 1; x < matrix[0].length; x++){
                current.next = new Node(matrix[headindex][x]);
                current = current.next;
            }
        }
        return head;
    }

    static void printMatrixType(String type) {
        System.out.println("It is a " + type + " matrix.");
    }

// Function to check if the matrix is a type 1 matrix
    static boolean isType1(Node head[]) {
        boolean result = false;
        for(int i = 0; i < head.length; i++){
            int count = 0;
            Node current = head[i];
            while (current != null) {
                System.out.print(current.data);
                if (current.data >= 0 && current.next != null) {
                    if(current.next.data > current.data){
                        count++;
                        if (count >= 2) { // add a check to see if there are no negative numbers between the positive numbers
                            result = true;
                        }
                    }
                    else {
                        count = 0;
                    }
                } 
                else {
                    count = 0;
                }
                current = current.next;
            }  
            System.out.println();          
        }
        return result;
    }

    //A type 2 matrix contains at least one negative number followed by another negative number.

    // Function to check if the matrix is a type 2 matrix
    static boolean isType2(Node head[]) {
        boolean result = false;
        for(int i = 0; i < head.length; i++){
            Node current = head[i];
            while (current != null) {
                if (current.data < 0 && current.next != null) {
                    if(current.next.data < 0){
                        result = true;
                    }
                } 
                current = current.next;
            }            
        }
        return result;
    }

    // Function to check if the matrix is a type 3 matrix
    static boolean isType3(Node head[]) {
        return isType1(head) && isType2(head);
    }

    // Function to print the matrix. Used for test purposes.
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

        // Print the matrix. Used for test purposes.
        System.out.println("The matrix is:");
        printMatrix(matrix);

        // Create a linked list from the matrix
        Node head[] = createLinkedList(matrix);

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