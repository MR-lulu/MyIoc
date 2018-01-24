package util;

/**
 * Created by blue on 2018/1/11.
 */
public class Singleton {
    private static Singleton singleton = null;
    private String name;

    private Singleton(){

    }

    /**
     * 线程不安全
     * @return
     */
    public static Singleton getSingleton(){
        if (singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }

    public void setName(String name){
        this.name = name;
    }
}
