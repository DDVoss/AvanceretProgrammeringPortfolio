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

    public static void findShortestPath(WeightedNode source, WeightedNode destination)  {
        // En node og den node vi kom fra. Skal bruges til at printe vejen fra start til slut
        Map<WeightedNode, WeightedNode> prev = new HashMap<>();

        // Gemmer den billigste kendte dist til hver node
        Map<WeightedNode, Integer> dist = new HashMap<>();

        // Noder vi har besøgt og ikke skal afsøge igen
        Set<WeightedNode> visited = new HashSet<>();

        // Køen sorterer selv efter dist fordi NodeWithDist implementerer Comparable
        PriorityQueue<NodeWithDist> queue = new PriorityQueue<>();

        Scanner scanner = new Scanner(System.in);

        queue.add(new NodeWithDist(source, 0));
        dist.put(source, 0);

        int step = 1;
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║   ALBERT'S COOKIE JOURNEY - Dijkstra's Algorithm Story     ║");
        System.out.println("║   (Albert is a bit chubby and prefers less cookies!)       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        System.out.println("Press ENTER to start Albert's journey...");
        scanner.nextLine();

        while (!queue.isEmpty()) {
            displayState(queue, visited, dist, step);
            System.out.println("\nPress ENTER to continue...");
            scanner.nextLine();

            NodeWithDist current = queue.poll();

            // Hvis mål noden er fundet bryder den ud af løkken
            if (current.node.equals(destination)) {
                System.out.println("\n✓ Albert reached his destination!");
                break;
            }

            // Hvis node allerede er besøgt går vi videre til næste iteration
            if (visited.contains(current.node)) {
                System.out.println(String.format("Node %s already visited, skipping...", current.node.getName()));
                continue;
            }

            // Vi er i gang med at undersøge current, så den skal i visited
            // så man ikke vender tilbage til den senere
            visited.add(current.node);
            System.out.println(String.format("→ Albert is now at node %s (total cookies eaten: %d)",
                    current.node.getName(), current.dist));

            // Vi henter alle nodens naboer ud
            for (Map.Entry<WeightedNode, Integer> entry : current.node.getNeighbors().entrySet())  {
                WeightedNode next = entry.getKey();
                int weight = entry.getValue();

                // Hvis det er en node vi før har besøgt, går vi til næste iteration
                if (visited.contains(next)) continue;

                // Currents afstand til startnode + currents afstand til nabo
                int newDist = current.dist + weight;

                // Opdater kun hvis vi har fundet en billigere vej
                if (newDist < dist.getOrDefault(next, Integer.MAX_VALUE))  {
                    dist.put(next, newDist);
                    prev.put(next, current.node);
                    // Vi putter en ny NodeWithDist i køen i stedet for at opdatere den eksisterende.
                    // Ideelt ville vi tjekke om noden allerede er i køen og opdatere dens dist,
                    // men Java's PriorityQueue understøtter ikke det effektivt (contains() og remove() er O(n)).
                    // I stedet bruger vi dist-mappet til at undgå at putte en dårligere vej i køen overhovedet.
                    // Hvis en forældet NodeWithDist alligevel popper ud, fanger visited-tjekket den.
                    queue.add(new NodeWithDist(next, newDist));
                    System.out.println(String.format("  → Found path to %s with %d cookies (better than %d)",
                            next.getName(), newDist, dist.getOrDefault(next, Integer.MAX_VALUE)));
                }
            }

            step++;
        }
        // Rekonstruer stien via prev
        List<String> path = new ArrayList<>();
        WeightedNode stepNode = destination;
        while (stepNode != null)  {
            path.add(0, stepNode.getName());
            stepNode = prev.get(stepNode);
        }


        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    JOURNEY COMPLETE!                       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println(String.format("Albert's path: %s", path));
        System.out.println(String.format("Total cookies eaten: %d", dist.get(destination)));
        System.out.println("Albert is happy with this route! 🍪");

        scanner.close();
    }

    private static void displayState(PriorityQueue<NodeWithDist> queue, Set<WeightedNode> visited,
                                     Map<WeightedNode, Integer> dist, int step)  {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("STEP: " + step);
        System.out.println("=".repeat(60));

        System.out.println("\n📋 QUEUE (nodes to visit, sorted by cookies eaten):");
        if (queue.isEmpty()) {
            System.out.println("   [empty]");
        } else {
            queue.stream()
                    .sorted(Comparator.comparingInt(n -> n.dist))
                    .forEach(n -> System.out.println(String.format("   - Node %s: %d cookies", n.node.getName(), n.dist)));
        }
        System.out.println("\n✅ VISITED (Albert has been here):");
        if (visited.isEmpty()) {
            System.out.println("   [none yet]");
        } else {
            visited.stream()
                    .sorted(Comparator.comparing(WeightedNode::getName))
                    .forEach(n -> System.out.println(String.format("   - Node %s (cost: %d)", n.getName(), dist.get(n))));
        }

        System.out.println("\n📊 BEST DISTANCES FOUND SO FAR:");
        dist.entrySet().stream()
                .sorted((a, b) -> a.getKey().getName().compareTo(b.getKey().getName()))
                .forEach(e -> System.out.println(String.format("   - Node %s: %d cookies", e.getKey().getName(), e.getValue())));
    }

    // Hjælpeklasse der pakker en node og dens afstand fra startnoden sammen
    // så køen selv kan sortere uden at slå op i et separat dist-map
    // Vi kan ikke bare have dist som attribut på WeighedNode fordi det der kunne være flere algoritmer
    // der kørte samtidig med forskellige startnoder og dermed forskellige distancer
    private static class NodeWithDist implements Comparable<NodeWithDist>  {
        WeightedNode node;
        int dist;

        public NodeWithDist(WeightedNode node, int dist)  {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(NodeWithDist other)  { return Integer.compare(this.dist, other.dist); }
    }
}
