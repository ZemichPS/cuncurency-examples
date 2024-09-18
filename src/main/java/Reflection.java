import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Reflection {


    public static void main(String[] args) {
        TenListWithFactory<Cat> catTenListWithFactory = new TenListWithFactory<>(Cat::new);

    }
}


class TenList<T> {
    private List<T> list = new ArrayList<T>();

    public TenList(Class<T> tClass) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        for (int i = 0; i < 10; i++) {
            T instanceOfT = (T) tClass.getConstructor().newInstance(null);
            list.add(instanceOfT);
        }
    }
}


class TenListWithFactory<T> {
    private List<T> list = new ArrayList<T>();

    public TenListWithFactory(Supplier<T> factory) {
        for (int i = 0; i < 10; i++) {
            list.add(factory.get());
        }
    }
}

class Cat {
    private String name;

    public Cat(String name) {
        this.name = name;
    }

    public Cat() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
