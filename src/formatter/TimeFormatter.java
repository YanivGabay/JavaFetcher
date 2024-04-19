package formatter;

import model.FetchResult;

/**
 * Formats the response time of the fetch result.
 */
public class TimeFormatter implements OutputFormatter {
    @Override
    public String format(FetchResult result) {
        return "Time: " + result.getResponseTime() + " ms";
    }
}
