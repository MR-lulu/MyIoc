package thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by blue on 2018/1/2.
 */
public class ConcurrentHashMapVsSynchronizedMap {
    public final static int THREAD_POOL_SIZE = 5;

    public static Map<String,Integer> hashTable = null;
    public static Map<String,Integer> synchronizedMap = null;
    public static Map<String,Integer> concurrentHashMap = null;

    public static void main(String[] args) throws InterruptedException {
        hashTable = new Hashtable<>();
        performTest(hashTable);

        synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        performTest(synchronizedMap);

        concurrentHashMap = new ConcurrentHashMap<>();
        performTest(concurrentHashMap);
    }

    public static void performTest(final Map<String,Integer> map) throws InterruptedException {
        System.out.println("Test started for:"+map.getClass());

        long averageTime = 0L;
        for (int i = 0;i<5;i++){
            long startTime = System.nanoTime();
            ExecutorService service = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

            for (int j = 0;j<THREAD_POOL_SIZE;j++){
                service.execute(()->{
                    for (int k = 0;k<50000;k++){
                        Integer randomNuber = (int)Math.ceil(Math.random()*550000);

                        Integer value = map.get(String.valueOf(randomNuber));

                        map.put(String.valueOf(randomNuber),randomNuber);
                    }
                });

            }

            service.shutdown();
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

            long endTime = System.nanoTime();
            long totalTime = (endTime - startTime) /1000000L;
            averageTime += totalTime;
            System.out.println("2500K entried added/retrieved in " + totalTime + " ms");
        }

        System.out.println("For " + map.getClass() + " the average time is " + averageTime / 5 + " ms\n");
    }
}
