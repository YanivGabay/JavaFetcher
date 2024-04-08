package main;

import threadpool.ThreadPool;
import urlprocessor.UrlProcessor;

public class Main {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Error: Invalid number of arguments.");
            printUsageAndExit();
        }

        String outputFormat = args[0];

        try {
            int poolSize = parsePoolSize(args[1]);
            String inputFilePath = args[2];

            // Initialize URL Processor
            UrlProcessor urlProcessor = new UrlProcessor(inputFilePath);

            // Initialize your custom ThreadPool
            ThreadPool threadPool = new ThreadPool(poolSize);

            // Here you would add logic to process URLs using the threadPool
            // For example: urlProcessor.getUrls().forEach(url -> threadPool.execute(new FetchTask(url)));

            // Shutdown the thread pool at the end
            threadPool.shutdown();

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
