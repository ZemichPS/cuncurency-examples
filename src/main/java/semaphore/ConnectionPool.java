package semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ConnectionPool {
    private final Semaphore semaphore;
    private int maxConnections;

    public ConnectionPool(int maxConnections) {
        this.maxConnections = maxConnections;
        semaphore = new Semaphore(maxConnections);
    }

    public void getConnection() throws InterruptedException {
        semaphore.acquire();
        System.out.println(Thread.currentThread().getName() + " get connection.");
        // Симуляция использования соединения
        Thread.sleep(new Random().nextInt(800, 3500)); // Используем соединение 2 секунды
        // Возврат соединения
        releaseConnection();
    }

    public void releaseConnection() {
        System.out.println(Thread.currentThread().getName() + " release connection.");
        semaphore.release();  // Освобождаем соединение
    }
}
