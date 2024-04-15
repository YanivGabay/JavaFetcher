package formatter;

import model.FetchResult;

public class UrlFormatter implements OutputFormatter {
    @Override
    public String format(FetchResult result) {
        return "Url: " + result.getUrl();
    }
}
