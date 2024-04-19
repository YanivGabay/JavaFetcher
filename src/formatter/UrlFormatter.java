package formatter;

import model.FetchResult;

/**
 * Formats the URL of the fetch result.
 */
public class UrlFormatter implements OutputFormatter {
    @Override
    public String format(FetchResult result) {
        return "Url: " + result.getUrl();
    }
}
