package app.Designpatterns.PortfolioExercise.AStar;

public class ManhattanDistance extends DistanceMetric {
    @Override
    public int heuristic(MazeNode node, MazeNode destination)  {
        return Math.abs(destination.getRow() - node.getRow())
                + Math.abs(destination.getCol() - node.getCol());
    }
}
