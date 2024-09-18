package lock.model;

import java.util.ArrayList;
import java.util.List;

public class Producer implements Runnable {

    private ProtectedStorage storage;
    private List<String> news = new ArrayList<>();

    public Producer(ProtectedStorage storage) {
        this.storage = storage;
        getNews();
    }


    @Override
    public void run() {
        ProtectedStorage protectedStorage = storage;
        for (String someNews : news) {
            try {
                protectedStorage.push(someNews);
                System.out.println("Producer pushed news: " + someNews);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void getNews() {
        String newsLines = """
                The Condition class provides the ability for a thread to wait for some condition to occur while executing the critical section.
                This can occur when a thread acquires the access to the critical section but doesn’t have the necessary condition to perform its operation. For example, a reader thread can get access to the lock of a shared queue that still doesn’t have any data to consume.
                Traditionally Java provides wait(), notify() and notifyAll() methods for thread intercommunication.
                Conditions have similar mechanisms, but we can also specify multiple conditions:
                In this article, we saw different implementations of the Lock interface and the newly introduced StampedLock class.
                We also explored how we can make use of the Condition class to work with multiple conditions.
                The complete code for this article is available over on GitHub.
                Another feature provided by StampedLock is optimistic locking.
                Most of the time, read operations don’t need to wait for write operation completion, and as a result of this, the full-fledged read lock isn’t required.
                Instead, we can upgrade to read lock:
                For both write methods, we need to surround the critical section with the write lock — only one thread can get access to it:
                ReentrantReadWriteLock class implements the ReadWriteLock interface.
                Let’s see the rules for acquiring the ReadLock or WriteLock by a thread:
                Read Lock – If no thread acquired the write lock or requested for it, multiple threads can acquire the read lock.
                Write Lock – If no threads are reading or writing, only one thread can acquire the write lock.
                Let’s look at how to make use of the ReadWriteLock:
                """;
        newsLines.lines().forEach(news::add);
    }
}

