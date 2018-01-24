package thread;

/**
 * Created by blue on 2017/12/29.
 * 终止线程（异常法）
 */
public class Interrupte extends Thread{
    @Override
    public void run() {
        try {
            for (int i = 0;i<50;i++){
                if (this.isInterrupted()){
                    throw new InterruptedException();
                }
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            System.out.println("线程安全退出！");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Interrupte();
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
