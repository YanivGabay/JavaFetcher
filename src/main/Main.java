package main;

import validator.InputValidator;
import threadpool.ThreadPool;
import urlprocessor.UrlProcessor;
import fetcher.FetcherFactory;
import fetcher.Fetcher;

public class Main {

    public static void main(String[] args) {
        try {
            //args[0] should be equal to the output commands
            // for example: su, tms.
            // any combination of s u t m any order minimum 1

            //args[1] should be equal to the pool size as the threads amount
            // for example: 5

            //args[2] should be equal to the file name
            // for example : urls.txt
            // so urls.txt will hold addresses, where each thread will get a url
            // to process, we need to have output for each url, and save them in
            // the same order
            // so input should be: stu 5 file.txt

            InputValidator validator = new InputValidator();
            validator.validate(args);

            FetcherFactory factory = new FetcherFactory();
            String contentTypeImage = "image/png";
            Fetcher imageFetcher = factory.createFetcher(contentTypeImage);

            // Initialize URL Processor
            //UrlProcessor urlProcessor = new UrlProcessor(inputFilePath);

            // Initialize your custom ThreadPool
            //ThreadPool threadPool = new ThreadPool(poolSize);

            // Here you would add logic to process URLs using the threadPool
            // For example: urlProcessor.getUrls().forEach(url -> threadPool.execute(new FetchTask(url)));

            // Shutdown the thread pool at the end
            //threadPool.shutdown();

        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }

    private static int parsePoolSize(String poolSizeArg) {
        // Parsing and validation logic for pool size
       // if(poolSizeArg.equals("0"))
        //    return 0;

        return Integer.parseInt(poolSizeArg);
    }

    private static void printUsageAndExit() {
        System.out.println("Usage: java Main <output format> <pool size> <input file>");
        System.exit(1);
    }
}
