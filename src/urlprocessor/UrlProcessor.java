package urlprocessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class UrlProcessor {
    private Set<String> urls;

    public UrlProcessor(String inputFilePath) {
        this.urls = new HashSet<>();
        loadUrls(inputFilePath);
    }

    private void loadUrls(String inputFilePath) {
        try {
            Files.lines(Paths.get(inputFilePath)).forEach(urls::add);
        } catch (IOException e) {
            System.err.println("Error reading URLs from file: " + e.getMessage());
            System.exit(1);
        }
    }

    public Set<String> getUrls() {
        return urls;
    }
}
