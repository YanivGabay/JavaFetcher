package fetcher;

import model.FetchResult;

public interface Fetcher {
    FetchResult fetch(String urlString);
}
