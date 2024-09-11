package phaser;

import java.util.concurrent.Phaser;

public class PhaserExample {
    public static void main(String[] args) throws InterruptedException {

        Phaser phaser = new Phaser();

        // Создаем несколько потоков
        new Thread(new Worker(phaser, "Поток 1")).start();
        new Thread(new Worker(phaser, "Поток 2")).start();
        new Thread(new Worker(phaser, "Поток 3")).start();

        // Добавляем новый поток на следующей фазе
        Thread.sleep(2_000);  // Имитация задержки
        new Thread(new Worker(phaser, "Поток 4")).start();
    }
}
