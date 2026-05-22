package app.Designpatterns.Exercises.Command;

public class Main {

    public static void main(String[] args) {
        CardReader terminal = new CardReader();

        Button green = new Button(new AcceptCommand(terminal));
        Button yellow = new Button(new CancelCommand(terminal));
        Button red = new Button(new ClearCommand(terminal));

        green.press();
        yellow.press();
        red.press();


    }
}
