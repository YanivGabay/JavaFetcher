package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * A simple thread pool implementation.
 */
public class ThreadPool {
    private final ExecutorService executor;

    /**
     * Constructs a ThreadPool with the specified pool size.
     *
     * @param poolSize The size of the thread pool.
     */
    public ThreadPool(int poolSize) {
        this.executor = Executors.newFixedThreadPool(poolSize);
    }

    /**
     * Executes the given task in the thread pool.
     *
     * @param task The task to execute.
     */
    public void execute(Runnable task) {
        executor.execute(task);
    }

    /**
     * Shuts down the thread pool.
     */
    public void shutdown() {
        executor.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait for existing tasks to terminate
            if (!executor.awaitTermination(15, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Cancel currently executing tasks
                // Wait a bit for tasks to respond to being cancelled
                if (!executor.awaitTermination(15, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            executor.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
