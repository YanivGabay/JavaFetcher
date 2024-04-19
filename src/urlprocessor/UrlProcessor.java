package urlprocessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UrlProcessor {
    private List<String> urls;
    private static final Logger LOGGER = Logger.getLogger(UrlProcessor.class.getName());

    public UrlProcessor(String inputFilePath) {
        this.urls = new ArrayList<>();
        loadUrls(inputFilePath);
    }

    private void loadUrls(String inputFilePath) {
        // Define the path from the file path string
        Path path = Paths.get(inputFilePath);

        // Try-with-resources to ensure the stream is closed after use
        try (Stream<String> lines = Files.lines(path)) {
            LOGGER.log(Level.INFO, "Attempting to read URLs from file: {0}", inputFilePath);
            urls = lines.collect(Collectors.toList());  // Collect lines into list
            LOGGER.log(Level.INFO, "Successfully loaded URLs from file.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading URLs from file: {0}", inputFilePath);
            LOGGER.log(Level.SEVERE, "IOException message: {0}", e.getMessage());
            System.exit(1);
        }
    }

    public List<String> getUrls() {
        return urls;
    }
}
