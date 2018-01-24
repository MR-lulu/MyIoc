package pojo;

import java.lang.reflect.Proxy;

/**
 * Created by blue on 2018/1/12.
 */
public class Singleton {
    private static Singleton instance = null;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null) {

            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
    public void test(){
        System.out.println(this==instance);
    }

    public static void main(String[] args) {
        getInstance().test();
    }
}
