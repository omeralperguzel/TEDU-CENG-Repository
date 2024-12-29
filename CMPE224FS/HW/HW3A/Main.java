import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input_q1.txt"));
        ArrayList<City> cities = new ArrayList<>();
        System.out.println("Paths are: ");
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");
            cities.add(new City(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2])));
        }

        // Create all possible edges
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        for (int i = 0; i < cities.size(); i++) {
            for (int j = i + 1; j < cities.size(); j++) {
                edges.add(new Edge(cities.get(i), cities.get(j)));
            }
        }

        // Implement Kruskal's algorithm for MST
        List<Edge> mst = new ArrayList<>();
        UnionFind uf = new UnionFind(cities.size());

        while (!edges.isEmpty() && mst.size() < cities.size() - 1) {
            Edge edge = edges.poll();
            int city1Index = cities.indexOf(edge.city1);
            int city2Index = cities.indexOf(edge.city2);

            if (uf.find(city1Index) != uf.find(city2Index)) {
                uf.union(city1Index, city2Index);
                mst.add(edge);
            }
        }

        // Output the result
        for (Edge edge : mst) {
            String result = String.format("%.1f",edge.distance);
            System.out.println(edge.city1.name + "-" + edge.city2.name + " : " + result);
        }
    }
}
