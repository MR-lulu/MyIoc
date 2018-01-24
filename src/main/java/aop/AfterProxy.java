package aop;

import java.lang.reflect.Method;

/**
 * Created by blue on 2018/1/5.
 */
public class AfterProxy extends AbstractProxy {

    @Override
    public void after(Class<?> cls, Method method, Object[] params) {
        System.out.println("After");
    }
}
