
## Yaniv Gabay 205745615

### Project Overview

This project is a Java-based crawler program that allows users to fetch and format information from URLs specified in a file. The program offers flexibility through its fetcher factory and formatter factory mechanisms.

#### Purpose
The program processes URLs from a given file using a thread pool. Each URL is fetched using a fetcher, and the results are formatted and presented based on the user's specified commands. The program currently has an image fetcher implemented but can easily accommodate new fetchers like a text fetcher.

#### Input
- **Commands**: A combination of `{s, t, u, m}` which stands for different formatting options.
- **Number of Threads**: Specifies the thread pool size.
- **File Path**: Path to a file containing the URLs.

### Design

#### Fetcher
The fetcher component fetches URLs and returns a result object. The fetcher factory facilitates easy creation of new fetchers, such as image fetcher, text fetcher, etc.

#### Formatter
The formatter component formats the fetch result based on the user’s input. The composite pattern is used to combine multiple formatting options, which include:
- **s**: Size formatting.
- **t**: Time formatting.
- **u**: URL formatting.
- **m**: Mime type formatting.

The formatter factory creates composite formatters based on the specified commands.

#### Thread Pool
The program employs a thread pool to manage concurrent fetching of URLs, enhancing efficiency.
Size of it is determined by the user input via argv.

### Documentation

For detailed, auto-generated documentation, you can visit [JavaDocSite](docs/index.html).

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
