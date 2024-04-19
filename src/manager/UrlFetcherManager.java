package manager;

import fetcher.Fetcher;
import model.FetchResult;
import threadpool.ThreadPool;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;



public class UrlFetcherManager {
    private final ThreadPool threadPool;
    private final Map<Integer, FetchResult> resultsMap; // Use LinkedHashMap for maintaining order

    public UrlFetcherManager(int poolSize) {
        this.threadPool = new ThreadPool(poolSize);
        this.resultsMap = Collections.synchronizedMap(new LinkedHashMap<>()); // Synchronize the LinkedHashMap
    }

    public void fetchUrls(List<String> urls, Fetcher fetcher) {
        for (int i = 0; i < urls.size(); i++) {
            final int index = i;
            final String url = urls.get(index);
            threadPool.execute(() -> {
                FetchResult result = fetcher.fetch(url);
                synchronized (resultsMap) {
                    resultsMap.put(index, result);
                    System.out.println("Result stored for URL at index " + index + " status: " + result.getSuccess() );// Synchronize writes to ensure thread safety
                }
            });
        }
        threadPool.shutdown();

    }

    public List<FetchResult> getOrderedResults() {
        synchronized (resultsMap) {
            return new ArrayList<>(resultsMap.values()); // Convert values to a list to maintain order
        }
    }
}
