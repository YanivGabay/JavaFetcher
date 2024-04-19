package formatter;



/**
 * A factory class for creating output formatters based on a specified format string.
 */
public class FormatterFactory {
    /**
     * Creates an output formatter based on the specified format string.
     *
     * @param format The format string specifying the format of the output.
     * @return An output formatter instance.
     */
    public static OutputFormatter createFormatter(String format) {
        CompositeFormatter compositeFormatter = new CompositeFormatter();
        for (char ch : format.toCharArray()) {
            switch (ch) {
                case 's':
                    compositeFormatter.addFormatter(new SizeFormatter());
                    break;
                case 'u':
                    compositeFormatter.addFormatter(new UrlFormatter());
                    break;
                case 't':
                    compositeFormatter.addFormatter(new TimeFormatter());
                    break;
                case 'm':
                    compositeFormatter.addFormatter(new MimeTypeFormatter());
                    break;
            }
        }
        return compositeFormatter;
    }
}
