package aop;

import java.lang.reflect.Method;

/**
 * Created by blue on 2018/1/5.
 */
public class BeforeProxy extends AbstractProxy {

    @Override
    public void before(Class<?> cls, Method method, Object[] params) {
        System.out.println("Before");
    }
}