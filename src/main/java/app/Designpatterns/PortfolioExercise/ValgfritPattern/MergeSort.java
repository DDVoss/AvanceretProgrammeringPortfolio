package app.Designpatterns.PortfolioExercise.ValgfritPattern;

public class MergeSort implements SortStrategy{
    @Override
    public void sort(int[] arr) {
        mergeSort(arr);
    }

    public static void mergeSort(int[] arr)  {
        // Base case: hvis arrayet har mindre end 2 elementer, er det allerede sorteret
        if (arr.length < 2)
            return;

        //midten findes i arrayet
        int middle = arr.length / 2;

        int[] leftHalf = new int[middle];
        int[] righthalf = new int[arr.length - middle];

        // Int arrayet arr kopiere første halvdel ind i leftHalf
        for (int i = 0; i < middle; i++){
            leftHalf[i] = arr[i];
        }

        // det samme gøres med rightHalf
        for (int i = middle; i < arr.length; i++){
            righthalf[i - middle] = arr[i];
        }

        // Vi kalder metoden rekursivt med de to arrays
        mergeSort(leftHalf);
        mergeSort(righthalf);

        merge(arr, leftHalf, righthalf);
    }

    public static void merge(int[] arr, int[] left, int[]right)  {
        int i = 0, l = 0, r = 0;

        // Vi sammenligner elementerne i left og right og kopierer det mindste element ind i arr
        while (l < left.length && r < right.length){
            if (left[l] <= right[r]){
                arr[i] = left[l];
                l++;
                i++;
            } else {
                arr[i] = right[r];
                r++;
                i++;
            }
        }
        // Hvis der er elementer tilbage i left eller right, kopierer vi dem ind i arr
        while (l < left.length){
            arr[i] = left[l];
            l++;
            i++;
        }
        while (r < right.length) {
            arr[i] = right[r];
            r++;
            i++;
        }
    }
}
