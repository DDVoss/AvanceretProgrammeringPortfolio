package app.Algorithms.CircularLinkedList;

public class ListFactory {

    public static void main(String[] args) {
        Node cycle = buildListWithCycle(); // Laver den cykliske liste
        Node list = buildList(1, 2, 3, 4, 5); // Laver almindelig liste

        boolean result = cycleDetector(cycle); // Finder ud af om det er en cyklisk liste
        System.out.println(result);

        boolean falseResult = cycleDetector(list); // Prøver at finde en cyklus af en almindelig liste (den vil fejle)
        System.out.println(falseResult);

        System.out.println(reverseList(list)); // Printer den omvendte liste
    }

    public static Node buildList(int... values)  {
        if (values.length == 0) return null;
        Node head = new Node(values[0]);
        Node current = head;
        for (int i = 1; i < values.length; i++)  {
            current.next = new Node(values[i]);
            current = current.next;
        }
        return head;
    }

    // Bygger en cyklist liste der starter fra 1 og går i cirkel fra 2, 3, 4, 5 og tilbage til 2
    public static Node buildListWithCycle()  {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n2; // Laver cyklus
        return n1;
    }

    public static Node reverseList(Node head)  {
        Node current = head;
        Node previous = null;
        Node next;

        while (current != null)  {

            // Holder på den næste værdi
            next = current.next;

            // Vender pointerne. Det ville sige at current peger på previous
            current.next = previous;

            // Flytter pointerne en position af gangen
            previous = current;
            // Current bliver den næste værdi i kæden vi holdte på
            current = next;

        }

        return previous;
    }


    public static boolean cycleDetector(Node head) {

        Node slow = head; // Flytter sig en gang
        Node fast = head; // Flytter sig to gange

        // Imens fast ikke er null og det næste skridt ikke er null skal den køre indtil fast møder slow.
        while(fast != null && fast.next != null) { // Null fører til en safe exit uden at skabe uendelige løkker
            slow = slow.next;
            fast = fast.next.next;
            // Når fast møder slow er det en cyklist liste
            if (slow == fast) {
                System.out.println("Liste er cyklisk");
                return true;
            }
        }
        System.out.println("Liste er ikke cyklisk");
        return false;
    }
}
