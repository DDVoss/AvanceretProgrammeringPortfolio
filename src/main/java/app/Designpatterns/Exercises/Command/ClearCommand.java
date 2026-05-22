package app.Designpatterns.Exercises.Command;

public class ClearCommand implements Command{
    private CardReader terminal;

    public ClearCommand(CardReader terminal)  {
        this.terminal = terminal;
    }

    @Override
    public void execute() {
        terminal.clear();
    }
}
