package cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DataProcessingTask implements Runnable {
    private final CyclicBarrier barrier;
    private final int id;

    public DataProcessingTask(CyclicBarrier barrier, int id) {
        this.barrier = barrier;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            // Этап 1: Обработка части данных
            System.out.println("Поток " + id + " выполняет первую фазу обработки данных.");
            Thread.sleep((long) (Math.random() * 12_000));  // Симуляция работы

            // Ожидание других потоков на барьере
            System.out.println("Поток " + id + " завершил первую фазу, ожидает других.");
            barrier.await();  // Ожидание всех потоков

            // Этап 2: Обработка данных после синхронизации
            System.out.println("Поток " + id + " приступает ко второй фазе обработки.");
            Thread.sleep((long) (Math.random() * 1000));  // Симуляция работы

            // Ожидание других потоков на барьере для следующей фазы
            barrier.await();

            System.out.println("Поток " + id + " завершил обработку.");
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
        }
    }
}
