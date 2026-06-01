package app.Graphs.Dijkstra;

import java.util.*;

public class Dijkstra {

    public static void main(String[] args) {
        WeightedNode A = new WeightedNode("A");
        WeightedNode B = new WeightedNode("B");
        WeightedNode C = new WeightedNode("C");
        WeightedNode D = new WeightedNode("D");
        WeightedNode E = new WeightedNode("E");
        WeightedNode F = new WeightedNode("F");
        WeightedNode G = new WeightedNode("G");
        WeightedNode H = new WeightedNode("H");


        //S.addNeighbor(A, 1);

        A.addNeighbor(B, 1);
        A.addNeighbor(C, 3);
        B.addNeighbor(D, 2);
        C.addNeighbor(E, 2);
        C.addNeighbor(F, 1);
        D.addNeighbor(G, 3);
        D.addNeighbor(E, 1);
        E.addNeighbor(H, 4);
        F.addNeighbor(H, 3);
        G.addNeighbor(H, 2);



        findShortestPath(A, H);
    }

    public static void findShortestPath(WeightedNode source, WeightedNode destination) {
        // Node og den node vi kom fra. Skal bruges til at printe vejen fra start til slut
        Map<WeightedNode, WeightedNode> prev = new HashMap<>();
        // Gemmer den billigste kendte dist til hver node
        Map<WeightedNode, Integer> dist = new HashMap<>();
        // Noder vi har besøgt og ikke skal afsøge igen
        Set<WeightedNode> visited = new HashSet<>();

        // Køen sorterer selv efter dist fordi NodeWithDist implementerer Comparable
        PriorityQueue<NodeWithDist> queue = new PriorityQueue<>();
        // Startnoden er 0 væk fra sig selv
        queue.add(new NodeWithDist(source, 0));
        dist.put(source, 0);

        while (!queue.isEmpty()) {
            NodeWithDist current = queue.poll(); // Gripper fat i den næste ukendte node

            // Vi bryder ud af løkken hvis vi finder vores mål-node
            if (current.node.equals(destination)) break;

            // Vi går til næste iteration hvis vi allerede har besøgt denne node
            if (visited.contains(current.node)) continue;

            // Vi er i gang med at undersøge current, så den skal i visited
            // så vi ikke vender tilbage til den senere
            visited.add(current.node);

            // Vi henter alle nodens naboer ud
            for (Map.Entry<WeightedNode, Integer> entry : current.node.getNeighbors().entrySet()) {
                WeightedNode next = entry.getKey();
                int weight = entry.getValue();

                // Hvis det er en node vi før har besøgt, går vi til næste iteration
                if (visited.contains(next)) continue;

                // Currents afstand til startnode + currents afstand til nabo
                int newDist = current.dist + weight;

                // Opdater kun hvis vi har fundet en billigere vej
                if (newDist < dist.getOrDefault(next, Integer.MAX_VALUE)) {
                    dist.put(next, newDist);
                    prev.put(next, current.node);
                    queue.add(new NodeWithDist(next, newDist));
                }
            }
        }

        // Rekonstruer stien via prev
        List<String> path = new ArrayList<>();
        WeightedNode step = destination;
        while (step != null) {
            path.add(0, step.getName()); // Adder path navn til index 0 så listen bliver konstrueret rigtigt
            step = prev.get(step); // Tjekker det forrige step (Følger brød krummerne)
        }

        System.out.println("Korteste vej: " + path);
        System.out.println("Samlet dist: " + dist.get(destination));
    }

    // Hjælpeklasse der pakker en node og dens afstand fra startnoden sammen
    // Så køen selv kan sortere uden at slå op i et separat dist-map
    private static class NodeWithDist implements Comparable<NodeWithDist> {
        WeightedNode node;
        int dist;

        public NodeWithDist(WeightedNode node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(NodeWithDist other) {
            return Integer.compare(this.dist, other.dist);
        }
    }
}