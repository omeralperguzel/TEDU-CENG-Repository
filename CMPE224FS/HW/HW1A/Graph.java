import java.util.*;

public class Graph {
    private int V;   // Shows the number of vertices
    private LinkedList<Integer>[] adjacent; // Shows the adjacency lists

    // This is a constructor for the Graph class
    Graph(int v) {
        V = v;
        adjacent = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adjacent[i] = new LinkedList();
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adjacent[v].add(w);
        adjacent[w].add(v); // Since the graph is undirected
    }

    // Other graph methods (like BFS, DFS) will be implemented here...
    int BFS(int startVertex, int endVertex) {
        boolean visited[] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        int distance[] = new int[V];

        visited[startVertex] = true;
        queue.add(startVertex);
        distance[startVertex] = 0;

        while (queue.size() != 0) {
            int s = queue.poll();

            Iterator<Integer> i = adjacent[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    distance[n] = distance[s] + 1;
                    queue.add(n);
                }
            }
        }
        return distance[endVertex];
    }

    // It finds the new tracks between X and Y. Then it returns the list of new tracks.
    ArrayList<int[]> findNewTracks(int X, int Y) {
        int originalDistance = BFS(X, Y);
        ArrayList<int[]> newTracks = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                // It checks if i and j are not directly connected.
                if (!adjacent[i].contains(j)) {
                    // It temporarily adds the edge and checks the distance between X and Y.
                    addEdge(i, j);
                    int newDistance = BFS(X, Y);
                    if (newDistance == originalDistance) {
                        newTracks.add(new int[]{i, j});
                    }
                    // Remove the added edge
                    adjacent[i].remove((Integer) j);
                    adjacent[j].remove((Integer) i);
                }
            }
        }
        return newTracks;
    }

    // This is the main method of the program for running this program.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // It reads the number of stations, tracks, home station, and TEDU station from the input.
        int N = scanner.nextInt(); // Total number of stations
        int M = scanner.nextInt(); // Total number of tracks
        int X = scanner.nextInt(); // Home station
        int Y = scanner.nextInt(); // TEDU station

        // It creates a graph with N vertices.
        Graph g = new Graph(N);

        // It adds existing tracks to the graph from the input.
        for (int i = 0; i < M; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            g.addEdge(a - 1, b - 1); // Adjusting for 0-based index
        }

        // It finds new tracks between X and Y.
        ArrayList<int[]> newTracks = g.findNewTracks(X - 1, Y - 1); // Adjusting for 0-based index

        // Finally it prints the number of new tracks and the new tracks.
        if (newTracks.isEmpty()) {
            System.out.println("-1");
        } else {
            System.out.println(newTracks.size());
            for (int[] track : newTracks) {
                System.out.println((track[0] + 1) + " " + (track[1] + 1)); // Adjusting for 1-based index in output
            }
        }

        scanner.close();
    }


}
