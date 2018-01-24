package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by blue on 2018/1/23.
 */
public class ExecutorDemo {
    public static void main(String[] args) {
        Runnable hello = () -> {
            for (int i = 0; i < 50; i++) {
                System.out.println(i + "hello");
            }
        };

        Runnable world = () -> {
            for (int i = 0; i < 50; i++) {
                System.out.println(i + "world");
            }
        };

        ExecutorService service = Executors.newSingleThreadExecutor();

        service.execute(hello);
        service.execute(world);

        //service.shutdown();
    }
}
