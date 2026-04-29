package app.CircularLinkedList;

public class ListFactory {

    public static void main(String[] args) {
        Node cycle = buildListWithCycle();
        boolean result = floydAlgorithm(cycle);
        System.out.println(result);
        Node notCycle = buildList(1, 2, 3, 4, 5);
        System.out.println(notCycle);
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

    public static boolean floydAlgorithm(Node head) {

        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
