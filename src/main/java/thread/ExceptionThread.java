package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by blue on 2018/1/2.
 * 主线程无法捕捉到子线程的异常
 */
public class ExceptionThread implements Runnable{
    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        /*Thread thread = new Thread(new ExceptionThread());

        try {
            thread.start();
        } catch (Exception e){
            System.out.println("main线程");
        }*/
        ExecutorService service = Executors.newFixedThreadPool(1);
        try {
            service.execute(new ExceptionThread());
        } catch (Exception e){
            System.out.println("main线程");
        }

    }
}
