package exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerExample {
    // Создание Exchanger для обмена данными между потоками


    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        Producer producer = new Producer(exchanger, "Hello from producer");
        Consumer consumer = new Consumer(exchanger, "Hello from consumer");


        // Запуск производителя и потребителя
        new Thread(producer).start();
        new Thread(consumer).start();

    }



}
