// 2. Thread Pool Implementation

//     Create a thread pool that:
//         Maintains a fixed number of threads.
//         Executes submitted tasks using the available threads.
//         Reuses threads when they become idle.
//     Demonstrate the use of the thread pool by executing multiple tasks.

import java.util.ArrayList;
import java.util.List;

class TaskHandler {
  Object executeTask(int limit) {
    return null;
  }
}

class ThreadPool {
  static volatile ThreadPool threadPoolInstance;
  List<Object> threadPool;
  List<Object> inUse;
  final int MAX_THREADS = 5;
  private ThreadPool() {
    threadPool = new ArrayList<>();
    inUse = new ArrayList<>();
    for (int i = 0; i < MAX_THREADS; ++i) {
      threadPool.add(new TaskHandler());
    }
  }
  public static ThreadPool getInstance() {
    if (threadPoolInstance == null) {
      synchronized(ThreadPool.class) {
        if (threadPoolInstance == null) {
          threadPoolInstance = new ThreadPool();
        }
      }
    }
    return threadPoolInstance;
  }
  public synchronized Object performTask(int limit) {
    if (threadPool.size() > 0) {
      Object currentInstance = threadPool.getLast();
      threadPool.remove(currentInstance);
      inUse.add(currentInstance);
      return currentInstance;
    }
    else {
      System.out.println("No more threads available in the thread pool");
      return null;
    }
  }
  public synchronized void onTaskCompletion(Object currentInstance) {
    inUse.remove(currentInstance);
    threadPool.add(currentInstance);
  }
}

public class ObjectPoolDesignPattern {
  public static void main(String []args) {
    ThreadPool threadPool = ThreadPool.getInstance();
    Object instance1 = threadPool.performTask(100);
    Object instance2 = threadPool.performTask(100);
    Object instance3 = threadPool.performTask(100);
    Object instance4 = threadPool.performTask(100);
    Object instance5 = threadPool.performTask(100);
    Object instance6 = threadPool.performTask(100);
    Object instance7 = threadPool.performTask(100);
    threadPool.onTaskCompletion(instance2);
    threadPool.onTaskCompletion(instance1);
    Object instance8 = threadPool.performTask(100);
    Object instance9 = threadPool.performTask(100);
  }
}
