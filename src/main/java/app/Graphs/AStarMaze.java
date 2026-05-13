package app.Graphs;

import app.Algorithms.CircularLinkedList.Node;

import java.util.*;

public class AStarMaze {

    // Nordby = 2
    // Sydby = 3
    // Østby = 4
    // Vestby = 5
    // 0 = åben, 1 = mur
    static int[][] grid = {
            {0,0,0,1,0,2,0,1,0,0,0,0},
            {1,1,0,1,0,1,0,1,0,1,1,0},
            {0,0,0,0,0,1,0,0,0,0,1,0},
            {0,1,1,1,1,1,1,1,1,1,1,0},
            {0,0,0,0,0,0,0,0,1,0,0,0},
            {1,1,1,0,1,1,1,0,1,1,0,1},
            {5,0,0,0,0,0,1,0,0,0,0,4},
            {0,1,1,1,1,0,1,1,1,1,1,0},
            {0,0,0,0,1,0,0,0,0,0,1,0},
            {1,1,0,1,1,3,1,1,0,1,1,0},
            {0,0,0,0,0,0,1,0,0,0,1,0},
            {0,1,1,1,0,1,1,0,1,1,0,0},
    };

    static final int ROWS = 12, COLS = 12;
    static Map<Integer, String> cityNames = Map.of(
            2, "Nordby",
            3, "Sydby",
            4, "Østby",
            5, "Vestby"
    );

