package app.Designpatterns.Preparation.Factory.reversedlinkedlist;

public class ListFactory {

    //EXPLANATION: Dette er et factory pattern som laver en liste af sammenhængende noder. I stedet for at lave en masse (new) nodes, samles det et sted som en fabrik.

    // Bygger en liste af et antal int-værdier
    public static Node buildList(int... values) {
        if (values.length == 0) return null;
        Node head = new Node(values[0]);
        Node current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new Node(values[i]);
            current = current.next;
        }
        return head;
    }


}
