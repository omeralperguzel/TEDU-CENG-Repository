import java.io.*;
import java.util.*;

public class MazeSolver {

    static class Point {
        int x, y;
    
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }
    
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    // Method to read the maze from a file
    static char[][] readMaze(String filename) throws IOException {
        List<char[]> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.toCharArray());
            }
        }
    
        char[][] maze = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            maze[i] = lines.get(i);
        }
    
        return maze;
    }

    // Revised solveMaze method
    static void solveMaze(char[][] maze, int x, int y, String path, List<String> solutions, boolean[][] visited) {
        if (x < 0 || y < 0 || x >= maze.length || y >= maze[0].length || maze[x][y] == '+' || maze[x][y] == '-' || maze[x][y] == '|' || visited[x][y]) {
            return; // Out of bounds, hit a wall, or already visited
        }
    
        if (maze[x][y] == 'E') { // Found a treasure
            solutions.add(path + 'E');
            return;
        }

        visited[x][y] = true;
        char currentChar = maze[x][y];

        // Recursive calls for all four directions
        solveMaze(maze, x + 1, y, path + currentChar, solutions, visited);
        solveMaze(maze, x - 1, y, path + currentChar, solutions, visited);
        solveMaze(maze, x, y + 1, path + currentChar, solutions, visited);
        solveMaze(maze, x, y - 1, path + currentChar, solutions, visited);
    
        visited[x][y] = false; // Backtrack
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the maze file: ");
        String filename = scanner.nextLine();
        scanner.close();
    
        char[][] maze = readMaze(filename);
        List<String> solutions = new ArrayList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
    
        // Find the starting point (first lowercase character)
        Point start = findStartingPoint(maze);
        if (start == null) {
            System.out.println("No starting point found in the maze.");
            return;
        }
    
        solveMaze(maze, start.x, start.y, "", solutions, visited);
    
        solutions.sort(Comparator.comparingInt(String::length));
    
        if (solutions.size() > 0) {
            System.out.println(solutions.size() + " treasures are found.");
            System.out.println("Paths are:");
            int count = 1;
            for (String solution : solutions) {
                System.out.println(count + ") " + solution);
                count++;
            }
        } else {
            System.out.println("0 treasures are found.");
        }
    }
    
    // Method to find the starting point in the maze
    static Point findStartingPoint(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (Character.isLowerCase(maze[i][j])) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }
    
}
