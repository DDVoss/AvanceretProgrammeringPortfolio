package app.Designpatterns.PortfolioExercise.ValgfritPattern;

public class QuickSort implements SortStrategy{
    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort (int arr[], int low, int high)  {
        // Base case: hvis low er større eller lig med high, er arrayet sorteret
        // Worst case opstår når pivoten altid er det mindste eller største element, fordi arrayet bliver delt ulighed, og vi får O(n²) kompleksitet i stedet for O(n log n).
        if (low < high)  {
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot -1);
            quickSort(arr, pivot + 1, high);
        }
    }

    private int partition (int arr[], int low, int high)  {
        // Vi vælger det sidste element som pivot
        int pivotValue = arr[high];
        int i = low -1;

        // vis j er mindre eller lig med pivotValue, så øger vi i og bytter elementerne på i og j
        for (int j = low; j < high; j++)  {
            if (arr[j]<= pivotValue)  {
                i++;
                swap(arr, i, j);
            }
        }
        // Efter for-loopet, bytter vi pivoten ind på den korrekte position ved at bytte elementerne på i + 1 og high
        swap(arr, i+ 1, high);

        // Returnerer den nye position for pivoten
        return i + 1;
    }

    // Hjælpefunktion til at bytte elementer i et array
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
