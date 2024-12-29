import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class FlightNetwork {
    // Graph class
    static class Graph {
        private Map<String, List<String>> adjList = new HashMap<>();

        public void addFlight(String src, String dest) {
            adjList.putIfAbsent(src, new ArrayList<>());
            adjList.get(src).add(dest);
        }

        public List<String> getReachableCities(String start, int maxHops) {
            List<String> routes = new ArrayList<>();
            Queue<String> queue = new LinkedList<>();
        
            // Initialize the queue with the start city
            queue.offer(start);
        
            while (!queue.isEmpty()) {
                String route = queue.poll();
                String[] cities = route.split("-");
                String lastCity = cities[cities.length - 1];
        
                // Check if the current route length is less than or equal to maxHops
                if (cities.length - 1 <= maxHops) {
                    if (cities.length - 1 == maxHops) {
                        routes.add(route);  // Add the route if it has exactly maxHops
                    }
        
                    List<String> neighbors = adjList.getOrDefault(lastCity, new ArrayList<>());
                    for (String neighbor : neighbors) {
                        String newRoute = route + "-" + neighbor;
                        queue.offer(newRoute);
                    }
                }
            }
        
            return routes;
        }
        
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the filename:");
        String filename = scanner.nextLine();

        Graph flightGraph = new Graph();

        // Reading and processing file
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                flightGraph.addFlight(parts[0], parts[1]);
            }
        }

        System.out.println("Enter the maximum number of hops:");
        int maxHops = scanner.nextInt();
        scanner.nextLine(); // consume the trailing newline
        System.out.println("Enter the source city:");
        String sourceCity = scanner.nextLine();


        List<String> routes = flightGraph.getReachableCities(sourceCity, maxHops);
    
        // Sort the routes alphabetically
        Collections.sort(routes);
    
        System.out.println("Number of total routes: " + routes.size());
        System.out.println("Routes are:");
        for (String route : routes) {
            System.out.println(route);
        }
    }    
}
