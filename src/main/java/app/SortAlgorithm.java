package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SortAlgorithm {
    public static void main(String[] args)  {

//        testBubbleSort();
//        testMergeSort();
        testQuickSort();


    }

    public static void testBubbleSort() {
        int arr[] = {5, 7, 2, 9, 1, 4, 3, 6, 10, 8};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void testMergeSort()  {
        int arr[] = {12, 33, 25, 9, 18, 4, 3, 6, 10, 8};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void testQuickSort()  {
        int arr3[] = {66, 14,43, 1, 3, 87, 33, 8, 18, 26};
        quickSort(arr3, 0, arr3.length - 1);
        System.out.println(Arrays.toString(arr3));
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++)  {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1])  {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
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

    public static void quickSort (int arr[], int low, int high)  {
        // Base case: hvis low er større eller lig med high, er arrayet sorteret
        // Worst case opstår når pivoten altid er det mindste eller største element, fordi arrayet bliver delt ulighed, og vi får O(n²) kompleksitet i stedet for O(n log n).
        if (low < high)  {
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot -1);
            quickSort(arr, pivot + 1, high);
        }
    }
    public static int partition (int arr[], int low, int high)  {
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
    public static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
    }

}
