package reentrantlockwithcondition;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerExample {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int MAX_CAPACITY = 5;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        ProducerConsumerExample example = new ProducerConsumerExample();

        // Поток производителя
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                example.produce(i);
            }
        });

        // Поток потребителя
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                example.consume();
            }
        });

        producer.start();
        consumer.start();
    }

    // Метод для добавления элементов в очередь
    public void produce(int value) {
        lock.lock();  // Блокировка для критической секции
        try {
            // Если очередь заполнена, ждём, пока освободится место
            while (queue.size() == MAX_CAPACITY) {
                notFull.await();  // Ожидаем, пока не появится место
            }
            queue.offer(value);  // Добавляем элемент в очередь
            System.out.println("Произведено: " + value);

            notEmpty.signal();  // Уведомляем потребителя, что элемент доступен
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();  // Обязательно освобождаем блокировку
        }
    }

    // Метод для потребления элементов из очереди
    public void consume() {
        lock.lock();  // Блокировка для критической секции
        try {
            // Если очередь пуста, ждём, пока не появятся элементы
            while (queue.isEmpty()) {
                notEmpty.await();  // Ожидаем, пока не появится элемент
            }
            int value = queue.poll();  // Извлекаем элемент из очереди
            System.out.println("Потреблено: " + value);

            notFull.signal();  // Уведомляем производителя, что появилось место
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();  // Обязательно освобождаем блокировку
        }
    }
}
