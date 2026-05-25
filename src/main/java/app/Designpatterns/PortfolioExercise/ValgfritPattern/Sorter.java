package app.Designpatterns.PortfolioExercise.ValgfritPattern;

import java.util.List;

public class Sorter {
    private SortStrategy strategy;

    public void setStrategy(SortStrategy strategy)  {
        this.strategy = strategy;
    }

    public void sort(int[] arr)  {
        strategy.sort(arr);
    }
}
