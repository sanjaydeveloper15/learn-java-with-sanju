// Lesson 7: Concurrency Basics
// Coming from Node.js: Java concurrency is multi-threaded (real parallelism), not event-loop.
// Coming from Python: no GIL — Java threads run truly in parallel.

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// ─── 1. Thread — the basics ───────────────────────────────────────────────────
// A Thread is an independent unit of execution.
// Like a worker — runs alongside the main program.

class ThreadDemo {
    static void run() throws InterruptedException {

        // Way 1: extend Thread
        Thread t1 = new Thread() {
            public void run() {
                System.out.println("Thread 1 running: " + Thread.currentThread().getName());
            }
        };

        // Way 2: pass a Runnable lambda (preferred — cleaner)
        Thread t2 = new Thread(() -> {
            System.out.println("Thread 2 running: " + Thread.currentThread().getName());
        });

        t1.start();  // start() launches the thread — DO NOT call run() directly
        t2.start();

        t1.join();   // wait for t1 to finish before continuing (like JS await / Python join)
        t2.join();
    }
}

// ─── 2. Race condition & synchronized ────────────────────────────────────────
// Race condition: two threads modify shared data at the same time → unpredictable result.
// synchronized keyword locks a method/block so only one thread runs it at a time.

class Counter {
    private int count = 0;

    // Without synchronized — count would be wrong under concurrent access
    synchronized void increment() {
        count++;
    }

    int getCount() {
        return count;
    }
}

class SyncDemo {
    static void run() throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Count (should be 2000): " + counter.getCount());
    }
}

// ─── 3. ExecutorService — thread pool ─────────────────────────────────────────
// Managing raw threads is error-prone. Use ExecutorService — a managed thread pool.
// Like Node's worker_threads pool or Python's ThreadPoolExecutor.

class ExecutorDemo {
    static void run() throws InterruptedException {
        // Fixed pool of 3 threads — reuses them for all tasks
        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            pool.submit(() -> {
                System.out.println("Task " + taskId + " on " + Thread.currentThread().getName());
            });
        }

        pool.shutdown();                        // stop accepting new tasks
        pool.awaitTermination(5, TimeUnit.SECONDS);  // wait for all tasks to finish
    }
}

// ─── 4. Future & Callable — get a result from a thread ────────────────────────
// Runnable has no return value. Callable<T> returns a value.
// Future<T> holds the result — like a JS Promise.

class FutureDemo {
    static void run() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        // Callable — like a Runnable that returns a value
        Callable<Integer> task1 = () -> {
            Thread.sleep(100);
            return 10 + 20;
        };
        Callable<Integer> task2 = () -> {
            Thread.sleep(50);
            return 5 * 6;
        };

        Future<Integer> future1 = pool.submit(task1);
        Future<Integer> future2 = pool.submit(task2);

        // .get() blocks until the result is ready — like JS await
        System.out.println("Result 1: " + future1.get());  // 30
        System.out.println("Result 2: " + future2.get());  // 30

        pool.shutdown();
    }
}

// ─── 5. volatile — visibility across threads ─────────────────────────────────
// Without volatile, threads may cache a variable and not see updates from other threads.
// volatile forces reads/writes to go to main memory — guarantees visibility.

class VolatileDemo {
    static volatile boolean running = true;  // visible to all threads immediately

    static void run() throws InterruptedException {
        Thread worker = new Thread(() -> {
            int count = 0;
            while (running) {
                count++;
            }
            System.out.println("Worker stopped after count: " + count);
        });

        worker.start();
        Thread.sleep(1);  // let worker run briefly
        running = false;  // signal worker to stop — volatile ensures worker sees this
        worker.join();
    }
}

// ─── Main — run everything ────────────────────────────────────────────────────

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("=== Threads ===");
        ThreadDemo.run();

        System.out.println("\n=== Synchronized (Race Condition Fix) ===");
        SyncDemo.run();

        System.out.println("\n=== ExecutorService (Thread Pool) ===");
        ExecutorDemo.run();

        System.out.println("\n=== Future & Callable ===");
        FutureDemo.run();

        System.out.println("\n=== Volatile ===");
        VolatileDemo.run();
    }
}