    public static void main(String[] args) {
        // Byg alle noder
        MazeNode[][] nodes = new MazeNode[ROWS][COLS];
        for (int r = 0; r < ROWS; r++)  {
            for (int c = 0; c < COLS; c++)  {
                if (grid[r][c] == 0 || grid[r][c] >= 2)  { // Tillad både veje (0) og byer (2-5)
                    nodes[r][c] = new MazeNode(r, c);
                }
            }
        }

        // Forbind naboer - urettede kanter i alle 4 retninger
        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        for (int r = 0; r < ROWS; r++)  {
            for (int c = 0; c < COLS; c++)  {
                if (nodes[r][c] == null) continue;
                for (int[] d : directions) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr >= 0 && nr < ROWS && nc >= 0 && nc < COLS && nodes[nr][nc] != null)  {
                        nodes[r][c].addNeighbors(nodes[nr][nc]);
                    }
                }
            }
        }

        MazeNode source = findCityNode(nodes, 2);
        MazeNode destination = findCityNode(nodes, 3);

        findShortestPath(source, destination);
    }

    private static MazeNode findCityNode(MazeNode[][] nodes, int cityNumber)  {
        for (int r = 0; r < ROWS; r++)  {
            for (int c = 0; c < COLS; c++)  {
                if (grid[r][c] == cityNumber)  {
                    return nodes[r][c];
                }
            }
        }
        return null;
    }

    private static int heuristic(MazeNode node, MazeNode destination)  {
        return Math.abs(destination.getRow() - node.getRow())
                + Math.abs(destination.getCol() - node.getCol());
    }

    private static String heuristicExplanation(MazeNode node, MazeNode destination)  {
        int rowDiff = Math.abs(destination.getRow() - node.getRow());
        int colDiff = Math.abs(destination.getCol() - node.getCol());
        int hCost = rowDiff + colDiff;
        return String.format("|%d - %d| + |%d - %d| = %d + %d = %d",
                destination.getRow(), node.getRow(),
                destination.getCol(), node.getCol(),
                rowDiff, colDiff, hCost);
    }

    public static void findShortestPath(MazeNode source, MazeNode destination)  {
        Map<MazeNode, MazeNode> prev = new HashMap<>();
        Map<MazeNode, Integer> dist = new HashMap<>();
        Set<MazeNode> visited = new HashSet<>();

        PriorityQueue<NodeWithDist> queue = new PriorityQueue<>();
        queue.add(new NodeWithDist(source, 0, heuristic(source, destination)));
        dist.put(source, 0);

        Scanner scanner = new Scanner(System.in);
        int step = 1;

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         A* PATHFINDING - Journey Through the Cities        ║");
        System.out.println("║                                                            ║");
        System.out.println("       Finding route from " + String.format("%-15s", cityNames.get(grid[source.getRow()][source.getCol()])) + " to " + cityNames.get(grid[destination.getRow()][destination.getCol()]));
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        System.out.println("Press ENTER to start the journey...");
        scanner.nextLine();

        while (!queue.isEmpty())  {
            displayState(queue, visited, dist, step, destination);
            System.out.println("\nPress ENTER to continue...");
            scanner.nextLine();

            NodeWithDist current = queue.poll();

            if (current.node.equals(destination))  {
                System.out.println("\n✓ Reached " + cityNames.get(grid[destination.getRow()][destination.getCol()]) + "!");
                break;
            }

            if (visited.contains(current.node))  {
                System.out.println("Node (" + current.node.getRow() + "," + current.node.getCol() + ") already visited, skipping...");
                continue;
            }
            visited.add(current.node);

            System.out.println(String.format("→ Exploring node (%d,%d) - Total distance: %d, F-cost: %d",
                    current.node.getRow(), current.node.getCol(), current.gCost, current.fCost));

            for (MazeNode next : current.node.getNeighbors())  {
                if (visited.contains(next)) continue;

                // Alle skridt koster 1 i en labyrint
                int newDist = current.gCost + 1;

                if (newDist < dist.getOrDefault(next, Integer.MAX_VALUE))  {
                    dist.put(next, newDist);
                    prev.put(next, current.node);
                    queue.add(new NodeWithDist(next, newDist, heuristic(next, destination)));
                    System.out.println(String.format("  → Found path to (%d,%d) with distance %d",
                            next.getRow(), next.getCol(), newDist));
                }
            }
            step++;
        }

        // Rekonstruer stien via prev
        List<String> path = new ArrayList<>();
        MazeNode stepNode = destination;
        while (stepNode != null)  {
            path.add(0, "(" + stepNode.getRow() + "," + stepNode.getCol() + ")");
            stepNode = prev.get(stepNode);
        }

        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    JOURNEY COMPLETE!                       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println(String.format("Route: %s", path));
        System.out.println(String.format("Total steps: %d", path.size() - 1));
        System.out.println("✓ Safe arrival at " + cityNames.get(grid[destination.getRow()][destination.getCol()]) + "!");

        scanner.close();
    }

    private static void displayState(PriorityQueue<NodeWithDist> queue, Set<MazeNode> visited,
                                     Map<MazeNode, Integer> dist, int step, MazeNode destination)  {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("STEP: " + step);
        System.out.println("=".repeat(60));

        System.out.println("\n📋 QUEUE (nodes to explore, sorted by F-cost):");
        if (queue.isEmpty()) {
            System.out.println("   [empty]");
        } else {
            queue.stream()
                    .sorted(Comparator.comparingInt(n -> n.fCost))
                    .limit(10)
                    .forEach(n -> {
                        int hCost = heuristic(n.node, destination);
                        System.out.println(String.format("   - (%d,%d): G=%d + H=%d = F=%d",
                                n.node.getRow(), n.node.getCol(), n.gCost, hCost, n.fCost));
                    });
            if (queue.size() > 10) {
                System.out.println("   ... and " + (queue.size() - 10) + " more nodes");
            }
        }

        System.out.println("\n✅ VISITED (explored nodes):");
        if (visited.isEmpty()) {
            System.out.println("   [none yet]");
        } else {
            visited.stream()
                    .sorted(Comparator.comparingInt(MazeNode::getRow)
                            .thenComparingInt(MazeNode::getCol))
                    .forEach(n -> System.out.println(String.format("   - (%d,%d): distance=%d",
                            n.getRow(), n.getCol(), dist.get(n))));
        }

        System.out.println("\n📊 MANHATTAN HEURISTIC EXPLANATION:");
        System.out.println(String.format("   H-cost = |destination_row - current_row| + |destination_col - current_col|"));
    }

    private static class NodeWithDist implements Comparable<NodeWithDist>  {
        MazeNode node;
        int gCost;
        int fCost;

        public NodeWithDist(MazeNode node, int gCost, int hCost) {
            this.node = node;
            this.gCost = gCost;
            this.fCost = gCost + hCost;
        }

        @Override
        public int compareTo(NodeWithDist other) {
            return Integer.compare(this.fCost, other.fCost);
        }
    }

}
