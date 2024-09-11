package thred;

import java.util.Random;
import java.util.stream.Stream;

public class Example extends Thread {


    @Override
    public void run() {
        Stream<Integer> stream = Stream.generate(()-> new Random().nextInt(0, 54564))
                .limit(541235656);

        stream.forEach(System.out::println);
    }
}
