package cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        int numberOfThreads = 3;
        // Инициализируем CyclicBarrier для 3 потоков и действия после достижения барьера
        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads, () ->
                System.out.println("Потоки завершили выполнение, продолжаем выполнение...\n"));

        // Запускаем потоки
        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(new DataProcessingTask(barrier, i + 1)).start();
        }
    }
}
