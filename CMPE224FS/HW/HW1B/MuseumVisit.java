package Try4C;

import java.util.*;

public class MuseumVisit {
    static class Graph {
        int vertices;
        int[][] travelTime; // Travel time between museums
        int[] visitTime; // Visit time for each museum
        List<List<Integer>> adjacentList;

        public Graph(int vertices) {
            this.vertices = vertices;
            travelTime = new int[vertices][vertices];
            visitTime = new int[vertices];
            adjacentList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjacentList.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v, int weight) {
            adjacentList.get(u).add(v);
            adjacentList.get(v).add(u);
            travelTime[u][v] = weight;
            travelTime[v][u] = weight;
        }

        void setVisitTime(int vertex, int time) {
            visitTime[vertex] = time;
        }
    }

    private static int calculateWeight(Graph graph, List<Integer> path) {
    int weight = 0;
    for (int i = 0; i < path.size() - 1; i++) {
        int u = path.get(i);
        int v = path.get(i + 1);
        weight += graph.travelTime[u][v];
    }
    return weight;
}

    private static int calculateTotalTime(Graph graph, List<Integer> path) {
        int totalTime = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            int current = path.get(i);
            int next = path.get(i + 1);
            totalTime += graph.travelTime[current][next] + graph.visitTime[current];
        }
        totalTime += graph.visitTime[path.get(path.size() - 1)]; // Add visit time for the last museum
        return totalTime;
    }


    // Function to find the minimum path (other helper methods remain the same)
    private static void findMinimumPath(Graph graph) {
        List<Integer> vertices = new ArrayList<>();
        for (int i = 0; i < graph.vertices; i++) {
            vertices.add(i);
        }
    
        List<Integer> bestPath = null;
        int minWeight = Integer.MAX_VALUE;
        int minTime = Integer.MAX_VALUE;
    
        do {
            if (isValidPath(graph, vertices)) {
                int currentWeight = calculateWeight(graph, vertices);
                if (currentWeight < minWeight) {
                    minWeight = currentWeight;
                    bestPath = new ArrayList<>(vertices);
                    minTime = calculateTotalTime(graph, vertices);
                }
            }
        } while (nextPermutation(vertices));
    
        if (bestPath == null) {
            System.out.println("-1");
        } else {
            System.out.println(minWeight);
            for (int v : bestPath) {
                System.out.print((v + 1) + " "); // It is converting back to 1-indexed vertices.
            }
            System.out.println();
        }
    }
    
    private static boolean isValidPath(Graph graph, List<Integer> path) {
        for (int i = 0; i < path.size() - 1; i++) {
            int u = path.get(i);
            int v = path.get(i + 1);
            if (!graph.adjacentList.get(u).contains(v)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt(); // Number of museums
        int N = scanner.nextInt(); // Number of roads

        Graph graph = new Graph(M);

        // Assume the visit time for each museum is 0
        for (int i = 0; i < M; i++) {
            graph.setVisitTime(i, 0);
        }

        for (int i = 0; i < N; i++) {
            int u = scanner.nextInt() - 1; // Convert to 0-indexed
            int v = scanner.nextInt() - 1; // Convert to 0-indexed
            int w = scanner.nextInt();
            graph.addEdge(u, v, w);
        }

        findMinimumPath(graph);
        scanner.close();
    }

    private static boolean nextPermutation(List<Integer> array) {
        int i = array.size() - 1;
        
        // Find the rightmost element that is smaller than the element next to it.
        while (i > 0 && array.get(i - 1) >= array.get(i))
            i--;
        if (i <= 0)
            return false;

        int j = array.size() - 1;

        // Find the rightmost element that is larger than the pivot.
        while (array.get(j) <= array.get(i - 1))
            j--;

        // Swap the pivot with j for getting the next permutation.
        int temp = array.get(i - 1);
        array.set(i - 1, array.get(j));
        array.set(j, temp);

        j = array.size() - 1;

        // Reverse the suffix array from i to the end for getting the next permutation.
        while (i < j) {
            temp = array.get(i);
            array.set(i, array.get(j));
            array.set(j, temp);
            i++;
            j--;
        }

        return true;
    }
}
