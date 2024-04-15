package formatter;

import model.FetchResult;

public class TimeFormatter implements OutputFormatter {
    @Override
    public String format(FetchResult result) {
        return "Time: " + result.getResponseTime() + " ms";
    }
}
