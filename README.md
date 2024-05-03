
## Yaniv Gabay 205745615

this is the crawler project in java.
I used a fetcher factory, where we can easily create fetchers depend on the wanted fetcher.
I used a composite pattern for the formatting, so depend on the user input of command we will create the according composite formatter using the formatter factory
so a fetcher will return a fetch result object, than we can print what we want from that object using the composite formatter which will format our output.

You can visit:
[JavaDocSite](docs/index.html) for the auto generated documentation - a static index.html file, you need to fork to view its content


this uml diagram is not finished yet
```mermaid
classDiagram
    class Main {
        +void main(String[] args)
    }
    class UrlProcessor {
        -String filePath
        +List getUrls()
    }
    class ThreadPool {
        -ExecutorService executor
        +void execute(Runnable task)
        +void shutdown()
    }
    class UrlFetcherManager {
        -ThreadPool threadPool
        -Map resultsMap
        +void fetchUrls(List urls, Fetcher fetcher)
        +List getOrderedResults()
    }
    class Fetcher {
        +FetchResult fetch(String url)
    }
    class ImageFetcher {
        +FetchResult fetch(String url)
    }
    class FetcherFactory {
        +Fetcher createFetcher(String type)
    }
    class OutputFormatter {
        +String format(FetchResult result)
    }
    class CompositeFormatter {
        +String format(FetchResult result)
    }
    class MimeTypeFormatter {
        +String format(FetchResult result)
    }
    class SizeFormatter {
        +String format(FetchResult result)
    }
    class TimeFormatter {
        +String format(FetchResult result)
    }
    class UrlFormatter {
        +String format(FetchResult result)
    }
    class FormatterFactory {
        +OutputFormatter createFormatter(String formatType)
    }

    ImageFetcher ..|> Fetcher : implements
    FetcherFactory --> Fetcher : creates
    CompositeFormatter ..|> OutputFormatter : implements
    MimeTypeFormatter ..|> OutputFormatter : implements
    SizeFormatter ..|> OutputFormatter : implements
    TimeFormatter ..|> OutputFormatter : implements
    UrlFormatter ..|> OutputFormatter : implements
    FormatterFactory --> CompositeFormatter : creates
    Main --> FetcherFactory : uses
    Main --> FormatterFactory : uses
    Main --> UrlFetcherManager : uses
    Main --> UrlProcessor : uses
    UrlFetcherManager --> ThreadPool : uses
    UrlFetcherManager --> Fetcher : uses
    UrlFetcherManager --> OutputFormatter : uses



```
