package formatter;

import model.FetchResult;

/**
 * Interface for output formatters.
 */
public interface OutputFormatter {
    /**
     * Formats the fetch result.
     *
     * @param result The fetch result to format.
     * @return The formatted output string.
     */
    String format(FetchResult result);
}
