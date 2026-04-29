package app.CircularLinkedList;

public class Node {
    int value;
    Node next;

    Node(int value) { this.value = value; }

    @Override
    public String toString() {
        String result = "" + value;
        if (next != null){
            result += " -> " + next.toString();
        }
        return result;
    }
}
