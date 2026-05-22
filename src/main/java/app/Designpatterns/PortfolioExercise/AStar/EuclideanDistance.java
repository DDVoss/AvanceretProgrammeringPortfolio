package app.Designpatterns.PortfolioExercise.AStar;

public class EuclideanDistance extends DistanceMetric{
    @Override
    public int heuristic(MazeNode node, MazeNode destination) {
        int dRow = destination.getRow() - node.getRow();
        int dCol = destination.getCol() - node.getCol();
        return (int) Math.sqrt(dRow * dRow + dCol * dCol);
    }
}
