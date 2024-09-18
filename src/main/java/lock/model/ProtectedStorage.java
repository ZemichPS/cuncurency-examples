package lock.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProtectedStorage {
    private List<String> storage = new ArrayList<>();
    private final int MAX_CAPACITY;
    private ReentrantLock lock = new ReentrantLock();
    private Condition emptyStorageCondition = lock.newCondition();
    private Condition overflowingStorageCondition = lock.newCondition();

    public ProtectedStorage(int maxCapacity) {
        MAX_CAPACITY = maxCapacity;
    }

    public String pop() throws InterruptedException {
        try {
            lock.lock();
            while (storage.size() == 0) {
                emptyStorageCondition.await();
                System.out.println("Consumer is awaiting signal...");
            }
            overflowingStorageCondition.signalAll();
            return storage.remove(0);
        } finally {
            lock.unlock();
        }

    }

    public void push(String value) throws InterruptedException {
        try {
            lock.lock();
            while (storage.size() == MAX_CAPACITY) {
                overflowingStorageCondition.await();
                System.out.println("Producer is awaiting signal...");
            }
            storage.add(value);
            emptyStorageCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
