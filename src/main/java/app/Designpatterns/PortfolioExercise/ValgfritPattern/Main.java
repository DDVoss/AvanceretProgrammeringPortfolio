package app.Designpatterns.PortfolioExercise.ValgfritPattern;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int arr[] = {5, 7, 2, 9, 1, 4, 3, 6, 10, 8};
        int arr2[] = {12, 33, 25, 9, 18, 4, 3, 6, 10, 8};
        int arr3[] = {66, 14,43, 1, 3, 87, 33, 8, 18, 26};

        Sorter sorter = new Sorter();

        sorter.setStrategy(new BubbleSort());
        sorter.sort(arr);
        System.out.println(Arrays.toString(arr));

        sorter.setStrategy(new MergeSort());
        sorter.sort(arr2);
        System.out.println(Arrays.toString(arr2));

        sorter.setStrategy(new QuickSort());
        sorter.sort(arr3);
        System.out.println(Arrays.toString(arr3));


    }
}
