package formatter;

public class FormatterFactory {
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
