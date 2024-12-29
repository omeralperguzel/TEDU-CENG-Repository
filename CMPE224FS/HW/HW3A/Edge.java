public class Edge implements Comparable<Edge> {
    City city1;
    City city2;
    double distance;

    public Edge(City city1, City city2) {
        this.city1 = city1;
        this.city2 = city2;
        this.distance = Math.sqrt(Math.pow(city1.x - city2.x, 2) + Math.pow(city1.y - city2.y, 2));
    }

    @Override
    public int compareTo(Edge other) {
        return Double.compare(this.distance, other.distance);
    }
}
