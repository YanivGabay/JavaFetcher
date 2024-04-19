package formatter;

import model.FetchResult;

/**
 * Formats the size of the fetch result.
 */
public class SizeFormatter implements OutputFormatter {
    @Override
    public String format(FetchResult result) {
        return "Size: " + result.getSize() + " bytes";
    }
}
