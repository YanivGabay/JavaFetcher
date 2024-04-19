package fetcher;

import model.FetchResult;

/**
 * Interface for fetching content from a URL.
 */
public interface Fetcher {
    /**
     * Fetches content from the given URL.
     *
     * @param urlString The URL to fetch content from.
     * @return The result of the fetch operation.
     */
    FetchResult fetch(String urlString);
}
