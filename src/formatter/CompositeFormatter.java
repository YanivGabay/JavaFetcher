package formatter;

import model.FetchResult;
import java.util.ArrayList;
import java.util.List;

public class CompositeFormatter implements OutputFormatter {
    private final List<OutputFormatter> formatters = new ArrayList<>();

    public void addFormatter(OutputFormatter formatter) {
        formatters.add(formatter);
    }

    @Override
    public String format(FetchResult result) {
        StringBuilder sb = new StringBuilder();
        for (OutputFormatter formatter : formatters) {
            sb.append(formatter.format(result)).append(" ");
        }
        return sb.toString().trim(); // Removes the trailing space
    }
}
