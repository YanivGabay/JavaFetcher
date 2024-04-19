package runner;

import fetcher.Fetcher;
import formatter.FormatterFactory;
import formatter.OutputFormatter;
import model.FetchResult;

import fetcher.FetcherFactory;
import java.util.List;
import urlprocessor.UrlProcessor;
import manager.UrlFetcherManager;
public class Runner {

    public static void startProgram(String[] args) {
        //args[0] should be equal to the output commands
        // for example: su, tms.
        // any combination of s u t m any order minimum 1

        //args[1] should be equal to the pool size as the threads amount
        // for example: 5

        //args[2] should be equal to the file name
        // for example : urls.txt
        // so urls.txt will hold addresses, where each thread will get an url
        // to process, we need to have output for each url, and save them in
        // the same order
        // so input should be: stu 5 file.txt
        String filePath = args[2];
        String commandsGiven = args[0];
        int poolSize = Integer.parseInt(args[1]);

        UrlProcessor processor = new UrlProcessor(filePath);
        List<String> urls = processor.getUrls();


        FetcherFactory factory = new FetcherFactory();
        String contentTypeImage = "image/png";
        Fetcher imageFetcher = factory.createFetcher(contentTypeImage);


        UrlFetcherManager manager = new UrlFetcherManager(poolSize);
        manager.fetchUrls(urls, imageFetcher);

        OutputFormatter formatter = FormatterFactory.createFormatter(commandsGiven);
        
        FetchResult[] orderedResults = manager.getOrderedResults(urls.size());
        // Format each result using the composite formatter
        for (FetchResult result : orderedResults) {
            if (result != null) { // Ensure the result is not null before formatting
                System.out.println(formatter.format(result));
            }
        }
    }
}
