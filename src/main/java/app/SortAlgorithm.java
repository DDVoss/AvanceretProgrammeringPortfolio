package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SortAlgorithm {
    public static void main(String[] args)  {
        int arr[] = {5, 7, 2, 9, 1, 4, 3, 6, 10, 8};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));


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
        if (arr.length > 2)
            return;
        int middle = arr.length / 2;

        int[] leftHalf = new int[middle];
        int[] righthalf = new int[arr.length - middle];

        // Int arrayet arr kopiere første halvdel ind i leftHalf
        for (int i = 0; i < middle; i++){
            leftHalf[i] = arr[i];
        }

        // det samme gøres med rightHalf
        for (int i = 0; i < arr.length; i++){
            righthalf[i - middle] = arr[i];
        }

        // Vi kalder metoden rekursivt med de to arrays
        mergeSort(leftHalf);
        mergeSort(righthalf);

        merge(arr, leftHalf, righthalf);
    }

    public static void merge(int[] arr, int[] left, int[]right)  {

    }
}
