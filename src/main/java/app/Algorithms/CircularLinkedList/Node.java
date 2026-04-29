package app.Algorithms.CircularLinkedList;

public class Node {
    int value;
    Node next;

    // Node tager imod en int værdi. Node er en simpel datastruktur hvor next referer til den næste node i listen
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
