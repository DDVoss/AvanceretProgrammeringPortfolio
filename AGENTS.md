# AGENTS.md - AI Agent Guidelines

This is an educational portfolio demonstrating advanced programming concepts. AI agents should understand the unique structure and patterns used throughout this codebase.

## Project Overview

- **Type**: Educational Java portfolio covering algorithms, data structures, and design patterns
- **Build**: Maven (Java 17, no external dependencies)
- **Language**: Danish comments interspersed with English code and method names
- **Philosophy**: Implement algorithms from scratch to demonstrate deep understanding

## Architecture Patterns

### 1. Algorithm Organization by Concept
Each algorithm lives in its own package within `src/main/java/app/`:
- `Complexity/` - Big O analysis with practical examples (binary search, two-sum)
- `Algorithms/` - Classic algorithms (NQueens, Maze solving, Cycle detection, Circular linked lists)
- `Graphs/` - Graph algorithms (Dijkstra, A*) with graph visualization
- `Designpatterns/` - Factory pattern, Proxy, Strategy (mostly empty, educational scaffolding)
- `Sort/` - Sorting algorithm implementations

**Why this matters**: When refactoring or adding algorithms, maintain this organizational structure. Don't add utilities to root `app/` package; create appropriate subdirectories.

### 2. Interactive Demonstration Pattern
Graph algorithms use interactive step-by-step visualization:

```java
// Dijkstra.java pattern:
- Scanner-based user prompts between algorithm steps
- displayState() method shows queue, visited nodes, and current distances
- Narrative wrapper (e.g., "Albert's cookie journey") makes learning engaging
- PriorityQueue with Comparable inner class for state management
```

**When working with**: Maintain this pattern for any pathfinding/graph extensions. The interactive element is pedagogically important.

### 3. State Management via Helper Classes
Simple inner Comparable classes pack state for data structures:

```java
// NodeWithDist in Dijkstra.java
private static class NodeWithDist implements Comparable<NodeWithDist> {
    WeightedNode node;
    int dist;
    @Override
    public int compareTo(NodeWithDist other) { return Integer.compare(this.dist, other.dist); }
}
```

**Why**: Keeps PriorityQueue naturally sorted without callbacks. Enables multiple simultaneous algorithm instances with different state.

### 4. Factory Pattern for Data Structures
List construction via static factory methods:

```java
// ListFactory.buildList(1, 2, 3, 4, 5)
// ListFactory.buildListWithCycle()
```

**Use this pattern**: When you need to create complex test data structures without cluttering algorithm code.

### 5. Backtracking Algorithm Structure
Recursive exploration with state marking:

```java
// MazeExercise.java and NQueens.java pattern:
- Base case checks first (boundary/termination conditions)
- Mark current state (path[row][col] = 2 or board[row][col] = 'Q')
- Recursive exploration (all directions or columns)
- Backtrack: unmark state (reset value)
```

## Key Conventions

### Naming
- **Methods**: English names (solveMaze, placeQueen, findShortestPath)
- **Variables**: English or Danish (both acceptable; Danish used for domain context)
- **Packages**: English hierarchy following concept

### Comments
- **Danish**: Algorithm explanations, complex logic reasoning (e.g., why we only check upward in NQueens)
- **English**: Edge cases, implementation notes for non-native speakers

### Testing & Running
- **No unit tests**: Algorithms verified via `main()` method output inspection
- **Run via Maven**: `mvn clean compile exec:java -Dexec.mainClass="app.Graphs.Dijkstra.DijkstraGuided"`
- **No build profiles or test phases**: Single standard build

### Unused Code Pattern
Commented-out variations are intentional for pedagogical comparison:

```java
// In BigOExamples.main() and MoneyHandler.main()
//System.out.println("O(log n) - Logaritmisk tid:");
// logTime(n);
```

**Don't remove these**: They show algorithm variations and complexity trade-offs.

## Common Workflows

### Building & Compiling
```powershell
# Standard Maven lifecycle
mvn clean compile          # Compile only
mvn clean package          # Build jar (no tests to run)
mvn exec:java -Dexec.mainClass="app.Complexity.BigOExamples"  # Run specific class
```

### Adding a New Algorithm
1. Create new package in `src/main/java/app/` (e.g., `app/Algorithms/QuickSort/`)
2. Implement algorithm in main class with `main()` method for demonstration
3. Include helper classes inline if algorithm-specific
4. Add Danish comments explaining the algorithm's rationale
5. Include worked examples in `main()` with clear output formatting

### Understanding Algorithm Relationships
- **Graphs use WeightedNode**: A node model with neighbor Maps and weights
- **Linked Lists use Node**: Simple value + next reference model
- **Maze uses 2D arrays**: State matrix where values = cell state codes

## Code Style

- **Indentation**: 4 spaces (Maven default)
- **Braces**: Opening on same line (Java convention)
- **Variables**: Descriptive names preferred (avoid single letters except loop indices)
- **State variables**: Often static within algorithm classes (acceptable for educational scope)
- **Output**: Use System.out with clear labeling (see Dijkstra displayState for model)

## Critical Files by Purpose

| File | Purpose |
|------|---------|
| `pom.xml` | Maven configuration (Java 17 target, no dependencies) |
| `src/main/java/app/Graphs/Dijkstra/Dijkstra.java` | Reference implementation: graph algorithm + interactive visualization |
| `src/main/java/app/Algorithms/Chess/NQueens.java` | Reference implementation: backtracking pattern |
| `src/main/java/app/Algorithms/CircularLinkedList/ListFactory.java` | Reference implementation: factory pattern for data structures |

## Gotchas & Warnings

1. **No dependency management**: All algorithms from scratch. Don't add `<dependency>` to pom.xml without explicit discussion.
2. **Static fields in algorithm classes**: Educational code; acceptable here but not production practice.
3. **Interactive input (Scanner)**: Dijkstra pauses execution for user input—may need modification for non-interactive testing.
4. **Package organization is strict**: Changing package names will break existing execution patterns; coordinate with user first.
5. **English/Danish mix**: Always maintain—Danish for pedagogical explanation, English for code internals.

## For AI Agents: Recommended Workflow

1. **Understand the context**: Read the parent package structure and any existing algorithm before adding new code
2. **Follow the pattern**: Use existing implementations (Dijkstra, NQueens) as templates
3. **Preserve interactivity**: If extending graph algorithms, maintain step-by-step visualization
4. **Test via main()**: Always include a worked example in the main method
5. **Comment strategically**: Explain the "why" in Danish, "how" in code comments (English)
6. **Respect the scope**: This is educational—prefer clarity and explanation over clever optimization

