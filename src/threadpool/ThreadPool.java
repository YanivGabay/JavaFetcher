package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    private final ExecutorService executor;

    public ThreadPool(int poolSize) {
        this.executor = Executors.newFixedThreadPool(poolSize);
    }

    public void execute(Runnable task) {
        executor.execute(task);
    }

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
