package app.Designpatterns.Exercises.Decorator;

public class UppercaseDecorator implements TextWriter{
    private TextWriter wrappee;

    public UppercaseDecorator(TextWriter wrappee)  {
        this.wrappee = wrappee;
    }

    @Override
    public void write(String text) {
        String modified = text.toUpperCase();
        wrappee.write(modified);
    }
}
