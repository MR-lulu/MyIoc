package thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by blue on 2018/1/5.
 */
public class MyThreadLocal<T> {
    @SuppressWarnings("unchecked")
    private Map<Thread,T> threadMap = new ConcurrentHashMap();

    public T get(){
        Thread thread = Thread.currentThread();
        T t = threadMap.get(thread);
        if (!threadMap.containsKey(thread)){
            t = initialValue();
            threadMap.put(thread,t);
        }
        return t;
    }

    public void set(T value){
        threadMap.put(Thread.currentThread(),value);
    }

    public void remove(){
        threadMap.remove(Thread.currentThread());
    }
    protected T initialValue(){
        return null;
    }
}
