package phaser;

import java.util.concurrent.Phaser;

public class Worker implements Runnable {

    private final Phaser phaser;
    private final String name;

    public Worker(Phaser phaser, String name) {
        this.phaser = phaser;
        this.name = name;
        this.phaser.register();
    }

    @Override

    public void run() {
        // Фаза 1
        System.out.println(name + " выполняет первую фазу.");
        phaser.arriveAndAwaitAdvance();  // Ожидание завершения всеми первой фазы

        // Фаза 2
        System.out.println(name + " выполняет вторую фазу.");
        phaser.arriveAndAwaitAdvance();  // Ожидание завершения всеми второй фазы

        // Фаза 3
        System.out.println(name + " выполняет третью фазу.");
        phaser.arriveAndDeregister();  // Завершение работы участника
    }
}
