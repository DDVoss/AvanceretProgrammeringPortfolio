package app.Designpatterns.Exercises.Decorator;

public class CensorDecorator implements TextWriter{
    private TextWriter wrappee;

    String[] swearWords = {"fandens", "satans", "forhelvede"};
    String regex = "bip!";

    public CensorDecorator(TextWriter wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void write(String text) {
        String modified = text;
        for (String word : swearWords) {
            modified = modified.replace(word, regex);
        }
        wrappee.write(modified);
    }
}
