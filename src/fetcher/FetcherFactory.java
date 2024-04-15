package fetcher;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FetcherFactory {
    private final Map<String, Supplier<Fetcher>> fetcherMap;

    public FetcherFactory() {
        this.fetcherMap = new HashMap<>();
        // Register default fetchers
        registerFetcher("image/", ImageFetcher::new);
        // Add more registrations here, for example:
        // registerFetcher("text/", TextFetcher::new);
    }

    public void registerFetcher(String key, Supplier<Fetcher> fetcherSupplier) {
        fetcherMap.put(key, fetcherSupplier);
    }

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
