package CMPE223FS.Homework.HW1.Q1A;

import java.util.ArrayList;
//import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class SpiralMatrix {

    public static int[][] initializeArray(ArrayList<String> lines){
        int totalRow = 0;
        int totalColumn = lines.size();
        //char[][] myArray = new char[totalRow][];

        String row = lines.get(0);
        String[] rowElements = row.split(" ");
        totalRow = rowElements.length;

        int[][] resultMatrix = new int[totalColumn][totalRow];
        /*for (int indexColumn = 0; indexColumn < totalColumn; indexColumn++){
            String[] rowElementStrings = lines.get(indexColumn).split(" ");
            for (int indexRow = 0; indexRow < totalRow; indexRow++){
                resultMatrix[indexColumn][indexRow] = Integer.parseInt(rowElementStrings[indexRow]);
            }
        }*/
        for (int indexColumn = 0; indexColumn < totalColumn; indexColumn++){
            String[] rowElementStrings = lines.get(indexColumn).split(" ");
            for (int indexRow = 0; indexRow < totalRow; indexRow++){
                resultMatrix[indexColumn][indexRow] = Integer.parseInt(rowElementStrings[indexRow]);
            }
        }

        /*try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            for (int row = 0; scanner.hasNextLine() && row < totalRow; row++) {
                myArray[row] = scanner.nextLine().toCharArray();
            }
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }*/

        return resultMatrix;
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> list = new ArrayList<>();
            int left = 0, right = matrix[0].length;
            int top = 0, bottom = matrix.length;
            while(left < right && top < bottom){
                //top to bottom (1)
                for(int i = top; i < bottom; i++){
                    list.add(matrix[i][left]);
                }
                left+=1;
                //left to right (2)
                for(int i = left; i < right; i++){
                    list.add(matrix[bottom - 1][i]);
                }
                bottom-=1;
                //bottom to top (3)
                for(int i = bottom - 1; i >= top; i--){
                    list.add(matrix[i][right - 1]);
                }
                right-=1;
                //right to left (4)
                for(int i = right - 1; i >= left; i--){
                    list.add(matrix[top][i]);
                }
                top+=1;
                //condition
                if (!(left < right && top < bottom)){
                    break;
                }


            }
            return list;
        }

    public static void main (String[] args) throws FileNotFoundException {
        System.out.println("Input filename:");
        Scanner console = new Scanner(System.in);
        File file = new File(console.nextLine());
        Scanner scan = new Scanner(file);

        ArrayList<String> lines = new ArrayList<String>();
        while(scan.hasNext()){
            lines.add(scan.nextLine());
        }
        int[][] matrix = initializeArray(lines);
        List<Integer> list = spiralOrder(matrix);
        //LinkedList<Integer> list = spiralOrder(matrix, -1);
        System.out.println(list);

        /*for (int i = 0; i < myArray.length; i++)

            // Loop through all elements of current row
            for (int j = 0; j < myArray[i].length; j++)
                System.out.print(myArray[i][j]);*/

    }

}
