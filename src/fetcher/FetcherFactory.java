package fetcher;

public class FetcherFactory {
    public Fetcher createFetcher(String contentType) {
        if (contentType.startsWith("image/")) {
            return new ImageFetcher();
        }
        // Add more conditions for different types of fetchers
        throw new IllegalArgumentException("Unsupported content type: " + contentType);
    }
}
