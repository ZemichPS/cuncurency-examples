package reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private int counter = 0;
    private final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();
        // Создание и запуск 5 потоков, каждый из которых будет увеличивать счётчик

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    example.increment();
                }
                System.out.println(Thread.currentThread().getName() + " завершён");
            }).start();
        }
    }

    public void increment() {
        lock.lock();  // Захват блокировки
        try {
            counter++;  // Критическая секция
        } finally {
            lock.unlock();  // Освобождение блокировки
        }
    }

    public int getCounter() {
        return counter;
    }
}
