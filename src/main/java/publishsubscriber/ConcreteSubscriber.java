package publishsubscriber;

public class ConcreteSubscriber implements Subscriber{
    private final String name;

    public ConcreteSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " получил сообщение: " + message);
    }
}
