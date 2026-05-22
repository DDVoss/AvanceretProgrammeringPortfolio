package app.Designpatterns.Exercises.Command;

public class CardReader {
    public void accept() { System.out.println("Transaction performed"); }

    public void cancel() {
        System.out.println("Transaction cancelled");
    }

    public void clear() {
        System.out.println("Input cleared");
    }
}
