package app;

public class BigOExamples {
    public static void main(String[] args) {
        int n = 100000; // Juster n for at se effekten
//        System.out.println("O(1) - Konstant tid:");
//        constantTime(n);
//
//        System.out.println("\nO(log n) - Logaritmisk tid:");
//        logTime(n);
//
//        System.out.println("\nO(n) - Lineær tid:");
//        linearTime(n);
//
//        System.out.println("\nO(n^2) - Kvadratisk tid:");
//        quadraticTime(n);

        System.out.println("\nO(log n) - number by binary search:");
        int[] ints = {1, 2, 4, 5, 7, 9, 11};
        System.out.println(binarySearch(ints, 9));

        System.out.println("\nO(n) - Total Dogs:");
        String[] dogs = {"Cat", "Dog", "Bird", "Dog", "Fish"};
        findSumOfDogs(dogs);

        System.out.println("\nO(n^2) - Find Two Sum:");
        int[] sum = {1, 2, 3, 4, 5};
        int target = 7;
        findTwoSum(sum, target);

    }

    // O(1) - Konstant tid
    public static void constantTime(int n) {
        System.out.println("Jeg printer altid én gang, uanset n.");
    }

    // O(log n) - Logaritmisk tid (Binær nedtælling)
    public static void logTime(int n) {
        for (int i = n; i > 1; i /= 2) {
            System.out.println("Jeg kører log n gange, n er nu: " + i);
        }
    }

    // O(n) - Lineær tid
    public static void linearTime(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Itererer: " + i);
        }
    }

    // O(n^2) - Kvadratisk tid
    public static void quadraticTime(int n) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("Kvadratisk iteration: " + i + "," + j);
            }
        }

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
}

