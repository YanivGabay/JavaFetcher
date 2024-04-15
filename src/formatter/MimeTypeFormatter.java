package formatter;

import model.FetchResult;

public class MimeTypeFormatter implements OutputFormatter {
    @Override
    public String format(FetchResult result) {
        return "Mime Header: " + result.getMimeType();
    }
}
