import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ShortestPathFinder {
    private Map<String, Map<String, Integer>> graph = new HashMap<>();

    public ShortestPathFinder(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");
            String city1 = line[0].trim();
            String city2 = line[1].trim();
            int distance = Integer.parseInt(line[2].trim());
            graph.putIfAbsent(city1, new HashMap<>());
            graph.putIfAbsent(city2, new HashMap<>());
            graph.get(city1).put(city2, distance);
            graph.get(city2).put(city1, distance);
        }
    }

    public String findShortestPath(String start, String end, List<String> waypoints) {
        List<String> fullPath = new ArrayList<>();
        Set<String> visitedCities = new HashSet<>();
        int totalDistance = 0;
    
        String currentCity = start;
        fullPath.add(start);  // Add the start city to the path
        visitedCities.add(start);
    
        // Handling cases with waypoints
        for (String waypoint : waypoints) {
            PathResult result = findPathAvoidingVisitedCities(currentCity, waypoint, visitedCities);
            if (!result.path.isEmpty()) {
                totalDistance += result.distance;
                // Exclude the current city from the path to avoid duplication
                fullPath.addAll(result.path.subList(1, result.path.size()));
                visitedCities.addAll(result.path);
                currentCity = waypoint;
            }
        }
    
        // Handling the final segment to the end city
        PathResult finalResult = findPathAvoidingVisitedCities(currentCity, end, visitedCities);
        if (!finalResult.path.isEmpty()) {
            totalDistance += finalResult.distance;
            fullPath.addAll(finalResult.path.subList(1, finalResult.path.size()));
        }
    
        return "Routes are: " + String.join("-", fullPath) + "\nLength of route is: " + totalDistance;
    }
    
    
    private PathResult findPathAvoidingVisitedCities(String start, String end, Set<String> visitedCities) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previousNodes = new HashMap<>();
    
        for (String vertex : graph.keySet()) {
            if (vertex.equals(start)) {
                distances.put(vertex, 0);
                queue.add(new Node(vertex, 0));
            } else {
                distances.put(vertex, Integer.MAX_VALUE);
                queue.add(new Node(vertex, Integer.MAX_VALUE));
            }
            previousNodes.put(vertex, null);
        }
    
        while (!queue.isEmpty()) {
            Node smallest = queue.poll();
            String currentCity = smallest.getId();
    
            if (currentCity.equals(end)) {
                List<String> path = reconstructPath(previousNodes, end);
                int distance = distances.get(end);
                return new PathResult(path, distance);
            }
    
            if (distances.get(currentCity) == Integer.MAX_VALUE) {
                break;
            }
    
            for (Map.Entry<String, Integer> neighbor : graph.get(currentCity).entrySet()) {
                if (visitedCities.contains(neighbor.getKey())) {
                    continue;
                }
    
                int alt = distances.get(currentCity) + neighbor.getValue();
                if (alt < distances.get(neighbor.getKey())) {
                    distances.put(neighbor.getKey(), alt);
                    previousNodes.put(neighbor.getKey(), currentCity);
    
                    forloop:
                    for (Node n : queue) {
                        if (n.getId().equals(neighbor.getKey())) {
                            n.distance = alt;
                            queue.remove(n);
                            queue.add(n);
                            break forloop;
                        }
                    }
                }
            }
        }
    
        return new PathResult(new ArrayList<>(), Integer.MAX_VALUE);
    }
    
    private List<String> reconstructPath(Map<String, String> previousNodes, String end) {
        List<String> path = new ArrayList<>();
        while (end != null) {
            path.add(end);
            end = previousNodes.get(end);
        }
        Collections.reverse(path);
        return path;
    }
    
    private List<List<String>> generatePermutations(List<String> original) {
        if (original.size() == 0) { 
            List<List<String>> result = new ArrayList<List<String>>();
            result.add(new ArrayList<String>());
            return result;
        }
        String firstElement = original.remove(0);
        List<List<String>> returnValue = new ArrayList<List<String>>();
        List<List<String>> permutations = generatePermutations(original);
        for (List<String> smallerPermutated : permutations) {
            for (int index=0; index <= smallerPermutated.size(); index++) {
                List<String> temp = new ArrayList<String>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }

    private PathResult findPath(String start, String end) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previousNodes = new HashMap<>();
    
        for (String vertex : graph.keySet()) {
            if (vertex.equals(start)) {
                distances.put(vertex, 0);
                queue.add(new Node(vertex, 0));
            } else {
                distances.put(vertex, Integer.MAX_VALUE);
                queue.add(new Node(vertex, Integer.MAX_VALUE));
            }
            previousNodes.put(vertex, null);
        }
    
        while (!queue.isEmpty()) {
            Node smallest = queue.poll();
            if (smallest.getId().equals(end)) {
                List<String> path = new ArrayList<>();
                while (end != null) {
                    path.add(end);
                    end = previousNodes.get(end);
                }
                Collections.reverse(path);
                return new PathResult(path, distances.get(smallest.getId()));
            }
    
            if (distances.get(smallest.getId()) == Integer.MAX_VALUE) {
                break;
            }
    
            for (Map.Entry<String, Integer> neighbor : graph.get(smallest.getId()).entrySet()) {
                int alt = distances.get(smallest.getId()) + neighbor.getValue();
                if (alt < distances.get(neighbor.getKey())) {
                    distances.put(neighbor.getKey(), alt);
                    previousNodes.put(neighbor.getKey(), smallest.getId());
    
                    forloop:
                    for (Node n : queue) {
                        if (n.getId().equals(neighbor.getKey())) {
                            n.distance = alt;
                            queue.remove(n);
                            queue.add(n);
                            break forloop;
                        }
                    }
                }
            }
        }
        return new PathResult(new ArrayList<>(), Integer.MAX_VALUE);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner inputScanner = new Scanner(System.in);

        //System.out.println("Enter the path to the input file:");
        String filePath = inputScanner.nextLine();

        //System.out.println("Enter start city:");
        String start = inputScanner.nextLine();

        //System.out.println("Enter end city:");
        String end = inputScanner.nextLine();

        //System.out.println("How many cities do you want to visit?");
        int numberOfWaypoints = Integer.parseInt(inputScanner.nextLine());
        List<String> waypoints = new ArrayList<>();
        for (int i = 0; i < numberOfWaypoints; i++) {
            //System.out.println("Enter waypoint city #" + (i + 1) + ":");
            waypoints.add(inputScanner.nextLine());
            
        }
        
        ShortestPathFinder finder = new ShortestPathFinder(filePath);
        
        String shortestPath = finder.findShortestPath(start, end, waypoints);
        System.out.println(shortestPath);

        inputScanner.close();
    }

    public static class Node implements Comparable<Node> {
        private String id;
        private int distance;

        public Node(String id, int distance) {
            this.id = id;
            this.distance = distance;
        }

        public String getId() {
            return id;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    private static class PathResult {
        List<String> path;
        int distance;

        public PathResult(List<String> path, int distance) {
            this.path = path;
            this.distance = distance;
        }
    }
}
