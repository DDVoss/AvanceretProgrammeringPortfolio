package app.Complexity;


import java.util.*;

// 1. Leg med kompleksitet
public class SearchAndSort {
    public static void main(String[] args){
        System.out.println("\nMethod binarySearch:");
        int[] numbers = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int target = 17;
        int result = binarySearch(numbers, target);
        System.out.println("Binary Search Result: " + result);

        System.out.println("\n_______________________________\n");
        System.out.println("Method findSumOfDogs:");
        String[] dogs = {"Dog", "Cat", "Dog", "Bird", "Dog", "Fish"};
        findSumOfDogs(dogs);

        System.out.println("\n_______________________________\n");
        System.out.println("Method findTwoSum:");
        int[] sum = {1, 2, 3, 4, 5};
        int targetSum = 7;
        findTwoSum(sum, targetSum);

        System.out.println("\n_______________________________\n");
        int size = 500000 + 1;

        System.out.println("*TEST* ___ ADD, GET, REMOVE ___ *TEST*\n");
        System.out.println("=== ArrayList Performance ===");
        listTest(new ArrayList<>(), size, 25000, 99);

        System.out.println("\n=== LinkedList Performance ===");
        listTest(new LinkedList<>(), size, 25000, 99);

        System.out.println("\n*TEST* ___ CONTAINS ___ *TEST*\n");

        System.out.println("=== ArrayList Performance ===");
        setTest(new ArrayList<>(), size, 25000);

        System.out.println("\n=== HashSet Performance ===");
        setTest(new HashSet<>(), size, 25000);

        System.out.println("\n*TEST* ___ LIST VS HASH ___ *TEST*\n");

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

    public static void listTest(List<Integer> list, int size, int index, int element)  {
        int iterations = 1000;

        // Populate the list
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        // GET
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            list.get(index);
        }
        long getTime = (System.currentTimeMillis() - startTime);
        System.out.println("Get: " + getTime + " ms");

        // ADD
        startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            list.add(index, element);
        }
        long addTime = (System.currentTimeMillis() - startTime);
        System.out.println("Add: " + addTime + " ms");

        // REMOVE
        startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            list.remove(index);
        }
        long removeTime = (System.currentTimeMillis() - startTime);
        System.out.println("Remove: " + removeTime + " ms");

        /*
        Forklaring:
        ArrayList (.get) tager direkte fat i indeks nummeret O(1)
        LinkeList tager længere tid fordi den skal gå igennem hver indeks nummer fra starten  O(n)
        */
    }

    // Søgning: List vs HashSet
    public static void setTest(Collection<Integer> collection, int size, int element)  {
        int iterations = 1000;

        // Populate the set
        for (int i = 0; i < size; i++) {
            collection.add(i);
        }

        // CONTAINS
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            collection.contains(element);
        }
        long containsTime = (System.currentTimeMillis() - startTime);
        System.out.println("Contains: " + containsTime + " ms");

        // CONTAINS ELEMENT NOT IN SET
        startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            collection.contains(-1);
        }
        long notContainsTime = (System.currentTimeMillis() - startTime);
        System.out.println("Not Contains: " + notContainsTime + " ms");

        /*
        Forklaring:
        ArrayList (.contains) skal tjekke hvert element i listen for at finde det, hvilket resulterer i O(n) tid.
        HashSet (.contains) bruger en hash-funktion til at finde elementet direkte, hvilket resulterer i O(1) tid i gennemsnit.
        Derfor er HashSet meget hurtigere end ArrayList for contains() operationen, især når elementet ikke er i samlingen.
        */

    }




    // Comparable, equals() og hashcode()
    public static void  treeVsHash(Set<EntityClass> set, int size){
        int iterations = 20000;

        EntityClass[] objects = new EntityClass[size];
        for (int i = 0; i < size; i++){
            objects[i] = new EntityClass(i);
        }

        // ADD
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++){
            set.add(objects[i]);
        }
        long addTime = System.currentTimeMillis() - startTime;
        System.out.println("Add: " + (addTime) + " ms");
        // Forklaring

        // FIND
        startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++){
            set.contains(objects[i]);
        }
        long findTime = System.currentTimeMillis() - startTime;
        System.out.println("Find: " + (findTime) + " ms");

        // REMOVE
        startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++){
            set.remove(objects[i]);
        }
        long removeTime = System.currentTimeMillis() - startTime;
        System.out.println("Remove: " + (removeTime) + " ms");

        /* Forklaring
        HashSet bruger hashCode() og equals() til at håndtere elementer, hvilket giver O(1) tid for add, find og remove operationer i gennemsnit.
        TreeSet bruger compareTo() til at holde elementerne i sorteret orden, hvilket resulterer i O(log n) tid for add, find og remove operationer. Derfor vil HashSet generelt være hurtigere end TreeSet for disse operationer, især når der er mange elementer i samlingen.
         */
    }
}