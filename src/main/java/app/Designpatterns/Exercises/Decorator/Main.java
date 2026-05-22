package app.Designpatterns.Exercises.Decorator;

public class Main {
    public static void main(String[] args) {
        TextWriter writer = new FileTextWriter("output.txt");

        writer = new TimestampDecorator(writer);
        writer = new UppercaseDecorator(writer);
        writer = new CensorDecorator(writer);

        writer.write("   fandens, det virker!   \n\nDette er en test.  ");
    }
}

