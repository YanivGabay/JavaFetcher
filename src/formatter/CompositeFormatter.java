package formatter;

import model.FetchResult;
import java.util.ArrayList;
import java.util.List;

/**
 * A composite formatter that combines multiple output formatters.
 */
public class CompositeFormatter implements OutputFormatter {
    private final List<OutputFormatter> formatters = new ArrayList<>();

    /**
     * Adds a formatter to the list of formatters.
     *
     * @param formatter The formatter to add.
     */
    public void addFormatter(OutputFormatter formatter) {
        formatters.add(formatter);
    }

    /**
     * Formats the fetch result using each formatter in the list and concatenates the results.
     *
     * @param result The fetch result to format.
     * @return The formatted output string.
     */
    @Override
    public String format(FetchResult result) {
        StringBuilder sb = new StringBuilder();
        for (OutputFormatter formatter : formatters) {
            sb.append(formatter.format(result)).append(" ");
        }
        return sb.toString().trim(); // Removes the trailing space
    }
}
