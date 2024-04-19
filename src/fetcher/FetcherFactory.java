package fetcher;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Factory class for creating fetcher instances based on content type.
 */
public class FetcherFactory {
    private final Map<String, Supplier<Fetcher>> fetcherMap;

    /**
     * Constructs a FetcherFactory and registers default fetchers.
     */
    public FetcherFactory() {
        this.fetcherMap = new HashMap<>();
        // Register default fetchers
        registerFetcher("image/", ImageFetcher::new);
        // Add more registrations here, for example:
        // registerFetcher("text/", TextFetcher::new);
    }

    /**
     * Registers a fetcher supplier for the given content type prefix.
     *
     * @param key             The content type prefix.
     * @param fetcherSupplier The supplier to create fetcher instances.
     */
    public void registerFetcher(String key, Supplier<Fetcher> fetcherSupplier) {
        fetcherMap.put(key, fetcherSupplier);
    }

    /**
     * Creates a fetcher instance based on the content type.
     *
     * @param contentType The content type.
     * @return A fetcher instance.
     * @throws IllegalArgumentException If the content type is not supported.
     */
    public Fetcher createFetcher(String contentType) {
        // Iterate over keys to find a match, allowing for partial matches (e.g., startsWith)
        for (Map.Entry<String, Supplier<Fetcher>> entry : fetcherMap.entrySet()) {
            if (contentType.startsWith(entry.getKey())) {
                return entry.getValue().get(); // Use the supplier to create a new instance
            }
        }
        throw new IllegalArgumentException("Unsupported content type: " + contentType);
    }
}
