//-----------------------------------------------------
// Author: Ömer Alper Güzel
// ID: 16057474442
// Section: 5
// Assignment: 1/Q1A
//-----------------------------------------------------

package CMPE223.Homework.HW1A;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Q1A {
//We are initialize our array from text file by deleting spaces, taking values and initializing the length of 2D Array.
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
//Now we have to sort our 2D array values within a List
    public static List<Integer> spiralOrder(int[][] matrix) {
//Create our new list to put our sorted values in proper order.
            List<Integer> list = new ArrayList<>();
            int left = 0, right = matrix[0].length;
            int top = 0, bottom = matrix.length;
            while(left < right && top < bottom){
                //Move top to bottom (step 1)
                for(int i = top; i < bottom; i++){
                    list.add(matrix[i][left]);
                }
                left+=1;
                //Move left to right (step 2)
                for(int i = left; i < right; i++){
                    list.add(matrix[bottom - 1][i]);
                }
                bottom-=1;
                //Move bottom to top (step 3)
                for(int i = bottom - 1; i >= top; i--){
                    list.add(matrix[i][right - 1]);
                }
                right-=1;
                //Move right to left (step 4)
                for(int i = right - 1; i >= left; i--){
                    list.add(matrix[top][i]);
                }
                top+=1;
                //end condition
                if (!(left < right && top < bottom)){
                    break;
                }
            }
            return list;
        }

    public static void main (String[] args) throws FileNotFoundException {
        //Write our filename (and directory if needed) to import our txt file.
        System.out.println("Input filename:");
        Scanner console = new Scanner(System.in);
        File file = new File(console.nextLine());
        Scanner scan = new Scanner(file);

        ArrayList<String> lines = new ArrayList<String>();
        while(scan.hasNext()){
            lines.add(scan.nextLine());
        }
        //Initialize 2D array by importing our text file to this object.
        int[][] matrix = initializeArray(lines);
        //Create a list by sort 2D array values with spiral index. 
        List<Integer> list = spiralOrder(matrix);
        //Finally print our list which included our values sorted. 
        System.out.println(list);

    }
    //Sorry I weren't manage to write proper report to Q1A due to my time-constraints and improper planning. Again, I'm very sorry about that.

}
