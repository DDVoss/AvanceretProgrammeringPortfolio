package app.Designpatterns.Exercises.Decorator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileTextWriter implements TextWriter {
    private Path filePath;

    public FileTextWriter(String filename) {
        this.filePath = Path.of(filename);
    }

    public void write(String text) {
        try {
            Files.writeString(filePath, text + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

