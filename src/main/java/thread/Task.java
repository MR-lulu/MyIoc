package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by blue on 2018/1/23.
 */
public class Task implements Callable<Result>{
    private String name;

    public Task(String name){
        this.name = name;
    }

    @Override
    public Result call() throws Exception {
        System.out.println(name + ":start");

        long duration = (long) (Math.random()*10);
        TimeUnit.SECONDS.sleep(duration);

        int value = 0;
        for (int i=0;i<5;i++){
            value+=(int)(Math.random()*100);
        }

        System.out.println(name + ":end");
        return new Result(name,value);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService =
                Executors.newCachedThreadPool();

        List<Task> taskList = new ArrayList<>();
        List<Future<Result>> resultList = new ArrayList<>();
        for (int i=0;i<3;i++){
            //taskList.add(new Task(i+""));
            resultList.add(executorService.submit(new Task(i+"")));
        }

        executorService.shutdown();

       // executorService.awaitTermination(Integer.MAX_VALUE,TimeUnit.DAYS);
        System.out.println("Main: Printing the results");
        for (int i=0; i<resultList.size(); i++){
            Future<Result> future=resultList.get(i);
            try {
                //导致线程阻塞
                Result result=future.get();
                System.out.println(result.getName()+": "+result.
                        getValue());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
       /* List<Future<Result>> resultList = null;
        try {
            resultList = executorService.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("Main: Printing the results");
        for (int i=0; i<resultList.size(); i++){
            Future<Result> future=resultList.get(i);
            try {
                Result result=future.get();
                System.out.println(result.getName()+": "+result.
                        getValue());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }*/
    }
}
