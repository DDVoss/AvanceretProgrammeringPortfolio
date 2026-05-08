package app.Graphs;

import java.util.HashMap;
import java.util.Map;

public class WeightedNode {
    private String name;
    // Kanterne er vægtede, afstanden til naboerne gemmes i et map
    private Map<WeightedNode, Integer> neighbors;

    public WeightedNode(String name) {
        this.name = name;
        this.neighbors = new HashMap<>();
    }

    public String getName() {return name;}

    public Map<WeightedNode, Integer> getNeighbors()  { return neighbors; }

    public void addNeighbor(WeightedNode neighbor, int cookies)  { neighbors.put(neighbor, cookies); }
}
