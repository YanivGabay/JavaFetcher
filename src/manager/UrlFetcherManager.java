package manager;

import fetcher.Fetcher;
import model.FetchResult;
import threadpool.ThreadPool;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Manages the fetching of URLs using a thread pool and maintains the order of results.
 */
public class UrlFetcherManager {
    private final ThreadPool threadPool;
    private final Map<Integer, FetchResult> resultsMap; // Use LinkedHashMap for maintaining order

    /**
     * Constructs a UrlFetcherManager with the specified thread pool size.
     *
     * @param poolSize The size of the thread pool.
     */
    public UrlFetcherManager(int poolSize) {
        this.threadPool = new ThreadPool(poolSize);
        this.resultsMap = Collections.synchronizedMap(new LinkedHashMap<>()); // Synchronize the LinkedHashMap
    }

    /**
     * Initiates the fetching of URLs using the provided fetcher.
     *
     * @param urls    The list of URLs to fetch.
     * @param fetcher The fetcher to use for fetching.
     */
    public void fetchUrls(List<String> urls, Fetcher fetcher) {
        for (int i = 0; i < urls.size(); i++) {
            final int index = i;
            final String url = urls.get(index);
            threadPool.execute(() -> {
                FetchResult result = null;  // Declare result outside the try block
                try {
                    result = fetcher.fetch(url);  // Attempt to fetch, handle failure within fetch()
                    System.out.println("Result stored for URL at index " + index + " status: " + result.getSuccess());
                } catch (Exception e) {
                    // Log the exception without altering result
                    // fetch object will return a FetchResult object
                    // with the error msg and -1 for values.


                    System.err.println("Error fetching URL at index " + index + ": " + e.getMessage());
                }
                synchronized (resultsMap) {
                    resultsMap.put(index, result);  // Store the result, successful or not
                }
            });
        }
        threadPool.shutdown();
    }


    /**
     * Retrieves the ordered list of fetch results.
     *
     * @return The ordered list of fetch results.
     */
    public List<FetchResult> getOrderedResults() {
        synchronized (resultsMap) {
            return new ArrayList<>(resultsMap.values()); // Convert values to a list to maintain order
        }
    }
}
