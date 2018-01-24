package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by blue on 2018/1/23.
 */
public class TestArrayList {
    /**
     * arrayList线程不安全,vector是线程安全的
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        //List<String> list = new ArrayList<>();
        List<String> list = new Vector<>();
        MyThread myThread1 = new MyThread(list);
        MyThread myThread2 = new MyThread(list);

        myThread1.start();
        myThread2.start();

        myThread1.join();
        myThread2.join();
        System.out.println(list.size());
    }
}

class MyThread extends Thread{
    private List<String> arraylist;

    public MyThread(List<String> arraylist){
        this.arraylist = arraylist;
    }
    @Override
    public void run() {
        for (int i=0;i<1000;i++){
            arraylist.add(i+"");
        }
    }
}
