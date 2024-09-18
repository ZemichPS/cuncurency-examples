package publishsubscriber;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private final List<Subscriber> subscribers = new ArrayList<>();

    // Метод для добавления подписчика
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    // Метод для удаления подписчика
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    // Метод для публикации сообщения всем подписчикам
    public void publish(String message) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(message);
        }
    }
}
