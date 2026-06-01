package app.Designpatterns.PortfolioExercise.ValgfritPattern;

import java.util.List;

public class BubbleSort implements SortStrategy{
    @Override
    public void sort(int[] arr) {
        bubbleSort(arr);
    }

    private void bubbleSort(int[] arr) {
        int n = arr.length;

        // Det ydre loop stopper når n ikke har flere tal at sammenligne med
        for (int i = 0; i < n - 1; i++)  {
            // Checker hvert tal med hinanden og bytter plads. Det største tal i hver iteration flytter til det sidste tal i arrayet
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1])  {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
