package formatter;

import model.FetchResult;

public interface OutputFormatter {
    String format(FetchResult result);
}
