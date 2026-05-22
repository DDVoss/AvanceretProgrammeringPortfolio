package app.Designpatterns.Exercises.Command;

public class AcceptCommand implements Command {
    private CardReader terminal;

    public AcceptCommand(CardReader terminal) {
        this.terminal = terminal;
    }

    public void execute() {
        terminal.accept();
    }
}
