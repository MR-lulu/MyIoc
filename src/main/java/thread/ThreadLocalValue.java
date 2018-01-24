package thread;

/**
 * Created by blue on 2018/1/5.
 */
public class ThreadLocalValue implements Runnable {

    /**
     * 不要乱用函数式编程。。。同样的方法得到的结果完全不同
     */
    //private ThreadLocal<String> threadLocal = ThreadLocal.withInitial(()->Thread.currentThread().getName());
   // private ThreadLocal<String> threadLocal = ThreadLocal.withInitial(Thread.currentThread()::getName);
    private MyThreadLocal<String> threadLocal = new MyThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return Thread.currentThread().getName();
        }
    };

    @Override
    public void run() {
        for (int i =0;i <3;i++) {
            String a = threadLocal.get();
            System.out.println(Thread.currentThread().getName()+":"+a);
            a = a+i;
            threadLocal.set(a);
        }
    }

    public static void main(String[] args) {
        ThreadLocalValue localValue = new ThreadLocalValue();
        new Thread(localValue,"A").start();
        new Thread(localValue,"B").start();
    }
}
