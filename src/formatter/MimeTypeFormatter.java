package formatter;

import model.FetchResult;

/**
 * Formats the MIME type of the fetch result.
 */
public class MimeTypeFormatter implements OutputFormatter {
    @Override
    public String format(FetchResult result) {
        return "Mime Header: " + result.getMimeType();
    }
}
