package urlprocessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UrlProcessor {
    private List<String> urls;

    public UrlProcessor(String inputFilePath) {
        this.urls = new ArrayList<>();
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

    public List<String> getUrls() {
        return urls;
    }
}
