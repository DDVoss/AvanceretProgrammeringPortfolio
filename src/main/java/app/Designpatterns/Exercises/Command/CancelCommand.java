package app.Designpatterns.Exercises.Command;

public class CancelCommand implements Command{
    private CardReader terminal;

    public CancelCommand(CardReader terminal)  {
        this.terminal = terminal;
    }

    @Override
    public void execute() {
        terminal.cancel();
    }
}
