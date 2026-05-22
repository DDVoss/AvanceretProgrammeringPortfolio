package app.Designpatterns.PortfolioExercise.AStar;

public abstract class DistanceMetric {

    public abstract int heuristic(MazeNode node, MazeNode destination);

}