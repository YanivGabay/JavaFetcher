package manager;

import fetcher.Fetcher;
import fetcher.ImageFetcher; // Assuming this is your implementation
import model.FetchResult;

import threadpool.ThreadPool;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class UrlFetcherManager {
    private final ThreadPool threadPool;
    private final ConcurrentHashMap<Integer, FetchResult> resultsMap;

    public UrlFetcherManager(int poolSize) {
        this.threadPool = new ThreadPool(poolSize);
        this.resultsMap = new ConcurrentHashMap<>();
    }

    public void fetchUrls(List<String> urls, Fetcher fetcher) {
        for (int i = 0; i < urls.size(); i++) {
            final int index = i;
            final String url = urls.get(index);
            threadPool.execute(() -> {
                FetchResult result = fetcher.fetch(url);
                resultsMap.put(index, result);
            });
        }
        threadPool.shutdown();
    }

    public FetchResult[] getOrderedResults(int size) {
        FetchResult[] results = new FetchResult[size];
        resultsMap.forEach((key, value) -> results[key] = value);
        return results;
    }
}
