import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class TaskHandler implements Runnable {
    private int taskId;

    public TaskHandler(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Executing task: " + taskId + " by " + Thread.currentThread().getName());
        try {
            // Simulate task execution time
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Task interrupted: " + taskId);
        }
        System.out.println("Task completed: " + taskId + " by " + Thread.currentThread().getName());
    }
}

class ThreadPool {
    private final BlockingQueue<Runnable> taskQueue;
    private final Thread[] workers;
    private volatile boolean isShutdown = false;

    public ThreadPool(int maxThreads) {
        taskQueue = new LinkedBlockingQueue<>();
        workers = new Thread[maxThreads];

        for (int i = 0; i < maxThreads; i++) {
            workers[i] = new Thread(() -> {
                while (!isShutdown || !taskQueue.isEmpty()) {
                    try {
                        Runnable task = taskQueue.take(); // Get a task from the queue
                        task.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }, "Worker-" + (i + 1));
            workers[i].start();
        }
    }

    public void submitTask(Runnable task) {
        if (!isShutdown) {
            taskQueue.offer(task);
        } else {
            System.out.println("ThreadPool is shut down. Cannot accept new tasks.");
        }
    }

    public void shutdown() {
        isShutdown = true;
        for (Thread worker : workers) {
            worker.interrupt();
        }
    }
}

public class AgainObjectPoolDesignPattern {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(3); // Create a pool with 3 threads

        for (int i = 1; i <= 10; i++) {
            threadPool.submitTask(new TaskHandler(i));
        }

        // Simulate shutdown after submitting tasks
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        threadPool.shutdown();
    }
}
