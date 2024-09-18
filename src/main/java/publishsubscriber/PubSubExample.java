package publishsubscriber;

public class PubSubExample {
    public static void main(String[] args) {
        Publisher publisher = new Publisher();

        // Создаем подписчиков
        Subscriber subscriber1 = new ConcreteSubscriber("Подписчик 1");
        Subscriber subscriber2 = new ConcreteSubscriber("Подписчик 2");

        // Подписываем их на издателя
        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);

        // Публикуем сообщения
        publisher.publish("Сообщение 1");
        publisher.publish("Сообщение 2");

        // Отписываем одного из подписчиков
        publisher.unsubscribe(subscriber1);

        // Публикуем ещё одно сообщение
        publisher.publish("Сообщение 3");
    }
}
