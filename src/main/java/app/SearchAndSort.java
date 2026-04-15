package app;


import java.util.*;

// 1. Leg med kompleksitet
public class SearchAndSort {
    public static void main(String[] args){
        int size = 500000;

        arrayVsLinked();
        listVsHash();

        System.out.println("=== HashSet Performance ===");
        treeVsHash(new HashSet<>(), size);

        System.out.println("\n=== TreeSet Performance ===");
        treeVsHash(new TreeSet<>(), size);
    }
    public static int binarySearch(int[] numbers, int target){
        int low = 0;
        int high = numbers.length - 1;

        while (low <= high){
            int middleposition = (low + high) / 2;
            int middlenumber = numbers[middleposition];

            if (target == middlenumber)  {
                return middleposition;
            }
            if (target < middlenumber)  {
                high = middleposition - 1;
            } else {
                low = middleposition + 1;
            }
        }
        return -1;
    }

    public static void findSumOfDogs(String[] dogs) {
        int total = 0;

        for (int i = 0; i < dogs.length; i++) {
            if (dogs[i].equals("Dog")) {
                total++;
            }
        }
        System.out.println("Total Dogs: " + total);
    }

    public static void findTwoSum(int[] sum, int target) {
        for (int i = 0; i < sum.length; i++)  {
            for (int j = 0; j < sum.length; j++) {
                if (sum[i] + sum[j] == target) {
                    System.out.println("Found: " + sum[i] + " + " + sum[j] + " = " + target);
                    return;
                }
            }
        }
    }

    // 2. Datastrukturer og kompleksitet i praksis

    // ArrayList vs LinkedList

    public static void arrayVsLinked(){

        int elements = 500000 + 1;
        ArrayList<Integer> aList = new ArrayList<>();
        LinkedList<Integer> lList = new LinkedList<>();

        // Populate list with elements
        for (int i = 0; i < elements; i++){
            aList.add(i);
            lList.add(i);
        }

        // get specific element from list and set

        // Time Array.get
        long startArray = System.currentTimeMillis();
        aList.get(25000);
        long stopArray = System.currentTimeMillis();
        System.out.println("Arraylist tid: " + (stopArray - startArray) + " ms");

        // Time Linked.get
        long startLinked = System.currentTimeMillis();
        lList.get(25000);
        long stopLinked = System.currentTimeMillis();
        System.out.println("LinkedList tid; " + (stopLinked - startLinked) + " ms");


        // add specific element in the middle to list and set
        long startArrayAdd = System.currentTimeMillis();
        aList.add(25000, 99);
        long stopArrayAdd = System.currentTimeMillis();
        System.out.println("Arraylist add tid: " + (stopArrayAdd - startArrayAdd) + " ms");

        long startLinkedAdd = System.currentTimeMillis();
        lList.add(25000, 99);
        long stopLinkedAdd = System.currentTimeMillis();
        System.out.println("LinkedList add tid; " + (stopLinkedAdd - startLinkedAdd) + " ms");

        // remove specific element in the middle of the list and set
        long startArrayRemove = System.currentTimeMillis();
        aList.remove(25000);
        long stopArrayRemove = System.currentTimeMillis();
        System.out.println("Arraylist remove tid: " + (stopArrayRemove - startArrayRemove) + " ms");

        long startLinkedRemove = System.currentTimeMillis();
        lList.remove(25000);
        long stopLinkedRemove = System.currentTimeMillis();
        System.out.println("LinkedList add tid; " + (stopLinkedRemove - startLinkedRemove) + " ms");

        /*
        Forklaring:
        BLABLABLA
        */

    }

    // Søgning: List vs HashSet
    public static void listVsHash(){
        int elements = 500000 + 1;
        ArrayList<Integer> aList = new ArrayList<>();
        HashSet<Integer> hSet = new HashSet<>();

        // Populate the data
        for (int i = 0; i < elements; i++){
            aList.add(i);
            hSet.add(i);
        }

        long StartArrayContains = System.currentTimeMillis();
        aList.contains(25000);
        long StopArrayContains = System.currentTimeMillis();
        System.out.println("Arraylist contains tid: " + (StopArrayContains - StartArrayContains) + " ms");

        long StartHashContains = System.currentTimeMillis();
        aList.contains(25000);
        long StopHashContains = System.currentTimeMillis();
        System.out.println("Hashset contains tid: " + (StopHashContains - StartHashContains) + " ms");


    }

    // Comparable, equals() og hashcode()
    public static void  treeVsHash(Set<EntityClass> set, int size){
        EntityClass[] objects = new EntityClass[size];
        for (int i = 0; i < size; i++){
            objects[i] = new EntityClass(i);
        }

        // ADD
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++){
            set.contains(objects[i]);
        }
        long addTime = System.currentTimeMillis() - startTime;
        System.out.println("Add: " + (addTime) + " ms");
        // Forklaring

        // FIND
        startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++){
            set.contains(objects[i]);
        }
        long findTime = System.currentTimeMillis() - startTime;
        System.out.println("Find: " + (findTime) + " ms");

        // Forklaring

        // REMOVE
        startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++){
            set.remove(objects[i]);
        }
        long removeTime = System.currentTimeMillis() - startTime;
        System.out.println("Remove: " + (removeTime) + " ms");

        // Forklaring
    }
}