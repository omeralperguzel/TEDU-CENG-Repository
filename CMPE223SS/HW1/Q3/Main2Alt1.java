import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main2Alt1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();
        scanner.close();
        
        int[][] matrix = readMatrixFromFile(fileName);
        int type = getMatrixType(matrix);
        
        switch (type) {
            case 1:
                System.out.println("It is a type 1 matrix.");
                break;
            case 2:
                System.out.println("It is a type 2 matrix.");
                break;
            case 3:
                System.out.println("It is a type 3 matrix.");
                break;
            default:
                System.out.println("It is not one of these types.");
                break;
        }
    }
    
    private static int[][] readMatrixFromFile(String fileName) {
        int[][] matrix = null;
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            int numRows = 0;
            int numCols = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] values = line.split("\\s+");
                if (matrix == null) {
                    numCols = values.length;
                    matrix = new int[numCols][];
                }
                matrix[numRows] = new int[numCols];
                for (int j = 0; j < numCols; j++) {
                    matrix[numRows][j] = Integer.parseInt(values[j]);
                }
                numRows++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            System.exit(1);
        }
        return matrix;
    }
    
    private static int getMatrixType(int[][] matrix) {
        boolean hasConsecutivePositiveNumbers = false;
        boolean hasNegativeNumbers = false;
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols - 1; j++) {
                int current = matrix[i][j];
                int next = matrix[i][j+1];
                if (current > 0 && next > 0 && current+1 == next) {
                    hasConsecutivePositiveNumbers = true;
                } else if (current < 0 && next < 0) {
                    hasNegativeNumbers = true;
                }
            }
        }
        if (hasConsecutivePositiveNumbers && hasNegativeNumbers) {
            return 3;
        } else if (hasConsecutivePositiveNumbers) {
            return 1;
        } else if (hasNegativeNumbers) {
            return 2;
        } else {
            return 0;
        }
    }
}