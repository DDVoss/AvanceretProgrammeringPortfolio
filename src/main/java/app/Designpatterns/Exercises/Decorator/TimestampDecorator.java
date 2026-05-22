package app.Designpatterns.Exercises.Decorator;

import java.time.LocalDateTime;

public class TimestampDecorator implements TextWriter {
    private TextWriter wrappee;

    public TimestampDecorator(TextWriter wrappee) {
        this.wrappee = wrappee;
    }

    public void write(String text) {
        String modified = "[" + LocalDateTime.now() + "] " + text;
        wrappee.write(modified);
    }
}

