package app.BackTracking;

public class MazeExercise {
    static final int N = 6;

    static int[][] maze = {
            {1, 0, 1, 1, 1, 0},
            {1, 1, 1, 0, 1, 0},
            {0, 0, 1, 0, 1, 1},
            {0, 1, 1, 1, 0, 1},
            {0, 1, 0, 0, 0, 1},
            {0, 1, 1, 1, 1, 1}
    };

    static int[][] path = new int[N][N];

    public static void main(String[] args) {
        if (solveMaze(0, 0)) {
            printPath();
        } else {
            System.out.println("Ingen løsning fundet.");
        }
    }

    // TODO: Implementer denne metode
    static boolean solveMaze(int row, int col) {

        if (row < 0 || row >= N || col < 0 || col >= N) { return false; }

        // Vis feltet ikke er sikkert
        if (maze[row][col] == 0 || path[row][col] == 2) {
            return false;
        }

        // Marker den besøgte felt
        path[row][col] = 2;

        // Har vi nået målet?
        if (row == 5 && col == 5)  {
            return true;
        }

        // Gå ned
        if (solveMaze(row + 1, col)) { return true; }
        // Gå til højre
        if (solveMaze(row, col + 1))  { return true; }
        // Gå op
        if (solveMaze(row - 1, col)) { return true; }
        // Gå til venstre
        if (solveMaze(row, col - 1))  { return true; }

        return false;
    }

    static void printPath() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(path[i][j] + " ");
            }
            System.out.println();
        }
    }
}
