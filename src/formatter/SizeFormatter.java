package formatter;

import model.FetchResult;

public class SizeFormatter implements OutputFormatter {
    @Override
    public String format(FetchResult result) {
        return "Size: " + result.getSize() + " bytes";
    }
}
